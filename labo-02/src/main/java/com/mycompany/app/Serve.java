package com.mycompany.app;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "serve", description = "serve")
class Serve implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("serve");
        return 1;
    }
}