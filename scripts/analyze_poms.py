#!/usr/bin/env python3
"""Walk every pom.xml in the workspace and emit a JSON inventory."""
from __future__ import annotations

import json
import sys
import xml.etree.ElementTree as ET
from pathlib import Path

NS = "{http://maven.apache.org/POM/4.0.0}"
ROOT = Path(__file__).resolve().parents[1]


def tag(elem):
    return elem.tag.split("}", 1)[-1] if "}" in elem.tag else elem.tag


def text(elem, name, default=None):
    if elem is None:
        return default
    child = elem.find(NS + name)
    if child is None:
        child = elem.find(name)
    return child.text.strip() if child is not None and child.text else default


def children(elem, name):
    if elem is None:
        return []
    nodes = elem.findall(NS + name)
    if not nodes:
        nodes = elem.findall(name)
    return nodes


def parse_dependencies(elem):
    out = []
    if elem is None:
        return out
    deps_root = elem.find(NS + "dependencies") or elem.find("dependencies")
    if deps_root is None:
        return out
    for dep in children(deps_root, "dependency"):
        excl_root = dep.find(NS + "exclusions") or dep.find("exclusions")
        exclusions = []
        if excl_root is not None:
            for ex in children(excl_root, "exclusion"):
                exclusions.append({
                    "groupId": text(ex, "groupId"),
                    "artifactId": text(ex, "artifactId"),
                })
        out.append({
            "groupId": text(dep, "groupId"),
            "artifactId": text(dep, "artifactId"),
            "version": text(dep, "version"),
            "scope": text(dep, "scope"),
            "type": text(dep, "type"),
            "classifier": text(dep, "classifier"),
            "optional": text(dep, "optional"),
            "exclusions": exclusions,
        })
    return out


def parse_properties(elem):
    props = {}
    p = elem.find(NS + "properties") or elem.find("properties")
    if p is None:
        return props
    for child in list(p):
        name = tag(child)
        props[name] = child.text.strip() if child.text else ""
    return props


def parse_plugins(elem):
    plugins = []
    if elem is None:
        return plugins
    plugins_root = elem.find(NS + "plugins") or elem.find("plugins")
    if plugins_root is None:
        return plugins
    for pl in children(plugins_root, "plugin"):
        plugins.append({
            "groupId": text(pl, "groupId"),
            "artifactId": text(pl, "artifactId"),
            "version": text(pl, "version"),
        })
    return plugins


def parse_pom(path: Path):
    try:
        tree = ET.parse(path)
    except ET.ParseError as e:
        return {"path": str(path), "error": str(e)}
    root = tree.getroot()
    parent = root.find(NS + "parent") or root.find("parent")
    info = {
        "path": path.relative_to(ROOT).as_posix(),
        "groupId": text(root, "groupId") or (text(parent, "groupId") if parent is not None else None),
        "artifactId": text(root, "artifactId"),
        "version": text(root, "version") or (text(parent, "version") if parent is not None else None),
        "packaging": text(root, "packaging", "jar"),
        "name": text(root, "name"),
        "parent": None,
        "modules": [],
        "properties": parse_properties(root),
        "dependencies": parse_dependencies(root),
        "dependency_management": [],
        "plugins": [],
        "plugin_management": [],
    }
    modules_root = root.find(NS + "modules") or root.find("modules")
    if modules_root is not None:
        info["modules"] = [m.text.strip() for m in children(modules_root, "module") if m.text]

    if parent is not None:
        info["parent"] = {
            "groupId": text(parent, "groupId"),
            "artifactId": text(parent, "artifactId"),
            "version": text(parent, "version"),
            "relativePath": text(parent, "relativePath"),
        }

    dm = root.find(NS + "dependencyManagement") or root.find("dependencyManagement")
    if dm is not None:
        info["dependency_management"] = parse_dependencies(dm)

    build = root.find(NS + "build") or root.find("build")
    if build is not None:
        info["plugins"] = parse_plugins(build)
        pm = build.find(NS + "pluginManagement") or build.find("pluginManagement")
        if pm is not None:
            info["plugin_management"] = parse_plugins(pm)
        info["finalName"] = text(build, "finalName")

    return info


def main():
    poms = sorted(ROOT.rglob("pom.xml"))
    out = [parse_pom(p) for p in poms]
    json.dump(out, sys.stdout, indent=2, ensure_ascii=False)


if __name__ == "__main__":
    main()
