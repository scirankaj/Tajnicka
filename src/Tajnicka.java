import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Trieda Tajnicka sluzi na vygenerovanie lubovolnej tajnicky na zaklade
 * parametrov zadanych pouzivatelom.
 *
 * @author fisher
 */
public final class Tajnicka {

    /**
     * Premenna pos typu Integer sluzi na ulozenie pozicie pismena v slove
     * zhodujuceho sa s pismenom finalneho slova v riadku.
     */
    private int pos = 0;
    /**
     * Premenna col typu Integer predstavuje pocet stlpcov struktury. Je
     * inicializovana na hodnotu 16.
     */
    private final int col = 16;
    /**
     * Premenna line typu String predstavuje slovo v aktualnom riadku tajnicky.
     */
    private String line;
    /**
     * Pole retazcov used obsahuje zoznam uz pouzitych slov pri generovani
     * tajnicky.
     */
    private ArrayList<String> used;
    /**
     * Pole retazcov quest obsahuje legendu k slovam tajnicky.
     */
    private final ArrayList<String> quest;
    /**
     * Pomocna premenna t typu Integer sluzi ako pocitadlo.
     */
    private int t = 1;

    /**
     * Konstruktor triedy Tajnicka.
     * @throws IOException 
     */
    @SuppressWarnings("empty-statement")
    public Tajnicka() throws IOException {
        used = new ArrayList<>();
        quest = new ArrayList<>();
        char c;
        int lines = 0;
        String finalw = "";

        finalw = (String) JOptionPane.showInputDialog("Insert final Word.");

        final JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(500, 475);
        jf.setLayout(new BorderLayout());

        JPanel q = new JPanel();

        TajnickaGrid taj = new TajnickaGrid(finalw.length(), col, finalw);
        taj.setLocation(600, 0);
        for (int i = 0; i < finalw.length(); i++) {
            c = finalw.charAt(i);

            line = findPos(c);

            int end = (line.length() - pos) + col / 2;
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
            
        used.clear();
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
        jf.pack();
        
    }

    /**
     * Funkcia findPos typu String sluzi na najdenie slova pre aktualny riadok
     * na zaklade pismena finalne slova v riadku.
     *
     * @param c Pismeno finalneho slova v riadku.
     * @return Funkcia vrati najdene vhodne slovo aj s poziciou.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public String findPos(char c) throws FileNotFoundException, IOException {
        String finder = "";
        String lines;
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("finals.txt"))) {

            read:
            while ((lines = br.readLine()) != null) {
                count++;
                for (int b = 0; b < lines.length(); b++) {
                    char ch = lines.charAt(b);
                    if (c == ch) {
                        finder = lines;
                        pos = b;
                        for (String str : used) {
                            if (str.equals(finder)) {
                                continue read;
                            }
                        }
                        used.add(finder);
                        findQues(count);
                        return finder;
                    }
                }
            }
        }
        return finder;
    }

    /**
     * Funkcia addQuestions prida vsetky otazky z pola quest do komponentu
     * JPanel.
     *
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

    /**
     * Funkcia prida do pola quest legendu zo suboru k aktualnemu slova na
     * zaklade pocitadla count.
     *
     * @param count
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void findQues(int count) throws FileNotFoundException, IOException {
        String lines;
        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("questions.txt"))) {
            while ((lines = br.readLine()) != null) {
                i++;
                if (i == count) {
                    quest.add(lines);
                }                
            }
        }
    }
}
