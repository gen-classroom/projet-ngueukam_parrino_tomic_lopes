package com.mycompany.app.commands;

import java.util.concurrent.Callable;
import picocli.CommandLine;
import com.mycompany.app.Utils.GenerateFolders;

@CommandLine.Command(name = "init", description = "init")
public class Init implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        initFolder();
        return 1;
    }

    public void initFolder() {
        new GenerateFolders().generatedFiles();
    }

}