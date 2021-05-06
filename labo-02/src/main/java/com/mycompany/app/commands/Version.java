package com.mycompany.app.commands;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "-version", description = "Retourne la version du programme.")
public class Version implements Callable<Integer> {

    private final String VERSION = "0.0.2";

    @Override
    public Integer call() {
        //Print Version
        System.out.println("version : " + VERSION);
        return 0;
    }
}