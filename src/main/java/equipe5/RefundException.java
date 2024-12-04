package equipe5;

/**
 * Cette classe génère un nouveau type d'exceptions
 *
 * @authors Victor Poirier, Thomas Primeau, Gabriella Farallo et Mehdi Bennis
 * @version Mars 2023
 */

public class RefundException extends Exception {
    public String message = "";
    public RefundException(String message) {
        this.message = message;
    }
}