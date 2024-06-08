package com.github.leleact.jtest.commons.lang3;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 5)
@Threads(4)
@Fork(1)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class DateTimeBenchMark {

    @Param(value = {"10", "50", "100", "10000000"})
    private int loopTimes;

    @Benchmark
    public void testStringAdd(Blackhole blackhole) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < loopTimes; i++) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            sdf.format(new Date());
        }
        long end = System.currentTimeMillis();
        blackhole.consume((end - start));
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(DateTimeBenchMark.class.getSimpleName())
                                          .result("result.json")
                                          .resultFormat(ResultFormatType.JSON)
                                          .build();
        new Runner(opt).run();
    }
}
