package ch.heigvd.statique.engine;
import ch.heigvd.statique.config.AppConfiguration;
import ch.heigvd.statique.entities.Metadata;
import freemarker.template.*;
import java.util.*;
import java.io.*;

/**
 * Class representing the templating engine,
 * uses Freemarker for file injection
 */
public class Engine {

    /** Create a data-model */
    final Map<String, Object> root = new HashMap<>();

    /** Create and adjust the configuration singleton */
    final Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);

    /**
     * Constructeur
     * @param path
     * @throws Exception
     */
    public Engine(String path) throws Exception {

        cfg.setDirectoryForTemplateLoading(new File(path));
        // Recommended settings for new projects
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);

    }

    /** Setter metadata */
    public void setMetadata(Metadata metadata){ root.put("page", metadata); }

    /** Setter configuration */
    public void setConfiguration(AppConfiguration config){ root.put("site", config); }

    /** Setter content */
    public void setContent(ArrayList<String> content ){ root.put("content", content); }

    /**
     * Processes the merge between the data model and the template
     * @param path directory del file
     * @param layout base template
     * @throws Exception
     */
    public void write(String path, String layout) throws Exception {

        /* Get the template (uses cache internally) */
        Template temp = cfg.getTemplate(layout);

        /* Merge data-model with template */
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        temp.process(root, writer);
        writer.close();
        root.clear();//Reset map when finished
    }

}
