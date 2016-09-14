package control;

import java.util.Locale;
import vue.FenConvertisseur;

/**
 *
 * @author Dan-Ghenadie Roman
 */
public class AppCtr {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Locale local = Locale.getDefault();
        Locale.setDefault(new Locale("fr", ""));
        FenConvertisseur fenetre = new FenConvertisseur(local);
    }

}
