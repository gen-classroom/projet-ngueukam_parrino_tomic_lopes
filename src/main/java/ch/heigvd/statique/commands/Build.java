package ch.heigvd.statique.commands;

import com.google.gson.Gson;
import ch.heigvd.statique.config.AppConfiguration;
import ch.heigvd.statique.engine.Engine;
import ch.heigvd.statique.entities.Metadata;
import ch.heigvd.statique.parser.MDParser;
import picocli.CommandLine;
import java.io.*;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import static ch.heigvd.statique.config.AppPaths.*;

/**
 * Class representing the clean command,
 * allows you to build your static site
 */
@CommandLine.Command(name = "build", description = "Build project")
public class Build implements Callable<Integer>, Executable {

    //Get user path
    @CommandLine.Parameters(index = "0")
    String userPath;

    @CommandLine.Option(names = "--watch")
    boolean option;

    /**
     * Function called when the "clean" command is invoked
     * @throws Exception
     */
    @Override
    public Integer call() throws Exception {
        if (option) {
            WatchOption watchOption = new WatchOption(userPath, "build");
            watchOption.startWatch(this::execute);
        }
        else {
            execute();
        }
        return 1;
    }

    /**
     * Create base folder and files
     */
    private void initBuild() {

        // Get Path
        String path = userPath;
        try {

            path += BUILD;
            // Create Folders and files for build command
            new File(path).mkdir();
            new File(path +"/index.html").createNewFile();

            path += "\\content";
            new File(path).mkdir();

            new File(path + "\\page.html").createNewFile();
            new File(path + "\\image.png").createNewFile();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    /**
     * Injects all pages of the static site
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {

        initBuild();

        injectFile("index", "layoutIndex.html");
        injectFile(CONTENT + "\\page", "layoutPage.html");

    }

    /**
     * Insert data extracted from markdown files into html templates
     * @param filePath name of the file to be created without extension (with relative path)
     * @param layout file template
     */
    private void injectFile(String filePath, String layout) throws Exception {

        Engine engine = new Engine(userPath + TEMPLATE);
        MDParser parser = new MDParser(userPath + "\\" + filePath + ".md");

        // Get Metadata
        Metadata meta = parser.getMetadataObject();
        // Get Content
        ArrayList<String> content = parser.getResultHTML();
        // Get Configuration
        AppConfiguration config = createConfigObject();

        // Pass the site information to the template engine
        engine.setMetadata(meta);
        engine.setContent(content);
        engine.setConfiguration(config);

        engine.write(userPath + BUILD  + "\\" + filePath + ".html", layout);
    }

    /**
     * Converts configuration file (json) into Java object of type AppConfiguration
     * @return AppConfiguration object
     */
    private AppConfiguration createConfigObject(){
        Gson gson = new Gson();
        Reader reader = null;
        try {
            reader = new FileReader(userPath + "/config.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert reader != null;

        return gson.fromJson(reader, AppConfiguration.class);
    }

}