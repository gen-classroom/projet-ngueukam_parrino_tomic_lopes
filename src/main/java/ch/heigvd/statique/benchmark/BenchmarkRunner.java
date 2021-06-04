package ch.heigvd.statique.benchmark;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkRunner {

    public static void main(String... args) throws RunnerException {
        Options options = new OptionsBuilder()
                .forks(2)
                .warmupIterations(2)
                .measurementIterations(5)
                .build();
        new Runner(options).run();
    }

}