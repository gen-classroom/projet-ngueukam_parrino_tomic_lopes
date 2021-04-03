package com.mycompany.app.commands;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Callable;
import picocli.CommandLine;


@CommandLine.Command(name = "init", description = "Initialise projet")
public class Init implements Callable<Integer> {

    @CommandLine.Parameters(index = "0")
    String userPath;

    @Override
    public Integer call() {
        initFolder();
        return 1;
    }

    public void initFolder() {

        String pathInitFolder = System.getProperty("user.dir");

        String[] folders = userPath.split("/");

        File file = new File(pathInitFolder + "/");
        for (int i = 0; i < folders.length; i++) {
            file = new File(pathInitFolder + "/" + folders[i]);
            if (!file.exists()) {
                file.mkdir();
            }
            pathInitFolder += "/" + folders[i];
        }

        File configFile = new File(file.getAbsolutePath() + "/config.json");
        File indexFile = new File(file.getAbsolutePath() + "/index.md");

        String indexPage =
                "titre: Mon premier article\n" +
                        "auteur: Nom Prenom\n" +
                        "date: YY-MM-DD\n" +
                        "---\n" +
                        "# Mon premier article\n" +
                        "## Mon sous-titre\n" +
                        "le contenu de mon article\n"
                        + "![Une image](./image.png)\n";
        try {
            FileWriter myWriter = new FileWriter(indexFile);
            myWriter.write(indexPage);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            if (configFile.createNewFile()) {
                System.out.println(configFile.getAbsolutePath());
            }
            if (indexFile.createNewFile()) {
                System.out.println(indexFile.getAbsolutePath());
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        file = new File(file.getAbsolutePath() + "/dossier");
        if (!file.exists())
            file.mkdir();

        file = new File(file.getAbsolutePath() + "/page.md");
        try {
            if (file.createNewFile()) {
                System.out.println(file.getAbsolutePath());
                System.out.println("File page.md créé");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


}