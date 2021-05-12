package ch.heigvd.statique.commands;

import com.google.gson.Gson;
import ch.heigvd.statique.config.AppConfiguration;
import ch.heigvd.statique.engine.Engine;
import ch.heigvd.statique.entities.Metadata;
import ch.heigvd.statique.parser.MDParser;
import picocli.CommandLine;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import static ch.heigvd.statique.config.AppPaths.*;

/**
 *
 */
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

    /**
     *
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
     *
     * @throws Exception
     */
    private void buildProject() throws Exception {

        initBuild();

        Engine engine = new Engine(userPath + TEMPLATE);

        MDParser parser = new MDParser(userPath + "\\index.md");
        Metadata meta = createMetadataObject(parser.getMetadata());
        ArrayList<String> content = parser.getResultHTML();
        AppConfiguration config = createConfigObject();

        engine.addMetadata(meta);
        engine.addContent(content);
        engine.addConfiguration(config);

        engine.write(userPath + BUILD + "/index.html", "layoutIndex.html");

        parser = new MDParser(userPath + CONTENT + "\\page.md");
        meta = createMetadataObject(parser.getMetadata());
        content = parser.getResultHTML();
        config = createConfigObject();

        engine.addMetadata(meta);
        engine.addContent(content);
        engine.addConfiguration(config);

        engine.write(userPath + BUILD + CONTENT + "/page.html", "layoutPage.html");
    }

    /**
     *
     * @param metadata
     * @return
     */
    private Metadata createMetadataObject(ArrayList<String> metadata){
        for(int i = 0; i < metadata.size(); ++i) {
            int index = metadata.get(i).indexOf(':');
            metadata.set(i, metadata.get(i).substring(index + 1));//+1 for blank space
        }
        String title = metadata.get(0);
        String author = metadata.get(1);
        String date = metadata.get(2);

        return new Metadata(title,author,date);
    }

    /**
     *
     * @return
     */
    private AppConfiguration createConfigObject(){
        Gson gson = new Gson();
        Reader reader = null;
        try {
             reader = new FileReader(userPath + "/config.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert reader != null; //for warning

        return gson.fromJson(reader, AppConfiguration.class);
    }

}