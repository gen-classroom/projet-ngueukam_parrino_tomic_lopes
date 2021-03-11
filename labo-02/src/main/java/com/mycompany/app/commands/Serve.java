package com.mycompany.app.commands;

import picocli.CommandLine;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "serve", description = "serve")
public class Serve implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("serve command");
        return 1;
    }
}