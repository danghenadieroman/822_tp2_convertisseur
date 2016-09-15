package vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Dan-Ghendie Roman
 */
public class FenConvertisseur {

    private JFrame fenetre;

    private JLabel lblTitre;

    private JTextField txtMonnaieSource;
    private JTextField txtMonnaieSible;

    private JButton btnCalculer;
    private JButton btnFermer;

    private JPanel jpPrincipal;
    private JPanel jpFormulaire;
    private JPanel jpBoutons;

    private ResourceBundle bundleI18n;
    JComboBox<String> comboBoxMonnaieSource = new JComboBox<String>();
    JComboBox<String> comboBoxMonnaieSible = new JComboBox<String>();

    public FenConvertisseur() {

        bundleI18n = ResourceBundle.getBundle("Internationalisation");

        fenetre = new JFrame();

        //creation paneau principal
        jpPrincipal = new JPanel(new BorderLayout(10, 10));
        jpPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        fenetre.add(jpPrincipal);

        //creation titre formulaire (NORTH)
        lblTitre = new JLabel(bundleI18n.getString("PTitre"));
        jpPrincipal.add(lblTitre, BorderLayout.NORTH);

        //creation paneau formulaire (CENTRE)
        jpFormulaire = new JPanel(new GridLayout(2, 2, 5, 5));
        jpPrincipal.add(jpFormulaire, BorderLayout.CENTER);

        txtMonnaieSource = new JTextField(20);
        txtMonnaieSource.setText("1");
        jpFormulaire.add(txtMonnaieSource);
        txtMonnaieSible = new JTextField(20);
        txtMonnaieSible.setText("1");
        txtMonnaieSible.setEnabled(true);
        jpFormulaire.add(txtMonnaieSible);

        comboBoxMonnaieSource();
        jpFormulaire.add(comboBoxMonnaieSource);

        comboBoxMonnaieSible();
        jpFormulaire.add(comboBoxMonnaieSible);

        //creation paneau Bouttons (SOUTH)
        jpBoutons = new JPanel();
        jpPrincipal.add(jpBoutons, BorderLayout.SOUTH);
        btnCalculer = new JButton(bundleI18n.getString("PbtnCalculer"));
        jpBoutons.add(btnCalculer);
        btnFermer = new JButton(bundleI18n.getString("PbtnFermer"));
        jpBoutons.add(btnFermer);

        //option fenetre
        fenetre.setTitle(bundleI18n.getString("PTitreFenetre"));
        fenetre.setDefaultCloseOperation(EXIT_ON_CLOSE);
        fenetre.setResizable(true);
        fenetre.setSize(500, 200);
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);

