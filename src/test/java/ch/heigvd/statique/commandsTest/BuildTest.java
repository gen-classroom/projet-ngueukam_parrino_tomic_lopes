package ch.heigvd.statique.commandsTest;

import ch.heigvd.statique.Statique;

import java.io.File;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.util.concurrent.Callable;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Class representing the version command,
 * allows you to see the current version
 */

public class BuildTest {

    private String projectPath = "/buildTest";
    private String buildPath = "/build";

    @Test
    public void CleanAllFilesInFolder() throws Exception {

        File[] allFiles = new File[4];

        allFiles[0] = new File(projectPath + buildPath+  "/content");
        allFiles[1] = new File(projectPath + buildPath+  "/content/page.html");
        allFiles[2] = new File(projectPath + buildPath +  "/content/image.png");
        allFiles[3] = new File(projectPath + buildPath +  "/index.html");
        

        Callable<Integer> callable = new Statique();
        CommandLine cmd = new CommandLine(callable);
        String[] args = new String[]{"init", projectPath};
        cmd.execute(args);


        args = new String[]{"build", projectPath};

        cmd.execute(args);

        for(int i = 0; i < allFiles.length; ++i){
            assertTrue(allFiles[i].exists());
        }

        for(int i = allFiles.length; i > 0; --i){
            if(allFiles[i-1].exists()){
                allFiles[i-1].delete();
            }
        }

    }
}