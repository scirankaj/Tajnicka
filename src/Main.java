import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Hlavna trieda Main predstavuje uvodnu obrazovku pri spusteni aplikacie.
 * Obsahuje hlavnu metodu main.
 * @author fisher
 */
public class Main {
        
        public static void main(String args[]) throws IOException, InterruptedException {
        final JFrame f = new JFrame("Tajnicka generator - menu");
        JPanel menu = new JPanel();
        /**
         * Tlacidlo typu JButton pre vygenerovanie novej tajnicky na zaklade 
         * zadaneho finalneho slova
         */
        JButton newt = new JButton("Generate Tajnicka");
        newt.setPreferredSize(new Dimension(250,100));
        newt.setFont(newt.getFont().deriveFont(18.0f));
        newt.setFocusPainted(false);
        newt.setBackground(Color.GRAY);
        newt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Tajnicka tj = new Tajnicka();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        /**
         * Tlacidlo typu JButton pre vygenerovanie vlastnej tajnicky.
         */
        JButton news = new JButton("Generate custom Tajnicka"); 
        news.setPreferredSize(new Dimension(250,100));
        news.setFont(news.getFont().deriveFont(18.0f));
        news.setFocusPainted(false);
        news.setBackground(Color.GRAY);
        news.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CustomTajnicka customTajnicka = new CustomTajnicka();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        JButton opt = new JButton("About");
        opt.setPreferredSize(new Dimension(250,100));
        opt.setFont(opt.getFont().deriveFont(18.0f));
        opt.setBackground(Color.GRAY);
        opt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(f, "Tajnicka generator v1.0\n "
                        + "Autor: Jaro Sciranka\n Vytvorene: 29.12.2014\n"
                        + "Programovaci jazyk: Java, kniznica Swing");
            }
        });
        
        /**
         * Tlacidlo typu JButton pre ukoncenie aplikacie.
         */
        JButton esc = new JButton("Exit");
        esc.setPreferredSize(new Dimension(250,100));
        esc.setFont(esc.getFont().deriveFont(18.0f));
        esc.setBackground(Color.GRAY);
        esc.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               f.dispose();
            }
        });
        
        menu.add(newt);
        menu.add(news);
        menu.add(opt);        
        menu.add(esc);
        menu.setBackground(Color.ORANGE);
        menu.setVisible(true);          
        
        f.add(menu);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setSize(400,500);
        f.setLocation(500, 100);
        f.setVisible(true);
    }
        
}
