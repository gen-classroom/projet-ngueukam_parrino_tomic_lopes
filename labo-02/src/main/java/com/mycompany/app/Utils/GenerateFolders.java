package com.mycompany.app.Utils;

import java.io.File;
import java.io.IOException;

public class GenerateFolders {
    String initFolder;
    public GenerateFolders() {
        this.initFolder = "/mon/site";
    }

    public void generatedFiles () {
        String pathInitFolder = System.getProperty("user.dir");
        String[] folders = initFolder.split("/");
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
        try{
            if (file.createNewFile()){
                System.out.println(file.getAbsolutePath());
                System.out.println("File page.md créé");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
