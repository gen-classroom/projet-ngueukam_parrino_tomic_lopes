package ch.heigvd.statique.commands;

import picocli.CommandLine;

import java.util.concurrent.Callable;

/**
 * Class representing the version command,
 * allows you to see the current version
 */
@CommandLine.Command(name = "-version", description = "Retourne la version du programme.")
public class Version implements Callable<Integer> {

    private final String VERSION = "0.0.2";

    /**
     * Function called when the "-version" command is invoked
     */
    @Override
    public Integer call() {
        System.out.println("version : " + VERSION);
        return 1;
    }
}