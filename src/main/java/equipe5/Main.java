package equipe5;

import java.io.IOException;

import static equipe5.Stats.storeStats;

/**
 * Cette classe modélise une application qui génère un .jar
 *
 * @authors Victor Poirier, Thomas Primeau, Gabriella Farallo & Mehdi Bennis
 * @version Mars 2023
 */
public class Main {
    public static void main(String[] args) throws RefundException, IOException {
        if (args.length > 1 && args.length < 4) {
            String inputFilePath = Arguments.getParams(args)[0];
            String outputFilePath = Arguments.getParams(args)[1];
            Stats stats = new Stats("Stats");
            if (Arguments.getOption(args).equals("-SR")) {
                stats.reinitialiserStats();
            }
            ClientReport clientReport = new ClientReport(inputFilePath);
            try {
                FileIO.writeStringToFile(outputFilePath, clientReport.toString());
                if (Arguments.getOption(args).equals("-S") || Arguments.getOption(args).equals("-SR")) {
                    FileIO.writeStringToFile("Stats",storeStats(stats));
                    System.out.println(stats);
                }
            } catch (IOException e) {
                exit("Erreur, écriture sur le fichier impossible.");
            }
        } else {
            exit("Erreur, nombre d'arguments invalide.");
        }
    }

    static void exit(String message) {
        System.out.println(message);
        System.exit(-1);
    }
}