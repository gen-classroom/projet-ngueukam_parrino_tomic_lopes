package ch.heigvd.statique.config;

/**
 * Names of the folders that constitute the static site
 */
public enum AppPaths {

    /** Folders  */
    BUILD("\\build"),
    CONTENT("\\content"),
    TEMPLATE("\\template");

    /** Folder path */
    private final String path;

    /**
     * Constructeur
     * @param path folder path
     */
    AppPaths(String path) {
        this.path = path;
    }

    /** Method toString */
    @Override
    public String toString() {
        return path;
    }
}
