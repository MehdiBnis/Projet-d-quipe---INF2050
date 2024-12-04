package equipe5;

import java.io.IOException;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.validator.GenericValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Cette classe modélise les rapports client.
 *
 * @authors Victor Poirier, Thomas Primeau, Gabriella Farallo et Mehdi Bennis
 * @version Mars 2023
 */

public class ClientReport {
    //--------------------
    //ATTRIBUTS D'INSTANCE
    //--------------------

    private MonthlyTotal monthlyTotal;
    private JSONObject clientReport;

    private RefundException error;

    private boolean valid;

    // Constructeur avec paramètre
    public ClientReport(String clientFile) {
        this.clientReport = null;
        this.error = null;
        try {
            this.loadClientReportFromJSON(clientFile);
            isClientReportValid();
            monthlyTotal = new MonthlyTotal();
            processClientReport();
        } catch (RefundException e) {
            this.error = e;

        }
    }

    /**
     * Cette méthode crée un objet JSONObject de rapport client à partir du fichier JSON.
     *
     * @param filename nom du fichier JSON
     * @throws RefundException
     */
    private void loadClientReportFromJSON(String filename) throws RefundException {
        try {
            this.clientReport = (JSONObject) JSONSerializer.toJSON(FileIO.readFileToString(filename));
        } catch (JSONException e) {
            throw new RefundException("Erreur, le fichier json est invalide.");
        } catch (IOException e) {
            throw new RefundException("Erreur, lecture du fichier impossible.");
        }
    }

    /**
     * Cette méthode valide que le dossier client est valide, si invalide une exception est lancée, sinon
     * le programme continue.
     *
     * @throws RefundException
     */
    private void isClientFolderValid() throws RefundException {
        if (this.clientReport.has("dossier")) {
            String clientFolder = this.clientReport.getString("dossier");
            Pattern pattern = Pattern.compile("^[ABCDE]\\d{6}$");
            Matcher matcher = pattern.matcher(clientFolder);
            if (matcher.find()) {
                return;
            }
        }
        Stats.incrementerReclam(1);
        throw new RefundException("Erreur, le dossier est invalide");
    }

    /**
     * Cette méthode valide que le mois soit sous le format yyyy-mm, si invalide une exception est lancée, sinon
     * le programme continue.
     *
     * @throws RefundException
     */
    private void isReportMonthValid() throws RefundException {
        String reportMonth = this.clientReport.getString("mois");
        if (!GenericValidator.isDate(reportMonth, "yyyy-MM", true)) {
            Stats.incrementerReclam(1);
            throw new RefundException("Erreur, le mois est invalide.");
        }
    }

    /**
     * Cette méthode valide que le mois soit sous le format yyyy-mm et que le
     * mois du soin soit le même que la date de la réclamation. Si invalide une exception est lancée, sinon
     * le programme continue.
     *
     * @param claimDate  mois du soin
     * @param reportDate mois de la réclamation
     * @throws RefundException
     */
    private void isClaimSameMonthAsReport(String claimDate, String reportDate) throws RefundException {
        boolean isClaimDateValid = GenericValidator.isDate(claimDate, "yyyy-MM-dd", true);
        if (isClaimDateValid) {
            String claimMonth = claimDate.substring(5, 7);
            String reportMonth = reportDate.substring(5, 7);
            if (claimMonth.equals(reportMonth)) {
                return;
            }
        }
        Stats.incrementerReclam(1);
        throw new RefundException("Erreur, le mois d'une des réclamations est invalide.");
    }

    /**
     * Cette méthode valide que le format du montant du soin soit sous format de deux décimales
     * et a un signe de dollar. Si invalide une exception est lancée, sinon le programme continue.
     *
     * @param amount montant du soins
     * @throws RefundException
     */
    private void isClaimAmountValid(String amount) throws RefundException {
        Pattern pattern = Pattern.compile("^\\d+[.,]\\d{2}\\$$");
        Matcher matcher = pattern.matcher(amount);
        if (!matcher.find()) {
            Stats.incrementerReclam(1);
            throw new RefundException("Erreur, le montant d'une réclamation est invalide.");
        }
    }


    /**
     * Cette méthode valide le chiffre qui représente le type de soin sous format
     * de plus d'un chiffre. Si invalide une exception est lancée, sinon le programme continue.
     *
     * @param careNumber numéro de soin
     * @throws RefundException
     */
    private void isClaimCareNumberValid(int careNumber) throws RefundException {
        String careNumberString = Integer.toString(careNumber);
        Pattern pattern = Pattern.compile("^\\d+$");
        Matcher matcher = pattern.matcher(careNumberString);
        if (matcher.find()) {
            if (Claims.getCategoryIndex(careNumber) != -1) {
                return;
            }
        }
        Stats.incrementerReclam(1);
        throw new RefundException("Erreur, le numéro de soin d'une réclamation est invalide.");
    }