        //listeners
        boutonCalculer();
        boutonFermer();

    }

    private void boutonCalculer() {
        btnCalculer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //j'ai mit convertir() dans comboBox aussi, pour faire marcher le calcule dans le cas de changement monnaie
                calculer();
            }
        });
    }

    private void boutonFermer() {
        btnFermer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
    }

    private void comboBoxMonnaieSource() {
        comboBoxMonnaieSource.addItem(bundleI18n.getString("PCAD"));
        comboBoxMonnaieSource.addItem(bundleI18n.getString("PUSD"));
        comboBoxMonnaieSource.addItem(bundleI18n.getString("PEuro"));
        comboBoxMonnaieSource.addItem(bundleI18n.getString("PGBP"));
        comboBoxMonnaieSource.addItem(bundleI18n.getString("PJPY"));
        comboBoxMonnaieSource.addItem(bundleI18n.getString("PMDL"));
        comboBoxMonnaieSource.addItem(bundleI18n.getString("PRON"));
        comboBoxMonnaieSource.addItem(bundleI18n.getString("PRUB"));

        comboBoxMonnaieSource.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                calculer();

            }
        });

    }

    private void comboBoxMonnaieSible() {
        comboBoxMonnaieSible.addItem(bundleI18n.getString("PCAD"));
        comboBoxMonnaieSible.addItem(bundleI18n.getString("PUSD"));
        comboBoxMonnaieSible.addItem(bundleI18n.getString("PEuro"));
        comboBoxMonnaieSible.addItem(bundleI18n.getString("PGBP"));
        comboBoxMonnaieSible.addItem(bundleI18n.getString("PJPY"));
        comboBoxMonnaieSible.addItem(bundleI18n.getString("PMDL"));
        comboBoxMonnaieSible.addItem(bundleI18n.getString("PRON"));
        comboBoxMonnaieSible.addItem(bundleI18n.getString("PRUB"));

        comboBoxMonnaieSible.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                calculer();
            }
        });
    }

    private double getTauxDeChange() {

        ResourceBundle bundleCurrencyRate = ResourceBundle.getBundle("CurrencyRate");
        double tauxDeChange = 0;
        
        switch (comboBoxMonnaieSource.getSelectedIndex()) {
            case 0: //CAD to ...
                switch (comboBoxMonnaieSible.getSelectedIndex()) {
                    case 0:
                        tauxDeChange = Double.parseDouble(bundleCurrencyRate.getString("PCADtoCAD"));
                        break;
                    case 1:
                        tauxDeChange = Double.parseDouble(bundleCurrencyRate.getString("PCADtoUSD"));
                        break;
                    case 2:
                        tauxDeChange = Double.parseDouble(bundleCurrencyRate.getString("PCADtoEUR"));
                        break;
                    case 3:
                        tauxDeChange = Double.parseDouble(bundleCurrencyRate.getString("PCADtoBGP"));
                        break;
                    case 4:
                        tauxDeChange = Double.parseDouble(bundleCurrencyRate.getString("PCADtoJPY"));
                        break;
                }//switch

                break; //case: 0 CAD to ...
                
            case 1: // USD to ...
                switch (comboBoxMonnaieSible.getSelectedIndex()) {
                    case 0:
                        tauxDeChange = Double.parseDouble(bundleCurrencyRate.getString("PUSDtoCAD"));
                        break;
                    case 1:
                        tauxDeChange = Double.parseDouble(bundleCurrencyRate.getString("PUSDtoUSD"));
                        break;
                    case 2:
                        tauxDeChange = Double.parseDouble(bundleCurrencyRate.getString("PUSDtoEUR"));
                        break;
                    case 3:
                        tauxDeChange = Double.parseDouble(bundleCurrencyRate.getString("PUSDtoBGP"));
                        break;
                    case 4:
                        tauxDeChange = Double.parseDouble(bundleCurrencyRate.getString("PUSDtoJPY"));
                        break;
                }//switch

                break;//case: 1 USD to ...
                
            case 2: // EUR to ...
                switch (comboBoxMonnaieSible.getSelectedIndex()) {
                    case 0:
                        tauxDeChange = Double.parseDouble(bundleCurrencyRate.getString("PEURtoCAD"));
                        break;
                    case 1:
                        tauxDeChange = Double.parseDouble(bundleCurrencyRate.getString("PEURtoUSD"));
                        break;
                    case 2:
                        tauxDeChange = Double.parseDouble(bundleCurrencyRate.getString("PEURtoEUR"));
                        break;
                    case 3:
                        tauxDeChange = Double.parseDouble(bundleCurrencyRate.getString("PEURtoBGP"));
                        break;
                    case 4:
                        tauxDeChange = Double.parseDouble(bundleCurrencyRate.getString("PEURtoJPY"));
                        break;
                }//switch

                break;//case: 2 EUR to ...
                
            case 3: // BGP to ...
                switch (comboBoxMonnaieSible.getSelectedIndex()) {
                    case 0:
                        tauxDeChange = Double.parseDouble(bundleCurrencyRate.getString("PBGPtoCAD"));
                        break;
                    case 1:
                        tauxDeChange = Double.parseDouble(bundleCurrencyRate.getString("PBGPtoUSD"));
                        break;
                    case 2:
                        tauxDeChange = Double.parseDouble(bundleCurrencyRate.getString("PBGPtoEUR"));
                        break;
                    case 3:
                        tauxDeChange = Double.parseDouble(bundleCurrencyRate.getString("PBGPtoBGP"));
                        break;
                    case 4:
                        tauxDeChange = Double.parseDouble(bundleCurrencyRate.getString("PBGPtoJPY"));
                        break;
                }//switch
                
                break;//case: 3 BGP to ...
                
            case 4: // JPY to ...
                //ici on a le resultat < 0.01 . 
                //il faut gerer ce cas, parce que il n<existe pas monnais plus petit que 1
                switch (comboBoxMonnaieSible.getSelectedIndex()) {
                    case 0:
                        tauxDeChange = Double.parseDouble(bundleCurrencyRate.getString("PJPYtoCAD"));
                        tauxDeChange = tauxDeChange / 100;
                        break;
                    case 1:
                        tauxDeChange = Double.parseDouble(bundleCurrencyRate.getString("PJPYtoUSD"));
                        tauxDeChange = tauxDeChange / 100;
                        break;
                    case 2:
                        tauxDeChange = Double.parseDouble(bundleCurrencyRate.getString("PJPYtoEUR"));
                        tauxDeChange = tauxDeChange / 100;
                        break;
                    case 3:
                        tauxDeChange = Double.parseDouble(bundleCurrencyRate.getString("PJPYtoBGP"));
                        tauxDeChange = tauxDeChange / 100;
                        break;
                    case 4:
                        tauxDeChange = Double.parseDouble(bundleCurrencyRate.getString("PJPYtoJPY"));
                        break;
                }//switch

                break;//case: 4 JPY to ...
        }
        return tauxDeChange;
    }

    private void calculer() {
        try {
            int source = Integer.parseInt(txtMonnaieSource.getText().trim());
            double sible = source * getTauxDeChange();
            txtMonnaieSible.setText(sible + "");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "SVP en numérique seulment",
                    "Echec formulaire", JOptionPane.ERROR_MESSAGE);
            txtMonnaieSource.setText("");
        }
    }
}
