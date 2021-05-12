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
    public Integer call() throws IOException {
        initializeApp();
        return 1;
    }

    /**
     * Create base folders and files
     */
    private void initializeApp() throws IOException {


        String pathInitFolder = "";

        //QUESTION: IL faut aussi prevoir "\" (linux) ??
        String[] folders = userPath.split("/");

        try {

            for (int i =0; i < folders.length; ++i) {
                pathInitFolder += folders[i];
                File file = new File(pathInitFolder);
                if (!file.exists()) {
                    file.mkdir();
                }
                if(i != folders.length - 1)
                    pathInitFolder += "\\";
            }

            File configFile = new File(pathInitFolder + "\\config.json");
            File indexFile = new File(pathInitFolder + "\\index.md");


            // Content folder and files
            File folderContent = new File(pathInitFolder + CONTENT);
            if (!folderContent.exists())
                folderContent.mkdir();

            File pageFile = new File(folderContent.getAbsolutePath() + "\\page.md");
            File imageFile = new File(folderContent.getAbsolutePath() + "\\image.png");

            // Template folder and files
            File folderTemplate = new File(pathInitFolder + TEMPLATE);
            if (!folderTemplate.exists())
                folderTemplate.mkdir();

            File menuIndexFile = new File(folderTemplate.getAbsolutePath() + "\\menuIndex.html");
            File menuPageFile = new File(folderTemplate.getAbsolutePath() + "\\menuPage.html");
            File layoutIndexFile = new File(folderTemplate.getAbsolutePath() + "\\layoutIndex.html");
            File layoutPageFile = new File(folderTemplate.getAbsolutePath() + "\\layoutPage.html");

            try {//WRITING

                //index.md
                FileWriter myWriter = new FileWriter(indexFile);
                myWriter.write(indexTemplate);
                myWriter.close();
                //page.md
                myWriter = new FileWriter(pageFile);
                myWriter.write(pageTemplate);
                myWriter.close();
                //config.json
                myWriter = new FileWriter(configFile);
                myWriter.write(configJSON);
                myWriter.close();
                //layoutIndex.html
                myWriter = new FileWriter(layoutIndexFile);
                myWriter.write(layoutIndexTemplate);
                myWriter.close();
                //layoutPage.html
                myWriter = new FileWriter(layoutPageFile);
                myWriter.write(layoutPageTemplate);
                myWriter.close();
                //menuIndex.html
                myWriter = new FileWriter(menuIndexFile);
                myWriter.write(menuIndexTemplate);
                myWriter.close();
                //menuPage.html
                myWriter = new FileWriter(menuPageFile);
                myWriter.write(menuPageTemplate);
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