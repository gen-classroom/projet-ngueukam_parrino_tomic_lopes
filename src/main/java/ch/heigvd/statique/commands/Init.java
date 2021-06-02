package ch.heigvd.statique.commands;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;
import picocli.CommandLine;

import static ch.heigvd.statique.config.AppPaths.CONTENT;
import static ch.heigvd.statique.config.AppPaths.TEMPLATE;
import static ch.heigvd.statique.config.AppTemplates.*;
import static ch.heigvd.statique.utils.Utils.writeFile;

/**
 * Class representing the init command,
 * allows to create a basic project for a static web site
 */
@CommandLine.Command(name = "init", description = "Initialise projet")
public class Init implements Callable<Integer> {

    @CommandLine.Parameters(index = "0")
    String userPath;

    /**
     * Function called when the "init" command is invoked
     * @return Returns 1 if the project was correctly created,
     *         Returns -1 if an error occurred during the creation or writing of the files
     */
    @Override
    public Integer call() {
        return initializeApp();
    }

    /**
     * Create base folders and files
     * and writes the basic content
     */
    private Integer initializeApp() {

        String pathInitFolder = "";

        String[] folders = userPath.split("/");

        try {

            for (int i = 0; i < folders.length; ++i) {
                pathInitFolder += folders[i];
                File file = new File(pathInitFolder);
                if (!file.exists()) {
                    file.mkdir();
                }
                if (i != folders.length - 1)
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

            try {//WRITING FILES

                //index.md
                writeFile(indexFile, indexTemplate);
                //page.md
                writeFile(pageFile, pageTemplate);
                //config.json
                writeFile(configFile, configJSON);
                //layoutIndex.html
                writeFile(layoutIndexFile, layoutIndexTemplate);
                //layoutPage.html
                writeFile(layoutPageFile, layoutPageTemplate);
                //menuIndex.html
                writeFile(menuIndexFile, menuIndexTemplate);
                //menuPage.html
                writeFile(menuPageFile, menuPageTemplate);
                //image.png
                writeFile(imageFile, "");

            } catch (IOException e) {
                System.out.println("An error occurred when file writing.");
                e.printStackTrace();
                return -1;
            }

        } catch (Exception e) {
            System.out.println("An error occurred in files creations");
            e.printStackTrace();
            return -1;
        }
        return 1;
    }
}