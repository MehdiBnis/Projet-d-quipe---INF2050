package equipe5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

/**
 *
 * Cette classe lit et affiche un fichier texte
 *
 * @authors Victor Poirier, Thomas Primeau, Gabriella Farallo et Mehdi Bennis
 * @version Mars 2023
 *
 * Code pris d'atelier 1 du cours INF2050.
 */
public class FileIO {

    public static String readFileToString(String filename) throws IOException {
        String result = "";
        try {
            result = Files.readString(Paths.get(filename));
        } catch (NoSuchFileException exception){
            System.out.println(filename + " n'est pas un chemin de fichier valide.");
            System.exit(-1);
        }
        return result;
    }

    public static void writeStringToFile(String filePath, String content) throws IOException {
        Files.writeString(Paths.get(filePath), content);
    }
}