package com.mycompany.app;

import com.mycompany.app.commands.Build;
import com.mycompany.app.commands.Clean;
import com.mycompany.app.commands.New;
import com.mycompany.app.commands.Serve;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(name = "Main",
         description = "A brand new static site generator.",
         subcommands = { New.class, Clean.class, Build.class, Serve.class })
class Main implements Callable<Integer> {
    final static String VERSION = "0.1";


    public static void main(String... args) {

        CommandLine cmd = new CommandLine(new Main());
        int exitCode = 1;
        if(cmd.isVersionHelpRequested()){
            System.out.println("Version : " + VERSION);
        }
        else {
            exitCode = cmd.execute(args);
        }
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        CommandLine.usage(this, System.out);
        return 0;
    }



}