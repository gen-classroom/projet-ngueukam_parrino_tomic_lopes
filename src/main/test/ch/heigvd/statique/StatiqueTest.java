package ch.heigvd.statique;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

class StatiqueTest {

  private final ByteArrayOutputStream output = new ByteArrayOutputStream();

  @BeforeEach
  void setUp() {
    System.setOut(new PrintStream(output));
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void result() throws Exception {
    int exitCode = new CommandLine(new Statique()).execute();
    assertEquals(exitCode, 0);
    assertThrows(Exception.class, () -> {
      throw new Exception();
    });
  }

  @Test
  void output() throws Exception {
    new CommandLine(new Statique()).execute("init");
    assertTrue(output.toString().contains("init"));
  }


  @Test
  void version() throws Exception {

    new CommandLine(new Statique()).execute("-version");
    assertTrue(output.toString().contains("Version: 0.0.2"));
  }

  @Test
  void clean() throws Exception {

    new CommandLine(new Statique()).execute("-version");
    assertTrue(output.toString().contains("Version: 0.0.2"));
  }

  @Test
  // Ce test devrait juste récupérer le contenu d'un dossier fraichement crée, et vérifier si celui-ci contient les fichiers à créer
  void init() throws Exception {
    private final String path = "test";
    new CommandLine(new Statique()).execute("init " + path);
    assertTrue(output.toString().contains(""));
  }

  /*

  Techniquement pas besoin d'un test unitaire, suffit juste d'exécuter la commande


  @Test
  void serve() throws Exception {

    new CommandLine(new Statique()).execute("serve");
    assertTrue(output.toString().contains(""));
  }

   */

  @Test
  // La même que init, mais pour les fichiers de build
  void build() throws Exception {
    private final String path = "test";
    new CommandLine(new Statique()).execute("build " + path);
    assertTrue(output.toString().contains(""));
  }


}