    /**
     * Cette méthode valide une seule réclamation selon trois critères: le mois rentré en param
     * est le même que celui de la réclamation, le montant et le numéro de soin. Si invalide une
     * exception est lancée, sinon le programme continue.
     *
     * @param claim      fichier objet JSON
     * @param reportDate date du mois que l'on veut vérifier
     * @throws RefundException
     */
    private void isClaimValid(JSONObject claim, String reportDate) throws RefundException {
        isClaimSameMonthAsReport(claim.getString("date"), reportDate);
        isClaimAmountValid(claim.getString("montant"));
        int claimCareNumber;
        Object claimCare = claim.get("soin");
        if (claimCare instanceof Integer) {
            claimCareNumber = (int) claimCare;
        } else {
            Stats.incrementerReclam(1);
            throw new RefundException("Erreur, le numéro de soin doit être un nombre entier.");
        }
        isClaimCareNumberValid(claimCareNumber);
    }

    /**
     * Cette méthode valide si toutes les réclamations d'un client sont valides, en utilisant
     * isClaimValid. Si invalide une exception est lancée, sinon le programme continue.
     *
     * @throws RefundException
     */
    private void areClaimsValid() throws RefundException {
        String reportDate = this.clientReport.getString("mois");
        String claimsString = this.clientReport.getString("reclamations");
        JSONArray claims = (JSONArray) JSONSerializer.toJSON(claimsString);
        for (int i = 0; i < claims.size(); i++) {
            JSONObject claim = claims.getJSONObject(i);
            isClaimValid(claim, reportDate);
        }
    }

    /**
     * Cette méthode valide le numéro du client, le caractère du type de contrat,
     * le mois de la réclamation ainsi que toutes les réclamations ensemble. Si
     * invalide une exception est lancée, sinon le programme continue.
     *
     * @throws RefundException
     */
    private void isClientReportValid() throws RefundException {
        isClientFolderValid();
        isReportMonthValid();
        areClaimsValid();
    }

    /**
     * Cette méthode assemble et crée un nouveau objet JSON pour un seul remboursement et
     * y ajoute les calculs de remboursements selon les contrats. Si invalide une exception
     * est lancée, sinon le programme continue.
     *
     * @param claim la réclamation d'un client
     * @return un remboursement
     * @throws RefundException
     */
    private JSONObject calculateReimbursement(JSONObject claim) throws RefundException {
        int contractIndex = Claims.getContractIndex(String.valueOf((this.clientReport.getString("dossier")).charAt(0)));
        int categoryIndex = Claims.getCategoryIndex(Integer.parseInt(claim.getString("soin")));
        String claimAmountString = claim.getString("montant");
        Money montant = Claims.calculateClaim(new Money(claimAmountString), categoryIndex, contractIndex);
        this.monthlyTotal.addToMonthlyTotalForCare(categoryIndex, montant);
        this.monthlyTotal.adjustAmount(categoryIndex, montant);
        Stats.incrementerSoin(categoryIndex);
        String reimbursedAmount = montant.toString();
        claim.put("montant", reimbursedAmount);
        return claim;
    }


    /**
     * Cette méthode calcule et assemble un nouveau objet JSON de tous les remboursements
     * d'un client selon ses réclamations.
     *
     * @return tableau JSON des remboursements
     */
    private JSONArray getReimbursements() throws RefundException {
        String claimsString = this.clientReport.getString("reclamations");
        JSONArray claims = (JSONArray) JSONSerializer.toJSON(claimsString);
        JSONArray reimbursements = new JSONArray();
        for (int i = 0; i < claims.size(); i++) {
            JSONObject reimbursement = calculateReimbursement(claims.getJSONObject(i));
            reimbursements.add(reimbursement);
        }
        return reimbursements;
    }

    /**
     * Cette méthode calcule et retourne le montant total de chaque remboursements du fichier json initial
     * sous forme d'objet Money
     * @return  totalAmount (Objet Money ayant le montant total)
     * @throws RefundException
     */
    private Money getTotal(JSONArray reimbursements) throws RefundException {
        Money totalAmount = new Money();
        for (int i = 0; i < reimbursements.size(); i++) {
            String reimbursementAmountString = reimbursements.getJSONObject(i).getString("montant");
            Stats.incrementerReclam(0);
            totalAmount.add(new Money(reimbursementAmountString));
        }
        return totalAmount;
    }

    /**
     * Cette méthode modifie le fichier JSON en ajoutant les remboursements nécessaires
     * et enlève les réclamations et le type de contrat.
     *
     * @throws RefundException
     */
    private void processClientReport() throws RefundException {
        JSONArray reimbursements = getReimbursements();
        this.clientReport.put("remboursements", reimbursements);
        this.clientReport.put("total", getTotal(reimbursements).toString());
        this.clientReport.remove("reclamations");
    }

    /**
     * Cette méthode transforme l'objet JSON de remboursements en String.
     *
     * @return Fiche de remboursements en String
     */
    public String toString() {
        String reportString;

        if (this.error == null)
            reportString = this.clientReport.toString(2);
        else {
            reportString = "{\n\t\"message\": \"" + this.error.message + "\"\n}";
        }

        return reportString;
    }
}