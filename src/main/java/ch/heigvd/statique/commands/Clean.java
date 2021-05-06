package ch.heigvd.statique.commands;

import picocli.CommandLine;
import java.io.File;
import java.util.concurrent.Callable;

/**
 *
 */
@CommandLine.Command(name = "clean", description = "Clear project")
public class Clean implements Callable<Integer> {

    // Get Path
    @CommandLine.Parameters(index = "0")
    String userPath;

    @Override
    public Integer call() {
        cleanProject();
        return 1;
    }

    /**
     *
     * @param directoryToBeDeleted
     * @return
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
     *
     */
    private void cleanProject() {
        // Get Path
        String path = System.getProperty("user.dir") + userPath;

        //Delete build folder
        if (deleteDirectory(new File(path + "/build")))
            System.out.println("Delete correctly done");
    }
}
