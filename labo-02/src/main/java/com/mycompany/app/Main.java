import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;


@Command(name = "Main", mixinStandardHelpOptions = true)
class Main implements Callable<Integer> {

    @Override
    public Integer call() {
        System.out.println("Subcommand needed: 'new', 'clean', 'build' or 'serve'");
        return 0;
    }

    public static void main(String... args) {
        int rc = new CommandLine(new Main()).execute(args);
        System.exit(rc);
    }
}
