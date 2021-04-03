package com.mycompany.app.commands;


import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.IVersionProvider;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "-version", description = "Retourne la version du programme.")
public class Version implements Callable<Integer> {

    private final String VERSION = "0.0.1";
    @Override
    public Integer call() throws Exception {
        System.out.println("version : " + VERSION);
        return 0;
    }
}