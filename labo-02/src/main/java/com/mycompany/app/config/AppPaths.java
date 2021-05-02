package com.mycompany.app.config;

/**
 *
 */
public enum AppPaths {

    /**  */
    BUILD("\\build"),
    CONTENT("\\content"),
    TEMPLATE("\\template");

    /**  */
    private final String path;

    /**
     * Constructeur
     * @param path
     */
    AppPaths(String path) {
        this.path = path;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return path;
    }
}
