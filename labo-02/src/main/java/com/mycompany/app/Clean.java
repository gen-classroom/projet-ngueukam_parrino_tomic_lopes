package com.mycompany.app;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "clean", description = "clean")
class Clean implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("clean");
        return 1;
    }
}
