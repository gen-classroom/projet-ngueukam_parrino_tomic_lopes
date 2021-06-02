package ch.heigvd.statique.commands;

import picocli.CommandLine;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * Class representing the serve command,
 * allows you to open your static website in your default browser
 */
@CommandLine.Command(name = "serve", description = "serve")
public class Serve implements Callable<Integer> {

    @CommandLine.Parameters(index = "0")
    String userPath;

    /**
     * Function called when the "serve" command is invoked
     */
    @Override
    public Integer call() {
        File root = new File(userPath);
        // We retrieve the build folder
        File buildDirectory = getBuildFolder(root);
        if (buildDirectory == null) {   // An error message is displayed if not found and the execution is interrupted
            System.err.println("No Build directory Found. You should make a build command first.");
            return 0;
        }
        // We retrieve the index.html file from the build folder
        File indexHtmlFile = getIndexHtmlFile(buildDirectory);
        if (indexHtmlFile == null) {    // On affiche un message d'erreur si pas trouvé et on interrompt l'exécution
            System.err.println("An error occurs. index.html file not found. Please run build command");
            return 0;
        }
        try {   // On ouvre le fichier dans le navigateur.
            Desktop.getDesktop().browse(indexHtmlFile.toURI());
        } catch (IOException e) {
            System.err.println("Unknow error occurs when trying to launch file in your default browser");
        }
        return 1;
    }

    /**
     *  Retrieves the build folder if it exists. returns null if not found
     * @param siteDirectory  Root of our static site
     * @return  The build folder or null if the build folder does not exist.
     */
    File getBuildFolder(File siteDirectory) {

        File[] files = siteDirectory.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory() && f.getName().equals("build"))
                    return f;
            }
        }
        return null;
    }

    /**
     * Retourne le fichier ou le dossier correspondant au nom passer en paramètre si celui si se trouve dans le dossier
     * @param rootFolder : Dossier où on cherche le fichier ou le dossier
     * @param fileName : Nom du fichier ou dossier à cherche
     * @return le fichier ou le dossier concerné ou null si pas trouvé
     */
//    private File getFileOrFolderWithGivenName(File rootFolder, String fileName) {
//        File[] files = rootFolder.listFiles();
//        if (files != null) {
//            for (File f : files) {
//                if ((f.isFile() || f.isDirectory()) && f.getName().equals(fileName))
//                    return f;
//            }
//        }
//        return null;
//    }

    /**
     * Returns the index.html file if it exists or null if it does not exist.
     * @param BuildDirectory : The build folder where we look for the index.html file
     * @return The index.html file or null if not found
     */
    File getIndexHtmlFile(File BuildDirectory) {
        File[] files = BuildDirectory.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isFile() && f.getName().equals("index.html"))
                    return f;
            }
        }
        return null;
    }
}