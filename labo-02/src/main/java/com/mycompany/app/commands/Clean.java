package com.mycompany.app.commands;

import picocli.CommandLine;

import java.io.File;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "clean", description = "clean")
public class Clean implements Callable<Integer> {

    @CommandLine.Parameters(index="0") String userPath;

    @Override
    public Integer call() throws Exception {
        cleanProject();
        return 1;
    }

    boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }


    void cleanProject(){
        // Get Path
        String path = System.getProperty("user.dir") + userPath;
        deleteDirectory(new File(path + "/build"));
    }
}
