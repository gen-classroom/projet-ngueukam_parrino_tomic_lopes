package ch.heigvd.statique.commands;

import picocli.CommandLine;
import java.io.File;
import java.util.concurrent.Callable;

/**
 * Class representing the clean command,
 * allows you to delete the current state of the static site
 */
@CommandLine.Command(name = "clean", description = "Clear project")
public class Clean implements Callable<Integer> {

    //Get user path
    @CommandLine.Parameters(index = "0")
    String userPath;

    /**
     * Function called when the "clean" command is invoked
     * @return Returns 1 if the build folder was correctly deleted,
     *         Returns -1 if an error occurred
     */
    @Override
    public Integer call() {
        return cleanProject();
    }

    /**
     * Function that allows you to delete a folder recursively
     * @param directoryToBeDeleted the folder
     * @return true if the folder has been correctly deleted, otherwise false.
     */
    private boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

    /**
     * Delete the /build folder
     * @return Returns 1 if the folder was correctly deleted,
     *         Returns -1 if an error occurred
     */
    private Integer cleanProject() {
        //Delete build folder
        if (deleteDirectory(new File( userPath + "/build"))) {
            System.out.println("Delete correctly done");
            return 1;
        }else
            return -1;
    }
}
