package com.mycompany.app.commands;

import java.util.concurrent.Callable;
import picocli.CommandLine.Command;

@CommandLine.Command(name = "new", description = "new")
class New implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("new");
        return 1;
    }
}