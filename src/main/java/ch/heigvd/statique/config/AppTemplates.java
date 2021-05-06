package ch.heigvd.statique.config;

/**
 *
 */
public class AppTemplates {

    public static final String pageTemplate =
            "titre: Mon premier article\n" +
            "auteur: Nom Prenom\n" +
            "date: YY-MM-DD\n" +
            "---\n" +
            "# Mon premier article\n" +
            "## Mon sous-titre\n" +
            "le contenu de mon article\n"
            + "![Une image](./image.png)\n";


    public static final String layoutTemplate =
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "\t<meta charset=\"utf-8\">\n" +
            "\t<title> ${site.title} | ${page.title}</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "\t<#include \"menu.html\">\n" +
            "\t<#list content as item> ${item} </#list>\n" +
            "</body>\n" +
            "</html>";

    public static final String menuTemplate =
            "<ul>\n" +
            "\t<li><a href=\"/index.html\">home</a></li>\n" +
            "\t<li><a href=\"/content/page.html\">page</a></li>\n" +
            "</ul>\n";


    public static final String configJSON =
            "{\n" +
            "\"domain\": \"www.mon-site.com\",\n" +
            "\"title\": \"Mon site\"\n" +
            "}";


}
