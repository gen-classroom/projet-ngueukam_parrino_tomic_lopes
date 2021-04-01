package com.mycompany.app;

import com.mycompany.app.commands.Build;
import com.mycompany.app.commands.Clean;
import com.mycompany.app.commands.New;
import com.mycompany.app.commands.Serve;
import com.mycompany.app.commands.Version;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(name = "Main",
         description = "A brand new static site generator.",
         subcommands = { New.class, Clean.class, Build.class, Serve.class, Version.class})
class Main implements Callable<Integer> {


    public static void main(String... args) {

        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        CommandLine.usage(this, System.out);
        return 0;
    }




}