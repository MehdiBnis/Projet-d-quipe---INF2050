package equipe5;

import java.io.IOException;
import java.util.Arrays;

public class Stats {

    // nbReclam[0] : nombre de réclamations valides traitées
    // nbReclam[1] : nombre de réclamations rejetées
    static int[] nbReclam = {0, 0};

    /**
     * nbSoins[0] : nombre de soins massothérapie
     * nbSoins[1] : nombre de soins ostéopathie
     * nbSoins[2] : nombre de soins kinésithérapie
     * nbSoins[3] : nombre de soins d'un médecin généraliste privé
     * nbSoins[4] : nombre de soins de psychologie individuelle
     * nbSoins[5] : nombre de soins dentaires
     * nbSoins[6] : nombre de soins de naturopathie et d'acuponcture
     * nbSoins[7] : nombre de soins de chiropratie
     * nbSoins[8] : nombre de soins de physiothérapie
     * nbSoins[9] : nombre de soins d'orthophonie et d'ergothérapie
     */

    static int[] nbSoins = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public Stats(String filename) throws IOException {
        int[] x = getStatsFromFile(filename);
        nbReclam[0] = x[0];
        nbReclam[1] = x[1];
        for (int i = 0; i < nbSoins.length; i++){
            nbSoins[i] = x[i+2];
        }
    }

    public static String storeStats(Stats stats) throws IOException {
        String temp = "";
        for (int i = 0; i < nbReclam.length; i++) {
            temp = temp + nbReclam[i] + " ";
        }
        for (int j = 0; j < nbSoins.length; j++) {
            temp = temp + nbSoins[j] + " ";
        }
        return temp;
    }

    public void reinitialiserStats() {
        Arrays.fill(nbReclam, 0);
        Arrays.fill(nbSoins, 0);
        System.out.println("\nLes statistiques précédemment accumulées viennent d'être réinitialisées.\n\n");
    }

    public int[] getStatsFromFile(String filename) throws IOException {
        String stats = FileIO.readFileToString(filename);
        String[] stringArray = stats.split(" ");
        int[] intArray = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            intArray[i] = Integer.parseInt(stringArray[i]);
        }
        return intArray;
    }

    public static void incrementerReclam(int reclamIndex){
        nbReclam[reclamIndex]++;
    }

    public static void incrementerSoin(int reclamIndex){
        nbSoins[reclamIndex]++;
    }

    public String toString() {
        return "Voici les statistiques des demandes d'assurances précédentes :" +
                "\n-------------------------------------------------------------------------------------------------" +
                "\n\n  Réclamations valides : " + nbReclam[0] + "." +
                "\n  Réclamations rejetées : " + nbReclam[1] + "." +
                "\n\n  Réclamations de massothérapie : " + nbSoins[0] + "." +
                "\n  Réclamations d'ostéopathie : " + nbSoins[1] + "." +
                "\n  Réclamations de kinésithérapie : " + nbSoins[2] + "." +
                "\n  Réclamations de soins d'un médecin généraliste privé : " + nbSoins[3] + "." +
                "\n  Réclamations de psychologie individuelle : " + nbSoins[4] + "." +
                "\n  Réclamations de soins dentaires : " + nbSoins[5] + "." +
                "\n  Réclamations de naturopathie et d'acuponcture : " + nbSoins[6] + "." +
                "\n  Réclamations de chiropratie : " + nbSoins[7] + "." +
                "\n  Réclamations de physiothérapie : " + nbSoins[8] + "." +
                "\n  Réclamations d'orthophonie et d'ergothérapie : " + nbSoins[9] + "." +
                "\n\n-------------------------------------------------------------------------------------------------";
    }
}
