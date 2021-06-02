package ch.heigvd.statique.config;

/**
 * Class represent the configuration of the static site
 */
public class AppConfiguration {
    private final String domain;
    private final String title;

    /**
     * Constructeur
     * @param domain site domain
     * @param title site name
     */
    public AppConfiguration(String domain, String title) {
        this.domain = domain;
        this.title = title;
    }

    /** Getter domain */
    public String getDomain() {
        return domain;
    }


    /** Getter title */
    public String getTitle() {
        return title;
    }

}
