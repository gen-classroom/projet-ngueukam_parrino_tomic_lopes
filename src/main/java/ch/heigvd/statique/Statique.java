package ch.heigvd.statique;

import ch.heigvd.statique.commands.*;

import java.util.concurrent.Callable;
import picocli.CommandLine;
import picocli.CommandLine.Command;

/**
 * Main application
 */
@Command(
    name = "statique",
    description = "A brand new static site generator.",
    subcommands = {Init.class, Clean.class, Build.class, Serve.class, Version.class})
public class Statique implements Callable<Integer> {

  /**
   * Executes the command passed in argument
   * @param args static site directory
   */
  public static void main(String... args) {
    int exitCode = new CommandLine(new Statique()).execute(args);
    System.exit(exitCode);
  }

  /**
   * Show available commands
   */
  @Override
  public Integer call() {
    CommandLine.usage(this, System.out);
    return 0;
  }
}
