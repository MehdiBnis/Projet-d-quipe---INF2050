package equipe5;

import java.util.ArrayList;

public class Arguments {

    public static String getOption(String[] args) throws RefundException {
        String option = "";
        if (args.length == 3) {
            if (args[0].startsWith("-")) {
                option = args[0];
            } else {
                throw new RefundException("Erreur, le premier argument doit être une option commençant par '-'");
            }
        }
        return option;
    }

    public static String[] getParams(String[] args) {
        String[] params = {null, null};
        if (args.length == 2) {
            getParamOrError(0, params, args);
        } else if (args.length == 3) {
            getParamOrError(1, params, args);
        }
        return params;
    }

    public static void getParamOrError(int i, String[] params, String[] args) {
        if (args[i].endsWith(".json") && args[i+1].endsWith(".json")) {
            params[0] = args[i];
            params[1] = args[i+1];
        } else {
            Main.exit("Erreur, les fichiers d'entrée et de sortie doivent être de format JSON");
        }
    }

}
