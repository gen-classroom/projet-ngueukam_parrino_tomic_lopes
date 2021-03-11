package com.mycompany.app.commands;

import picocli.CommandLine;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "clean", description = "clean")
public class Clean implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("clean command");
        return 1;
    }
}
