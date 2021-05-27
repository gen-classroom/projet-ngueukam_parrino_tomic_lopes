package ch.heigvd.statique;

import ch.heigvd.statique.commands.Build;
import ch.heigvd.statique.commands.Clean;
import ch.heigvd.statique.commands.Init;
import ch.heigvd.statique.commands.Serve;
import java.util.concurrent.Callable;
import picocli.CommandLine;
import picocli.CommandLine.Command;

/**
 *
 */
@Command(
    name = "statique",
    description = "A brand new static site generator.",
    subcommands = {Init.class, Clean.class, Build.class, Serve.class})
public class Statique implements Callable<Integer> {

  /**
   *
   * @param args
   */
  public static void main(String... args) {
    int exitCode = new CommandLine(new Statique()).execute(args);
    System.exit(exitCode);
  }

  /**
   *
   * @return
   * @throws Exception
   */
  @Override
  public Integer call() throws Exception {
    CommandLine.usage(this, System.out);
    return 0;
  }
}
