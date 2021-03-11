package com.mycompany.app;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "build", description = "build")
class Build implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("build");
        return 1;
    }
}