package ch.heigvd.statique.parser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Class used for parse page.md to html
 * Markdown file should contain only:
 * - Headers h1 to h6
 * - Paragraph
 * - Images
 */
public class MDParser {
    final ArrayList<String> metadata = new ArrayList<>();
    final ArrayList<String> content = new ArrayList<>();
    final ArrayList<String> resultHTML = new ArrayList<>();

    /**
     * Constructeur
     * @param path
     */
    public MDParser(String path){
        getDataFromMD(path);
        mdToHTML();
    }

    /**
     * Separe metadata and content of page.md and store into ArrayList<String>
     */
    private void getDataFromMD(String path) {
        boolean isContent = false;
        BufferedReader bufferedReader = null; // buffered for readLine()

        try {
            String line;
            bufferedReader = new BufferedReader(new FileReader(path,StandardCharsets.UTF_8));
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("---")) {
                    isContent = true;
                    continue;
                }

                if(isContent)
                    content.add(line);
                else
                    metadata.add(line);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage()); // handle exception
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Parse md to HTML and store into ArrayList<String>
     */
    private void mdToHTML(){

        // Regex used for check that image String is valid
        String imageRegex = "!\\[[^\\]]*?\\]\\([^)]+\\)";

        // Create a pattern from regex
        Pattern pattern = Pattern.compile(imageRegex);

        resultHTML.add(0,"\n");

        for (String s : content) {

            // Create a matcher for the input String
            Matcher matcher = pattern.matcher(s);

            if (s.charAt(0) == '#') {// Check headers
                if (s.substring(0, 6).contains(MarkdownSymbols.H5))
                    resultHTML.add("<h6>" + s.substring(7) + "</h6>\n");

                else if (s.substring(0, 5).contains(MarkdownSymbols.H5))
                    resultHTML.add("<h5>" + s.substring(6) + "</h5>\n");

                else if (s.substring(0, 4).contains(MarkdownSymbols.H4))
                    resultHTML.add("<h4>" + s.substring(5) + "</h4>\n");

                else if (s.substring(0, 3).contains(MarkdownSymbols.H3))
                    resultHTML.add("<h3>" + s.substring(4) + "</h3>\n");

                else if (s.substring(0, 2).contains(MarkdownSymbols.H2))
                    resultHTML.add("<h2>" + s.substring(3) + "</h2>\n");

                else if (s.substring(0, 1).contains(MarkdownSymbols.H1))
                    resultHTML.add("<h1>" + s.substring(2) + "</h1>\n");
            }
            else if (matcher.lookingAt()) { //Check img
                int startAlt = 2;
                int endAlt = s.indexOf(']');
                int startUrl = s.indexOf('(') + 1;
                int endUrl = s.indexOf(')');
                resultHTML.add("<img alt=\"" + s.substring(startAlt, endAlt) +
                        "\" src=\"" + s.substring(startUrl, endUrl) + "\" >");
            } else {//Check p
                resultHTML.add("<p>" + s + "</p>\n");
            }

        }
    }

    /** Getter metadata */
    public ArrayList<String> getMetadata() {
        return metadata;
    }
    /** Getter html result */
    public ArrayList<String> getResultHTML() {
        return resultHTML;
    }


}
