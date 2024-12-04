
package equipe5;

/**
 *
 * Cette classe construit des objets Money pour modéliser l'argent sous forme de int incluant dollars et cents.
 *
 * @authors Victor Poirier, Thomas Primeau, Gabriella Farallo & Mehdi Bennis
 * @version Mars 2023
 *
 * Code inspiré d'un exemple du cours.
 */

public class Money {

    private int totalMoney;

    public Money() throws RefundException {
        this(0,0);
    }

    public Money(Money money) {
        totalMoney = money.getTotalMoney();
    }

    public Money(String montantAmount) throws RefundException {
        this(getDollarsFromString(montantAmount), getCentsFromString(montantAmount));
    }

    public Money(int dollars) throws RefundException {

        if(dollars < 0) {
            throw new RefundException("Erreur, un montant d'argent est invalide");
        } else {
            totalMoney += dollars;
        }
    }

    public Money(int dollars, int cents) throws RefundException {

        if(dollars < 0 || cents < 0 || cents > 99) {
            throw new RefundException("Erreur, un montant d'argent est invalide");
        } else {
            totalMoney += dollars * 100 + cents;
        }
    }

    /**
     * Cette méthode parse un paramètre String et retourne le nombre de dollars sans les cents,
     * la virgule/point et le signe de dollar en forme de int.
     * @param amount    String du montant pris d'un fichier json
     * @return  int dollars
     * @throws NumberFormatException
     */
    public static int getDollarsFromString(String amount) throws NumberFormatException {
        int dollars = Integer.parseInt(amount.split("[\\.,$]")[0]);
        return dollars;
    }

    /**
     * Cette méthode parse un paramètre String et retourne le nombre de cents sans les dollars,
     * la virgule/point et le signe de dollar en forme de int.
     * @param amount    String du montant pris d'un fichier json
     * @return  int cents
     * @throws NumberFormatException
     */
    public static int getCentsFromString(String amount) throws NumberFormatException {
        int cents = Integer.parseInt(amount.split("[\\.,$]")[1]);
        return cents;
    }

    /**
     * Cette méthode est un getter pour le nombre de dollars d'un objet Money.
     * @return  int nombre de dollars
     */
    public int getDollars(){
        return this.totalMoney / 100;
    }

    /**
     * Cette méthode est un getter pour le nombre de cents d'un objet Money.
     * @return  int nombre de cents
     */
    public int getCents(){
        return this.totalMoney % 100;
    }

    /**
     * Cette méthode est un getter pour le montant total d'un objet Money.
     * @return  int nombre de dollars et cents
     */
    public int getTotalMoney(){
        return this.totalMoney;
    }

    /**
     * Cette méthode set le montant total d'un objet Money avec le montant int passé en paramètre.
     * @param amount    int montant total (nouveau)
     * @throws IllegalArgumentException
     */
    public void setTotalMoney(int amount) throws IllegalArgumentException {
        if(amount < 0) {
            throw new IllegalArgumentException();
        } else {
            this.totalMoney = amount;
        }
    }

    /**
     * Cette méthode additionne le montant total d'un objet Money avec un autre.
     * @param autre     objet Money qui sera additionné
     */
    public void add(Money autre){
        this.totalMoney += autre.totalMoney;
    }

    public void substract(Money autre){
        this.totalMoney -= autre.totalMoney;
    }

    /**
     * Cette méthode transforme un objet Money en chaîne String
     * @return  String
     */
    @Override
    public String toString(){
        String centsZero = "";
        if (this.getCents() < 10) {
            centsZero = "0";
        }
        return getDollars() + "." + centsZero + getCents() + "$";
    }

}
