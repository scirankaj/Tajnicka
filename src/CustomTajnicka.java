import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Trieda CustomTajnicka vytvori tajnicky kompletne na zaklade parametrov
 * zadanych pouzivatelom.
 *
 * @author fisher
 */
public class CustomTajnicka {

    /**
     * Premenna finalWord typu String predstavuje finalne slovo zadane
     * pouzivatelom.
     */
    private String finalWord = "";
    /**
     * Premennu row a col typu Integer predstavuju pocet riadkov a stlpcov
     * struktury tajnicky.
     */
    private int row =0, col = 0;
    /**
     * Pole retazcov quest predstavuje legendu k danym slovam tajnicky.
     */
    private final ArrayList<String> quest;
    /**
     * Pomocna premenna t typu Integer pouzita ako pocitadlo.
     */
    private int t = 1;

    /**
     * Kontruktor triedy CustomTajnicka.
     * @throws IOException 
     */
    public CustomTajnicka() throws IOException {

        quest = new ArrayList<>();

            finalWord = (String) JOptionPane.showInputDialog("Insert final word for new grid:");
        char[] finalw;
        finalw = finalWord.toCharArray();
        row = finalWord.length();
        col = 16;

        final JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(500, 475);
        jf.setLayout(new BorderLayout());

        /**
         * Komponent JPanel predstavuje okno pre legendu k jednotlivym slovam
         * tajnicky.
         */
        JPanel q = new JPanel();

        TajnickaGrid taj = new TajnickaGrid(row, col, finalWord);
        taj.setLocation(600, 0);

        for (int i = 0; i < finalw.length; i++) {
            int pos = 0;
  
              String word = JOptionPane.showInputDialog("Insert Word for letter " + finalw[i] + ".");
              String question = JOptionPane.showInputDialog("Insert Question for this word.");
            quest.add(question);

            char[] slovo = word.toCharArray();
            for (int j = 0; j <= slovo.length - 1; j++) {
                if (slovo[j] == finalw[i]) {
                    pos = j;
                }
            }
            while (pos == 0) {
                JOptionPane.showMessageDialog(taj, "Error! You inserted invalid word.");
                word = JOptionPane.showInputDialog(taj, "Insert word for letter " + finalw[i] + ":");
                slovo = word.toCharArray();
                for (int j = 0; j <= slovo.length - 1; j++) {
                    if (slovo[j] == finalw[i]) {
                        pos = j;
                    }
                }
            }

            int end = (word.length() - pos) + col / 2;
            for (int x = (col / 2) - pos; x < end; x++) {
                if (i == 0) {
                    taj.cells[x].setBackground(Color.YELLOW);
                    taj.cells[x].setSignal();
                }
                if (x % (col / 2) == 0) {
                    taj.cells[x].setBackground(Color.blue);
                    taj.cells[x].setSignal();
                } else {
                    if (x % (col / 2) == 0) {
                        taj.cells[x + (col * i)].setBackground(Color.blue);
                        taj.cells[x + (col * i)].setSignal();
                    }
                    taj.cells[x + (col * i)].setBackground(Color.yellow);
                    taj.cells[x + (col * i)].setSignal();
                }
            }
        }
        for (int f = 0; f < taj.getGSize(); f++) {
            if (taj.cells[f].getSignal() == 0) {
                if (!(taj.cells[f].getBackground().equals(Color.blue))) {
                    taj.cells[f].setVisible(false);
                }
            }

        }
        addQuestions(quest, q);
        JButton but = new JButton("Exit");
        but.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
            }
        });
        q.add(but);
        
        jf.setVisible(true);
        jf.add(q);
        jf.add(taj);

    }

    /**
     * Funkcia addQuestions prida vsetky otazky z pola quest do komponentu
     * JPanel.     
     * @param al predstavuje pole s legendou.
     * @param j predstavuje okno, v ktorom sa zobrazi legenda.
     */
    private void addQuestions(ArrayList<String> al, JPanel j) {
        for (String al1 : al) {
            JLabel jl = new JLabel(t + ". " + al1);
            j.add(jl);
            t++;
        }
    }

}
