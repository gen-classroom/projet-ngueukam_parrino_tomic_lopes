package com.mycompany.app.commands;

import com.mycompany.app.parser.ParserHTML;
import picocli.CommandLine;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "build", description = "Build project")
public class Build implements Callable<Integer> {
    //Get user path
    @CommandLine.Parameters(index = "0")
    String userPath;

    @Override
    public Integer call() throws Exception {
        buildProject();
        return 1;
    }

    void initBuild() {

        // Get Path
        String path = System.getProperty("user.dir") + userPath;

        try {
            // Create Folders
            new File(path + "/build").mkdir();
            new File(path + "/build/dossier").mkdir();

            // Create Files
            new File(path + "/build/index.html").createNewFile();
            new File(path + "/build/dossier/page.html").createNewFile();
            new File(path + "/build/dossier/image.png").createNewFile();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    void buildProject() throws IOException {

        // Get Path
        String path = System.getProperty("user.dir") + userPath;

        initBuild();

        ArrayList<String> result = new ArrayList<>();

        ParserHTML parser = new ParserHTML();

        result = parser.toHTML(path + "/index.md");

        FileWriter writer = new FileWriter(path + "/build/index.html");
        for (String str : result) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();

    }

}