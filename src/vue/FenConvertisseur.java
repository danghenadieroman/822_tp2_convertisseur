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

    private ResourceBundle bundle;
    JComboBox<String> comboBoxMonnaieSource = new JComboBox<String>();
    JComboBox<String> comboBoxMonnaieSible = new JComboBox<String>();

    public FenConvertisseur(Locale locale) {

        bundle = ResourceBundle.getBundle("Internationalisation", locale);

        fenetre = new JFrame();

        //creation paneau principal
        jpPrincipal = new JPanel(new BorderLayout(10, 10));
        jpPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        fenetre.add(jpPrincipal);

        //creation titre formulaire (NORTH)
        lblTitre = new JLabel(bundle.getString("PTitre"));
        jpPrincipal.add(lblTitre, BorderLayout.NORTH);

        //creation paneau formulaire (CENTRE)
        jpFormulaire = new JPanel(new GridLayout(2, 2, 5, 5));
        jpPrincipal.add(jpFormulaire, BorderLayout.CENTER);

        txtMonnaieSource = new JTextField(20);
        txtMonnaieSource.setText("1");
        jpFormulaire.add(txtMonnaieSource);
        txtMonnaieSible = new JTextField(20);
        txtMonnaieSible.setText("1");
        txtMonnaieSible.setEnabled(false);
        jpFormulaire.add(txtMonnaieSible);

        comboBoxMonnaieSource();
        jpFormulaire.add(comboBoxMonnaieSource);

        comboBoxMonnaieSible();
        jpFormulaire.add(comboBoxMonnaieSible);

        //creation paneau Bouttons (SOUTH)
        jpBoutons = new JPanel();
        jpPrincipal.add(jpBoutons, BorderLayout.SOUTH);
        btnCalculer = new JButton(bundle.getString("PbtnClaculer"));
        jpBoutons.add(btnCalculer);
        btnFermer = new JButton(bundle.getString("PbtnFermer"));
        jpBoutons.add(btnFermer);

        //option fenetre
        fenetre.setTitle(bundle.getString("PTitreFenetre"));
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
                convertir();
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
        comboBoxMonnaieSource.addItem(bundle.getString("PCAD"));
        comboBoxMonnaieSource.addItem(bundle.getString("PUSD"));
        comboBoxMonnaieSource.addItem(bundle.getString("PEuro"));
        comboBoxMonnaieSource.addItem(bundle.getString("PGBP"));
        comboBoxMonnaieSource.addItem(bundle.getString("PJPY"));
        comboBoxMonnaieSource.addItem(bundle.getString("PMDL"));
        comboBoxMonnaieSource.addItem(bundle.getString("PRON"));
        comboBoxMonnaieSource.addItem(bundle.getString("PRUB"));

        comboBoxMonnaieSource.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                convertir();
            }
        });

    }

    private void comboBoxMonnaieSible() {
        comboBoxMonnaieSible.addItem(bundle.getString("PCAD"));
        comboBoxMonnaieSible.addItem(bundle.getString("PUSD"));
        comboBoxMonnaieSible.addItem(bundle.getString("PEuro"));
        comboBoxMonnaieSible.addItem(bundle.getString("PGBP"));
        comboBoxMonnaieSible.addItem(bundle.getString("PJPY"));
        comboBoxMonnaieSible.addItem(bundle.getString("PMDL"));
        comboBoxMonnaieSible.addItem(bundle.getString("PRON"));
        comboBoxMonnaieSible.addItem(bundle.getString("PRUB"));

        comboBoxMonnaieSible.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                convertir();
            }
        });
    }

    private int getTauxDeChange() {

        //à faire
        //ici on determine les monnaies choisi et on calcule le taux d'echange
        // on return le taux
        return 2;
    }

    private void convertir() {
        try {
            int source = Integer.parseInt(txtMonnaieSource.getText().trim());
            int sible = source * getTauxDeChange();
            txtMonnaieSible.setText(sible + "");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "SVP en numérique seulment",
                    "Echec formulaire", JOptionPane.ERROR_MESSAGE);
            txtMonnaieSource.setText("");
        }
    }
}
