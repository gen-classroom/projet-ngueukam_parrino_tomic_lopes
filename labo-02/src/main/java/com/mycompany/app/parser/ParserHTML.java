package com.mycompany.app.parser;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Class used for parse .md to html
 * Markdown file should contain only:
 *  - Headers h1 to h6
 *  - Paragraph
 *  - Images
 */
public class ParserHTML {

    ArrayList<String> lines = new ArrayList<>();
    ArrayList<String> result = new ArrayList<>();

    //Get all lines of file
    void getInput(String path ){
        boolean canWrite = false;

        InputStream ins = null; // raw byte-stream
        Reader r = null; // cooked reader
        BufferedReader br = null; // buffered for readLine()
        try {
            String s;
            ins = new FileInputStream(path);
            r = new InputStreamReader(ins, "UTF-8"); // leave charset out for default
            br = new BufferedReader(r);
            while ((s = br.readLine()) != null) {
                if(canWrite)
                    lines.add(s);
                if(s.contains("---"))
                    canWrite = true;
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage()); // handle exception
        }
        finally {
            if (br != null) { try { br.close(); } catch(Throwable t) { /* ensure close happens */ } }
            if (r != null) { try { r.close(); } catch(Throwable t) { /* ensure close happens */ } }
            if (ins != null) { try { ins.close(); } catch(Throwable t) { /* ensure close happens */ } }
        }
    }

    public ArrayList<String> toHTML( String path ) throws IOException {

        //Get all lines of file
        getInput(path);

        // Regex to be checked
        String imageRegex = "!\\[[^\\]]*?\\]\\([^)]+\\)";

        // Create a pattern from regex
        Pattern pattern = Pattern.compile(imageRegex);

        for(String s : lines){

            // Create a matcher for the input String
            Matcher matcher = pattern.matcher(s);

            if(s.charAt(0) == '#') {// Check headers
                if (s.substring(0, 6).contains(MarkdownSymbols.H5))
                    result.add("<h6>" + s.substring(7) + "</h6>");

                else if (s.substring(0, 5).contains(MarkdownSymbols.H5))
                    result.add("<h5>" + s.substring(6) + "</h5>");

                else if(s.substring(0, 4).contains(MarkdownSymbols.H4))
                    result.add("<h4>" + s.substring(5) + "</h4>");

                else if(s.substring(0, 3).contains(MarkdownSymbols.H3))
                    result.add("<h3>" + s.substring(4) + "</h3>");

                else if(s.substring(0, 2).contains(MarkdownSymbols.H2))
                    result.add("<h2>" + s.substring(3) + "</h2>");

                else if(s.substring(0, 1).contains(MarkdownSymbols.H1))
                    result.add("<h1>" + s.substring(2) + "</h1>");
            }
            else if(matcher.lookingAt()){ //Check img
                int startAlt = 2;
                int endAlt = s.indexOf(']') ;
                int startUrl = s.indexOf('(') + 1 ;
                int endUrl   = s.indexOf(')') ;
                result.add("<img alt=\"" + s.substring(startAlt,endAlt) +
                                 "\" src=\""+s.substring(startUrl,endUrl) +"\" >");
            }
            else{//Check p
                result.add("<p>"+ s + "</p>");
            }
        }
        return result;
    }

}
