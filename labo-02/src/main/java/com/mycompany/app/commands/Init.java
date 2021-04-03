package com.mycompany.app.commands;

import java.util.concurrent.Callable;
import picocli.CommandLine;
import com.mycompany.app.Utils.GenerateFolders;

@CommandLine.Command(name = "init", description = "Commande de initialisation")
public class Init implements Callable<Integer> {

    @CommandLine.Parameters(index="0") String path;

    @Override
    public Integer call() throws Exception {
        initFolder();
        return 1;
    }

    public void initFolder() {
        new GenerateFolders(path).generatedFiles();
    }

}