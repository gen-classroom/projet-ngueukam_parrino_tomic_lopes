import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "new", description = "new")
class New implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("new");
        return 1;
    }
}


@Command(name = "Main", description = "Laboratoire du cours de génie logicielle", subcommands = { New.class})
class Main implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        CommandLine.usage(this, System.out);
        return 0;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }
}
