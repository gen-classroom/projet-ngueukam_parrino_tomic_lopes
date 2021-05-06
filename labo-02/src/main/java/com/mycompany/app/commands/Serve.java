package com.mycompany.app.commands;

import picocli.CommandLine;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

/**
 *
 */
@CommandLine.Command(name = "serve", description = "serve")
public class Serve implements Callable<Integer> {

    @CommandLine.Parameters(index = "0")
    String userPath;

    @Override
    public Integer call() {
//        System.out.println("serve command on " + userPath);
        String path = userPath;
        File root = new File(path);
        // On récupère le dossier build
        File buildDirectory = getBuildFolder(root);
        if (buildDirectory == null) {   // On affiche un message d'erreur si pas trouvé et on interrompt l'exécution
            System.err.println("No Build directory Found. You should make a build command first.");
            return 0;
        }
        // On récupère le fichier index.html depuis le dossier build
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
     * Permet de récupérer le dossier build s'il existe. retourne null si pas trouvé
     * @param siteDirectory : Racine de notre site statique
     * @return : le dossier build ou null si le dossier build n'existe pas.
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
     * Retourne le fichier index.html s'il existe ou null s'il n'existe pas.
     * @param BuildDirectory : Le dossier build ou l'on cherche le fichier index.html
     * @return Le fichier index.html ou null si pas trouvé
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