package ch.heigvd.statique.commands;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Callable;
import picocli.CommandLine;

import static ch.heigvd.statique.config.AppPaths.CONTENT;
import static ch.heigvd.statique.config.AppPaths.TEMPLATE;
import static ch.heigvd.statique.config.AppTemplates.*;

/**
 *
 */
@CommandLine.Command(name = "init", description = "Initialise projet")
public class Init implements Callable<Integer> {

    @CommandLine.Parameters(index = "0")
    String userPath;

    @Override
    public Integer call() {
        initializeApp();
        return 1;
    }

    /**
     * Create base folders and files
     */
    private void initializeApp() {


        String pathInitFolder = "";

        //QUESTION: IL faut aussi prevoir "\" (linux) ??
        String[] folders = userPath.split("/");

        try {

            // Create folder passed in arguments (Ex. mon/site/)
            File file = new File(pathInitFolder + "\\");
            for (String folder : folders) {
                file = new File(pathInitFolder + "\\" + folder);
                if (!file.exists()) {
                    file.mkdir();
                }
                pathInitFolder += "/" + folder;
            }

            File configFile = new File(file.getAbsolutePath() + "/config.json");
            File indexFile = new File(file.getAbsolutePath() + "/index.md");


            // Content folder and files
            File folderContent = new File(pathInitFolder + CONTENT);
            if (!folderContent.exists())
                folderContent.mkdir();

            File pageFile = new File(folderContent.getAbsolutePath() + "/page.md");
            File imageFile = new File(folderContent.getAbsolutePath() + "/image.png");

            // Template folder and files
            File folderTemplate = new File(pathInitFolder + TEMPLATE);
            if (!folderTemplate.exists())
                folderTemplate.mkdir();

            File menuFile = new File(folderTemplate.getAbsolutePath() + "/menu.html");
            File layoutFile = new File(folderTemplate.getAbsolutePath() + "/layout.html");

            try {//WRITING

                //page.md
                FileWriter myWriter = new FileWriter(indexFile);
                myWriter.write("indexTemplate");
                myWriter.close();

                //page.md
                myWriter = new FileWriter(pageFile);
                myWriter.write(pageTemplate);
                myWriter.close();
                //config.json
                myWriter = new FileWriter(configFile);
                myWriter.write(configJSON);
                myWriter.close();
                //layout.html
                myWriter = new FileWriter(layoutFile);
                myWriter.write(layoutTemplate);
                myWriter.close();
                //menu.html
                myWriter = new FileWriter(menuFile);
                myWriter.write(menuTemplate);
                myWriter.close();
                //image.png
                myWriter = new FileWriter(imageFile);
                myWriter.write("");
                myWriter.close();


                System.out.println("Successfully wrote to the file.");

            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        } catch( Exception e) {
            System.out.println("An error occurred in files creations");
        }




    }

}