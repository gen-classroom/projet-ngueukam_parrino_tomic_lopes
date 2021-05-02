package com.mycompany.app.config;

/**
 *
 */
public class AppConfiguration {
    private final String domain;
    private final String title;

    /**
     * Constructeur
     * @param domain
     * @param title
     */
    public AppConfiguration(String domain, String title) {
        this.domain = domain;
        this.title = title;
    }

    /**
     *
     * @return
     */
    public String getDomain() {
        return domain;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

}
