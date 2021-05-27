package ch.heigvd.statique.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Utils {

    /**
     * Write String content to file
     * @param file the file
     * @param content the String content
     * @throws IOException if an error occurs when writing.
     */
    public static void writeFile(File file, String content) throws IOException {
        FileWriter myWriter = new FileWriter(file);
        myWriter.write(content);
        myWriter.close();
    }
}
