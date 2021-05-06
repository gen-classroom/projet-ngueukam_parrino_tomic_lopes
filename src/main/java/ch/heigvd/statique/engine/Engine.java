package ch.heigvd.statique.engine;
import ch.heigvd.statique.config.AppConfiguration;
import ch.heigvd.statique.entities.Metadata;
import freemarker.template.*;
import java.util.*;
import java.io.*;

/**
 *
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
        // Recommended settings for new projects:
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);

    }

    /**
     *
     * @param metadata
     */
    public void addMetadata(Metadata metadata){ root.put("page", metadata); }

    /**
     *
     * @param config
     */
    public void addConfiguration(AppConfiguration config){ root.put("site", config); }

    /**
     *
     * @param content
     */
    public void addContent(ArrayList<String> content ){ root.put("content", content); }

    /**
     *
     * @param path
     * @throws Exception
     */
    public void write(String path) throws Exception {

        /* Get the template (uses cache internally) */
        Template temp = cfg.getTemplate("layout.html");

        /* Merge data-model with template */
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        temp.process(root, writer);
        writer.close();
    }

}
