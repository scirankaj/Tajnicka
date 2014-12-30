package tajnickagrid;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

/**
 * Trieda Cell predstavuje prototyp 1 stvorceka (bunky) v strukture tajnicky.
 * Rozsiruje komponent JTextField a implementuje MouseListener.
 * @author Jarko
 */
public class Cell extends JTextField implements MouseListener{
    /**
     * Pomocna premenna value typu byte je inkrementovana pri kliknuti mysou 
     * na bunku.
     */
    byte value =0;
    /**
     * Premenna limit typu Integer sluzi na limitovanie poctu pismen v bunke.
     * Je inicializovana na hodnotu 1.
     */
    public int limit =1;
    
    
    
    public Cell(int limit) throws IOException{
        super();
        
        this.limit = limit = 1;
        setEditable(true);
        addMouseListener(new MouseAdapter() {
           
           @Override
           public void mouseClicked(MouseEvent e){
               if(getBackground() == Color.yellow)                                        
                        setBackground(Color.green);                              
           }
        });              
        

    }
   
    
    
    @Override
    protected Document createDefaultModel() {
        return new LimitDocument();
    }

    /**
     * Pomocna funkcia getSignal vrati hodnotu premennej value.
     * @return 
     */
    public byte getSignal() {
        return value;
    }

    /**
     * Trieda LimitDocument sluzi na limitovanie poctu znakov v bunke na 
     * hodnotu limit.
     */
    private class LimitDocument extends PlainDocument {

        @Override
        public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
            if (str == null) return;

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }       

    }
   
    @Override
    public void mouseClicked(MouseEvent e) {
        value = 1;
    }

    @Override
    public void mousePressed(MouseEvent e) {    }

    @Override
    public void mouseReleased(MouseEvent e) {    }

    @Override
    public void mouseEntered(MouseEvent e) {    }

    @Override
    public void mouseExited(MouseEvent e) {    }
   
    /**
     * Pomocna funkcia setSignal nastavi hodnotu premennej value na 1.
     */
    public void setSignal(){
        value =1;
    }
}
