package com.mycompany.app;

import com.mycompany.app.commands.*;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(name = "Main",
        description = "A brand new static site generator.",
        subcommands = {Init.class, Clean.class, Build.class, Serve.class, Version.class})
class Main implements Callable<Integer> {


    public static void main(String... args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        //If user enter wrong command
        CommandLine.usage(this, System.out);
        return 0;
    }

}