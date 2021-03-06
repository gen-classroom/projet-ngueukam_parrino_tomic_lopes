package ch.heigvd.statique.entities;

/**
 * Class representing the metadata of an html page
 */
public class Metadata {
    private final String title;
    private final String author;
    private final String date;

    /**
     * Constructor
     * @param title
     * @param author
     * @param date
     */
    public Metadata(String title, String author, String date) {
        this.title = title;
        this.author = author;
        this.date = date;
    }

    /** Getter title */
    public String getTitle() {
        return title;
    }

    /** Getter author */
    public String getAuthor() {
        return author;
    }

    /** Getter date */
    public String getDate() {
        return date;
    }

}
