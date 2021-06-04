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

public class CleanTest {

    private String projectPath = "/cleantest";
    private String buildPath = "/build";

    @Test
    public void CleanAllFilesInFolder() throws Exception {
        File buildFolder = new File(projectPath);
        File initFolder = new File(projectPath + buildPath);
        File randomFile = new File(projectPath + buildPath + "/testFile.txt");
        File randomFolder = new File(projectPath + buildPath + "/test");
        File randomFolderFile = new File(projectPath + buildPath + "/test/testFile2.txt");

        buildFolder.mkdirs();
        initFolder.mkdirs();
        randomFolder.mkdirs();

        randomFile.createNewFile();
        randomFolderFile.createNewFile();

        Callable<Integer> callable = new Statique();
        CommandLine cmd = new CommandLine(callable);
        String[] args = new String[]{"clean", projectPath};

        cmd.execute(args);
        assertFalse(initFolder.exists());
        assertTrue(buildFolder.exists());
        assertEquals(buildFolder.listFiles().length, 0);


        randomFolderFile.delete();
        randomFolder.delete();
        randomFile.delete();
        initFolder.delete();
        buildFolder.delete();

    }
}