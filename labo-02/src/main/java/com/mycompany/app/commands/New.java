package com.mycompany.app.commands;

import java.util.concurrent.Callable;
import picocli.CommandLine;

@CommandLine.Command(name = "new", description = "new")
public class New implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("new command");
        return 1;
    }
}