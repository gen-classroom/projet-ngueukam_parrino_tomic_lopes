package ch.heigvd.statique;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

public class StatiqueTest {

  private final ByteArrayOutputStream output = new ByteArrayOutputStream();

  @BeforeEach
  void setUp() {
    System.setOut(new PrintStream(output));
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  public void result() throws Exception {
    int exitCode = new CommandLine(new Statique()).execute();
    assertEquals(exitCode, 0);
    assertThrows(Exception.class, () -> {
      throw new Exception();
    });
  }

}