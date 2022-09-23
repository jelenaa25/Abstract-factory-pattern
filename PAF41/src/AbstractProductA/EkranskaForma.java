

package AbstractProductA;

import javax.swing.JFrame;


public abstract class EkranskaForma extends JFrame{
    //Panel pan;
    
    public Panel getPanel() {return (Panel)getContentPane();}  
    public void setPanel(Panel pan1) {setContentPane(pan1);//pan=pan1;
        
    }
    public void prikaziEkranskuFormu(){setVisible(true);}
    abstract public void odrediDekoracijuForme();
}
