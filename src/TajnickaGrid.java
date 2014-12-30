import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;
import tajnickagrid.Cell;

/**
 * Trieda TajnickaGrid predstavuje prototyp vytvarania zakladnej struktury
 * tajnicky. Vytvori tajnicku na zakladne parametrov, ktorymi su pocet riadkov,
 * stlpcov a finalne slovo.
 *
 * @author fisher
 */
public class TajnickaGrid extends JFrame {

    /**
     * Komponent JPanel predstavuje zakladnu kostru pouzivatelskeho rozhrania
     * aplikacie.
     */
    JPanel p = new JPanel();
    /**
     * Pole cells[] typu Cell predstavuje zakladnu stvorcekovu strukturu hry.
     */
    Cell cells[];
    /**
     * Premenna row typu Integer predstavuje pocet riadkov v strukture urcenych
     * na zaklade dlzky finalneho slova.
     */
    private int row;
    /**
     * Premenna col typu Integer predstavuje pocet stlpcov v strukture.
     */
    private int col;
    /**
     * Premenna finalWord typu String predstavuje finalne slovo tajnicky.
     */
    private String finalWord;
    /**
     * Premenna tajnicka typu String predstavuje finalne slovo zadane riesitelom
     * tajnicky. Sluzi na kontrolu spravnosti riesenia tajnicky.
     */
    private String tajnicka = "";
    /**
     * Pomocna premenna count typu Integer pouzita ako pocitadlo.
     */
    public int count = 0;

    
    /**
     * Konstruktor triedy TajnickaGrid.
     * @param row Pocet riadkov v strukture tajnicky.
     * @param col Pocet stlpcov v strukture tajnicky.
     * @param finalWords Finalne slovo tajnicky.
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public TajnickaGrid(int row, int col, final String finalWords) throws FileNotFoundException, IOException {
        super("Tajnicka v1.0");
        this.row = row;
        this.col = col;
        this.finalWord = finalWords;
        this.cells = new Cell[col * row];
        setSize(600, 600);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JMenuBar menuBar;
        JMenu menu, menu2;
        JMenuItem menuItem, menuItem4, menuItem6;

        /**
         * Vytvorenie hornej listy hlavneho menu.
         */
        menuBar = new JMenuBar();

        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        menu.getAccessibleContext().setAccessibleDescription(
                "Basic file menu.");
        menuBar.add(menu);

        /**
         * Pridane ovladacie prvky pre aktualnu tajnicku do menu.
         */
        menuItem = new JMenuItem("Check grid",
                KeyEvent.VK_C);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Checking of actual solution.");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                check();
                if (tajnicka.equalsIgnoreCase(finalWord)) {
                    JOptionPane.showMessageDialog(rootPane, "You won, congrats!");
                } else {
                    System.out.println(tajnicka + " and " + finalWord);
                    JOptionPane.showMessageDialog(rootPane, "Game over!");
                }
            }
        });

        menuItem6 = new JMenuItem("Exit");
        menuItem6.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {                
                dispose();
            }
        });
        menu.add(menuItem);
        menu.add(menuItem6);

        menu2 = new JMenu("New Tajnicka");
        menu2.setMnemonic(KeyEvent.VK_I);
        menuBar.add(menu2);

        menuItem4 = new JMenuItem("Generate new Custom Grid.", KeyEvent.VK_G);
        menuItem4.getAccessibleContext().setAccessibleDescription(
                "Generating new Grid based on inserted final word.");
        menuItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CustomTajnicka customTajnicka = new CustomTajnicka();
                } catch (IOException ex) {
                    Logger.getLogger(TajnickaGrid.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        menu2.add(menuItem4);

        int z = 0, b = 0;
        String[] quest = new String[30];
        setJMenuBar(menuBar);
        p.setLayout(new GridLayout(row, col));
        for (int i = 0; i < (row * col); i++) {
            cells[i] = new Cell(1);
            Border raisedbevel = BorderFactory.createRaisedBevelBorder();
            Border loweredbevel = BorderFactory.createLoweredBevelBorder();
            Border redborder = BorderFactory.createLineBorder(Color.gray, 0);
            Border compound = BorderFactory.createCompoundBorder(
                    raisedbevel, loweredbevel);
            Border border = BorderFactory.createCompoundBorder(redborder, compound);
            cells[i].setBorder(border);
            if (col % 2 == 0) {
                if ((i % (col / 2) == 0) && i % col != 0) {
                    cells[i].setBackground(Color.blue);
                } else if (col % 2 != 0) {
                    if ((i % ((col - 1) / 2) == 0) && i % (col - 1) != 0) {
                        cells[i].setBackground(Color.blue);
                    }
                }
            }
            p.add(cells[i]);
        }
        add(p);

        setVisible(true);

    }

    /**
     * Funkcia check typu String sprostredkuje kontrolu spravnosti riesenia
     * aktualnej tajnicky.
     *
     * @return Funkcia vracia premennu tajnicka, ktoru ziska zo tajnicky.
     */
    public String check() {
        int middle = this.getCol() / 2;
        for (int j = middle; j <= (this.getRow() * this.getCol()); j += this.getCol()) {
            tajnicka = tajnicka + this.cells[j].getText();
            this.cells[j].setBackground(Color.red);
        }
        return tajnicka;
    }

    /**
     * @return Funkcia getRow() vrati aktualny pocet riadkov struktury.
     */
    public int getRow() {
        return row;
    }

    /**
     * @return Funkcia getCol() vrati aktualny pocet stlpcov struktury.
     */
    public int getCol() {
        return col;
    }

    /**
     * @return Funkcia getGSize() vrati rozmer celej struktury tajnicky.
     */
    public int getGSize() {
        return getCol() * getRow();
    }

}
