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

public class InitTest {

    private String projectPath = "/initTest";

    @Test
    public void CleanAllFilesInFolder() throws Exception {

        File[] allFiles = new File[6];
        allFiles[0] = new File(projectPath);
        allFiles[1] = new File(projectPath + "/content");
        allFiles[2] = new File(projectPath  +  "/index.md");
        allFiles[3] = new File(projectPath + "/config.json");
        allFiles[4] = new File(projectPath + "/content" +  "/page.md");
        allFiles[5] = new File(projectPath + "/content" +  "/image.png");

        for(int i = allFiles.length; i > 0; --i){
            if(allFiles[i-1].exists()){
                allFiles[i-1].delete();
            }
        }

        Callable<Integer> callable = new Statique();
        CommandLine cmd = new CommandLine(callable);
        String[] args = new String[]{"init", projectPath};

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