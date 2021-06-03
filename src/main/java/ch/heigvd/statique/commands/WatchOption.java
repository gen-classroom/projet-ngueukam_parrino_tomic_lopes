package ch.heigvd.statique.commands;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WatchOption {
    private Path pathFolderToWatch;
    private WatchService watchService;

    WatchOption(String pathFolderToWatch, String ... excludeFolders) throws IOException {
        watchService = FileSystems.getDefault().newWatchService();
        this.pathFolderToWatch = Paths.get(pathFolderToWatch);
//        System.out.println("Watching folder : " + pathFolderToWatch);
        // Liste des dossiers à exclure sous forme de tableau
        List<String> listExcludeFolder = Arrays.stream(excludeFolders).distinct().collect(Collectors.toList());

        // On enregistre tous les sous-dossiers compris dans le dossier qu'on regarde
        Files.walkFileTree(this.pathFolderToWatch, new SimpleFileVisitor<>() {
           @Override
           public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
               // On ignore les dossiers pas nécessaires donnés à la fonction
               if (listExcludeFolder.contains(dir.getFileName().toString()))
                   return FileVisitResult.SKIP_SUBTREE;
               dir.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
               return FileVisitResult.CONTINUE;
           }
        });
    }

    void startWatch(Executable toExecute) throws Exception {
        WatchKey key;
        boolean update = false;
        toExecute.execute();
        while (((key = watchService.take()) != null)) {
            for (WatchEvent<?> event : key.pollEvents()) {
                // On ignore les fichiers temporaires créés par windows lors de la modification d'un fichier.
                update = event.context().toString().charAt(event.context().toString().length() - 1) != '~';
            }
            if (update) {
                toExecute.execute();
                update = false;
            }
            key.reset();
        }
    }
}
