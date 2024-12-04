package equipe5;

/**
 * Cette classe calcul le montant de remboursement des réclamations d'assurances
 * selon le type de contrat et le type de soin procuré par un client
 *
 * @authors Victor Poirier, Thomas Primeau, Gabriella Farallo et Mehdi Bennis
 * @version Mars 2023
 */
public class Claims {


    static int [][][] myArray = new int [][][]{
            {{25,0,0},{50,4000,0},{90,0,0},{100,8500,0},{15,0,0}},
            {{35,0,25000},{50,5000,25000},{95,0,25000},{100,7500,25000},{25,0,25000}},
            {{0,0,0},{0,0,0},{85,0,0},{100,15000,0},{15,0,0}},
            {{50,0,20000},{75,0,20000},{90,0,20000},{95,0,20000},{25,2000,20000}},
            {{25,0,25000},{100,0,25000},{90,0,25000},{100,10000,25000},{12,0,25000}},
            {{0,0,0},{50,0,0},{90,0,0},{100,0,0},{60,0,0}},
            {{0,0,0},{0,0,0},{90,0,0},{100,6500,0},{25,1500,0}},
            {{25,0,15000},{50,5000,15000},{90,0,15000},{100,0,15000},{30,2000,15000}},
            {{40,0,30000},{100,0,30000},{90,0,30000},{100,10000,30000},{15,0,30000}},
            {{0,0,0},{70,0,0},{75,0,0},{100,9000,0},{22,0,0}}
    };

    /**
     * Cette méthode retourne l'index du tableau myArray selon le numéro de soin et -1 si le soin n'existe pas
     * @param number            le numéro de soin correspondant
     * @return x                l'index du premier tableau de soins (de tableaux de ABCD)
     */
    public static int getCategoryIndex(int number){
        int x = -1;
        switch (number){
            case 0: x = 0; break;
            case 100: x = 1; break;
            case 150: x = 2; break;
            case 175: x = 3; break;
            case 200: x = 4; break;
            case 400: x = 6; break;
            case 500: x = 7; break;
            case 600: x = 8; break;
            case 700: x = 9; break;

            default:
                if (number > 299 && number < 400){ x = 5;}
        }
        return x;
    }
    /**
     * Cette méthode retourne l'index du tableau de tableau myArray selon le type de contrat (A, B, C, D ou E)
     * @param letter            la lettre correspondant au contrat du client
     * @return x                l'index du tableau de tableaux myArray
     */
    public static int getContractIndex (String letter){
        int x = 0;
        switch (letter){
            case "A": x = 0; break;
            case "B": x = 1; break;
            case "C": x = 2; break;
            case "D": x = 3; break;
            case "E": x = 4; break;
            default: break;
        }
        return x;
    }

    /**
     * Ce getter retourne le pourcentage de remboursement
     * @param categoryIndex                                 l'indice de la catégorie de soins
     * @param contractIndex                                 l'indice du contrat du client
     * @return myArray[categoryIndex][contractIndex][0]     le pourcentage en double de remboursement sur 1
     */
    public static int getRefundPercentage(int categoryIndex, int contractIndex){
        return myArray[categoryIndex][contractIndex][0];
    }

    /**
     * Ce getter retourne le montant maximum de remboursement
     * @param categoryIndex                                 l'indice de la catégorie de soins
     * @param contractIndex                                 l'indice du contrat du client
     * @return myArray[categoryIndex][contractIndex][0]     le maximum de remboursement en double (en $)
     */
    public static int getRefundMax(int categoryIndex, int contractIndex){
        return myArray[categoryIndex][contractIndex][1];
    }

    /**
     * Ce getter retourne le montant maximum mensuel de remboursement
     * @param categoryIndex                                 l'indice de la catégorie de soins
     * @return myArray[categoryIndex][contractIndex][2]     le maximum de remboursement en double (en $)
     */
    public static int getMonthlyRefundMax(int categoryIndex){
        return myArray[categoryIndex][0][2];
    }

    /**
     * Cette méthode calcule le montant qui sera remboursé au client
     * @param amountDisbursed   le montant déboursé par le client au procurement du soin
     * @param categoryIndex     la catégorie de soin
     * @param contractIndex     le contrat du client
     * @return claimAmount      le montant remboursable
     */
    public static Money calculateClaim(Money amountDisbursed, int categoryIndex, int contractIndex) {
        int claimAmount = 0;
        int percentRefund = getRefundPercentage(categoryIndex,contractIndex);
        int maxRefund = getRefundMax(categoryIndex,contractIndex);
        claimAmount = amountDisbursed.getTotalMoney() * percentRefund / 100;
        if (claimAmount > maxRefund && maxRefund != 0){
            claimAmount = maxRefund;
        }
        amountDisbursed.setTotalMoney(claimAmount);
        return amountDisbursed;
    }
}
