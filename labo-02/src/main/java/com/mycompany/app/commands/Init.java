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

        String indexTemplate = "";
        String pageTemplate =
                "titre: Mon premier article\n" +
                        "auteur: Nom Prenom\n" +
                        "date: YY-MM-DD\n" +
                        "---\n" +
                        "# Mon premier article\n" +
                        "## Mon sous-titre\n" +
                        "le contenu de mon article\n"
                        + "![Une image](./image.png)\n";

        String configData = "{\n" + "\"domaine\": \"www.mon-site.com\"\n" + "\"titre\": \"\"Mon site\"\"\n" + "}";

        String layoutData = "<html lang=\"en\">\n" +
                            "<head>\n" +
                            "\t<meta charset=\"utf-8\">\n" +
                            "\t<title>{{ site.titre }} | {{ page.titre }}</title>\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "\t{% include menu.html }\n" +
                            "\t{{ content }}\n" +
                            "</body>\n" +
                            "</html>";

        String menuData = "<ul>\n" +
                          "\t<li><a href=\"/index.html\">home</a></li>\n" +
                          "\t<li><a href=\"/content/page.html\">page</a></li>\n" +
                          "</ul>";

        String imageData = "";

        String pathInitFolder = System.getProperty("user.dir");

        String[] folders = userPath.split("/");

        try {
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

            String path = file.getAbsolutePath();

            File folderContent = new File(path + "/content");
            if (!folderContent.exists())
                folderContent.mkdir();

            File pageFile = new File(folderContent.getAbsolutePath() + "/page.md");


            File imageFile = new File(folderContent.getAbsolutePath() + "/image.png");


            File folderTemplate = new File(path + "/template");
            if (!folderTemplate.exists())
                folderTemplate.mkdir();

            File menuFile = new File(folderTemplate.getAbsolutePath() + "/menu.html");


            File layoutFile = new File(folderTemplate.getAbsolutePath() + "/layout.html");

            try {//WRITING

                //page.md
                FileWriter myWriter = new FileWriter(indexFile);
                myWriter.write(indexTemplate);
                myWriter.close();

                //page.md
                myWriter = new FileWriter(pageFile);
                myWriter.write(pageTemplate);
                myWriter.close();
                //config.json
                myWriter = new FileWriter(configFile);
                myWriter.write(configData);
                myWriter.close();
                //layout.html
                myWriter = new FileWriter(layoutFile);
                myWriter.write(layoutData);
                myWriter.close();
                //menu.html
                myWriter = new FileWriter(menuFile);
                myWriter.write(menuData);
                myWriter.close();
                //image.png
                myWriter = new FileWriter(imageFile);
                myWriter.write(imageData);
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