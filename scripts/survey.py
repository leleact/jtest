#!/usr/bin/env python3
"""Summarize the POM JSON inventory: unique deps, properties, plugins, leaf modules."""
import json
import sys
from collections import defaultdict
from pathlib import Path

data = json.load(open(sys.argv[1], "r", encoding="utf-8"))

leaves = [p for p in data if p.get("packaging") != "pom"]
aggregators = [p for p in data if p.get("packaging") == "pom"]
print(f"Total POMs: {len(data)}")
print(f"Leaf modules (jar/war/etc): {len(leaves)}")
print(f"Aggregator (pom) modules: {len(aggregators)}")

packagings = defaultdict(int)
for p in data:
    packagings[p.get("packaging", "jar")] += 1
print("Packagings:", dict(packagings))

# All explicit properties
all_props = defaultdict(set)
for p in data:
    for k, v in (p.get("properties") or {}).items():
        all_props[k].add(v)

print("\n== Properties seen ==")
for k in sorted(all_props):
    vals = all_props[k]
    if len(vals) > 1:
        print(f"  {k}: MULTIPLE -> {vals}")
    else:
        print(f"  {k}: {next(iter(vals))}")

# All unique dependencies (group:artifact)
ga = set()
ga_with_version = defaultdict(set)
for p in data:
    for d in (p.get("dependencies") or []):
        key = f"{d.get('groupId')}:{d.get('artifactId')}"
        ga.add(key)
        if d.get("version"):
            ga_with_version[key].add(d["version"])
    for d in (p.get("dependency_management") or []):
        key = f"{d.get('groupId')}:{d.get('artifactId')}"
        ga.add(key)
        if d.get("version"):
            ga_with_version[key].add(d["version"])

print(f"\nUnique group:artifact: {len(ga)}")
print("\n== Dependencies with explicit versions ==")
for k in sorted(ga_with_version):
    print(f"  {k}: {ga_with_version[k]}")

print("\n== Dependencies without explicit version (rely on BOM/DM) ==")
for k in sorted(ga - set(ga_with_version)):
    print(f"  {k}")

# Plugins
all_plugins = set()
for p in data:
    for pl in (p.get("plugins") or []) + (p.get("plugin_management") or []):
        all_plugins.add(f"{pl.get('groupId')}:{pl.get('artifactId')}={pl.get('version')}")
print("\n== Plugins ==")
for pl in sorted(all_plugins):
    print(f"  {pl}")

# Leaf module list
print("\n== Leaf modules (gradle subprojects) ==")
for p in sorted(leaves, key=lambda x: x["path"]):
    parent_dir = str(Path(p["path"]).parent).replace("\\", "/")
    print(f"  {parent_dir} | artifactId={p['artifactId']} | packaging={p['packaging']}")

# Inter-module deps (groupId == com.github.leleact.jtest.* implies internal)
print("\n== Internal cross-module deps ==")
for p in data:
    deps = [d for d in (p.get("dependencies") or []) if d.get("groupId", "").startswith("com.github.leleact")]
    if deps:
        print(f"  {p['path']} -> {[d['artifactId'] for d in deps]}")
