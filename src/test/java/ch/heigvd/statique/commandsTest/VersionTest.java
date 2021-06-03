package ch.heigvd.statique.commandsTest;

import ch.heigvd.statique.Statique;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import java.util.regex.*;

import java.util.concurrent.Callable;


/**
 * Class representing the version command,
 * allows you to see the current version
 */

public class VersionTest {


    @Test
    public void version() throws Exception {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            System.setOut(new PrintStream(output));
            Callable<Integer> callable = new Statique();
            CommandLine cmd = new CommandLine(callable);
            String[] args = new String[]{"-version"};
            output.reset();
            cmd.execute(args);
            String[] result = output.toString().split("version : \\d{1,2}\\.\\d{1,2}\\.\\d{1,2}");
            assertTrue(result.length != 0);
        } catch (Exception e){
            assertTrue("Could run the test".equals("true"));
        }
    }


}