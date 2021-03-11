package com.mycompany.app.commands;

import picocli.CommandLine;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "build", description = "build")
public class Build implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("build command");
        return 1;
    }
}