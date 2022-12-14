

package AbstractProductC;


import AbstractProductA.*;
import AbstractProductB.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Timer;
import java.util.TimerTask;
import DomainClasses.DKPorudzbina;  // Promenljivo


public final class Kontroler1 extends Kontroler{
   
    
   
    
    public Kontroler1(EkranskaForma ef1,BrokerBazePodataka bbp1){ef=ef1;bbp=bbp1; Povezi();}
    
    void Povezi()
    {javax.swing.JButton Kreiraj = ef.getPanel().getKreiraj();
     javax.swing.JButton Promeni = ef.getPanel().getPromeni();
     javax.swing.JButton Obrisi = ef.getPanel().getObrisi();
     javax.swing.JButton Nadji = ef.getPanel().getNadji();
     Kreiraj.addActionListener( new OsluskivacKreiraj(this));
     Promeni.addActionListener( new OsluskivacPromeni(this));
     Obrisi.addActionListener( new OsluskivacObrisi(this));
     Nadji.addActionListener( new OsluskivacNadji(this));
     
     javax.swing.JTextField SifraPorudzbine = ef.getPanel().getSifraPorudzbine1(); // Promenljivo!!!
     SifraPorudzbine.addFocusListener( new OsluskivacNadji1(this));
    }
    
// Promenljivo!!!  
void napuniDomenskiObjekatIzGrafickogObjekta()   {
       ip= new DKPorudzbina();
       ip.setSifraPorudzbine(getInteger(ef.getPanel().getSifraPorudzbine()));
       ip.setPalacinka(ef.getPanel().getPalacinka());
       ip.setPreliv(ef.getPanel().getPreliv());
       ip.setVoce(ef.getPanel().getVoce());
   
    
    }

// Promenljivo!!!
void napuniGrafickiObjekatIzDomenskogObjekta(DKPorudzbina ip){
       ef.getPanel().setSifraPorudzbine(Integer.toString(ip.getSifraPorudzbine()));
       ef.getPanel().setPalacinka(ip.getPalacinka());
       ef.getPanel().setPreliv(ip.getPreliv());
       ef.getPanel().setVoce(ip.getVoce());

     //  System.out.println(ip.getPalacinka());
      
    }

// Promenljivo!!!
void isprazniGrafickiObjekat(){

 ef.getPanel().setSifraPorudzbine("");
 ef.getPanel().setPalacinka("nista");
 ef.getPanel().setPreliv("nista");
 ef.getPanel().setVoce("nista");
 //ef.getPanel().setDatum(new java.util.Date());
}

void prikaziPoruku() 
{ ef.getPanel().setPoruka(poruka);
      
  Timer timer = new Timer();
  
  timer.schedule(new TimerTask() {
  @Override
  public void run() {
    ef.getPanel().setPoruka(""); 
  }
}, 3000);
  
}

 public int getInteger(String s) {
    int broj = 0;
    try
        {
            if(s != null)
                broj = Integer.parseInt(s);
        }
            catch (NumberFormatException e)
            { broj = 0;}
   
    return broj;
}


 
boolean zapamtiDomenskiObjekat(){ 
    
    bbp.makeConnection();
    boolean signal = bbp.insertRecord(ip);
    if (signal==true) 
        { bbp.commitTransation();
          poruka ="?????? ?? ???????? ???? ?????????."; // Promenljivo!!!
        }
        else
        { bbp.rollbackTransation();
          poruka ="?????? ?? ???? ?? ??????? ???? ?????????."; // Promenljivo!!!  
        }
    prikaziPoruku();
    bbp.closeConnection();
    return signal; 
       
    }
    
boolean kreirajDomenskiObjekat(){
    boolean signal;
    ip= new DKPorudzbina(); // Promenljivo!!!
    AtomicInteger counter = new AtomicInteger(0);
    
    bbp.makeConnection();
    if (!bbp.getCounter(ip,counter)) return false;
    if (!bbp.increaseCounter(ip,counter)) return false;
          
    ip.setSifraPorudzbine(counter.get()); // Promenljivo!!!
    signal = bbp.insertRecord(ip);
    if (signal==true) 
        { bbp.commitTransation();
          napuniGrafickiObjekatIzDomenskogObjekta(ip);
          poruka = "?????? ?? ??????? ???? ?????????."; // Promenljivo!!!
        }
        else
        { bbp.rollbackTransation();
        isprazniGrafickiObjekat();
        poruka ="?????? ?? ???? ?? ?????? ???? ?????????."; // Promenljivo!!!
        }
    prikaziPoruku();
    bbp.closeConnection();
    return signal;
}

boolean obrisiDomenskiObjekat(){
    bbp.makeConnection();
    boolean signal = bbp.deleteRecord(ip);
    if (signal==true) 
        { bbp.commitTransation();
          poruka = "?????? je o?????? ?????????."; // Promenljivo!!!
            isprazniGrafickiObjekat();
        }
        else
        { bbp.rollbackTransation();
          poruka = "?????? ?? ???? ?? ?????? ?????????."; // Promenljivo!!!
        }
    prikaziPoruku();
    bbp.closeConnection();
    return signal;   
}

boolean promeniDomenskiObjekat(){
    bbp.makeConnection();
    boolean signal = bbp.updateRecord(ip);
    if (signal==true) 
        { bbp.commitTransation();
          poruka = "?????? je ???????? ?????????."; // Promenljivo!!!
        }
        else
        { bbp.rollbackTransation();
          isprazniGrafickiObjekat();
          poruka = "?????? ?? ???? ?? ??????? ?????????."; // Promenljivo!!!
        }
    prikaziPoruku();
    bbp.closeConnection();
    return signal;   
}


boolean nadjiDomenskiObjekat(){
    boolean signal;
    bbp.makeConnection();
    ip = (DKPorudzbina)bbp.findRecord(ip); // Promenljivo!!!
    if (ip != null) 
        { napuniGrafickiObjekatIzDomenskogObjekta(ip);
          poruka = "?????? je ????? ?????????."; // Promenljivo!!!
          signal = true;
        }
        else
        { isprazniGrafickiObjekat();
          poruka ="?????? ?? ???? ?? ???? ?????????."; // Promenljivo!!!
          signal = false;
        }
    prikaziPoruku();
    bbp.closeConnection();
    return signal;   
}

}

class OsluskivacZapamti implements ActionListener
{   Kontroler1 kon;
 
    OsluskivacZapamti(Kontroler1 kon1) {kon = kon1;}
    
@Override
    public void actionPerformed(ActionEvent e) {
         kon.napuniDomenskiObjekatIzGrafickogObjekta();
         kon.zapamtiDomenskiObjekat();
        
    }
}

class OsluskivacKreiraj implements ActionListener
{   Kontroler1 kon;
 
    OsluskivacKreiraj(Kontroler1 kon1) {kon = kon1;}
    
@Override
    public void actionPerformed(ActionEvent e) {
         kon.kreirajDomenskiObjekat();
         
        
    }
}

class OsluskivacObrisi implements ActionListener
{   Kontroler1 kon;
 
    OsluskivacObrisi(Kontroler1 kon1) {kon = kon1;}
    
@Override
    public void actionPerformed(ActionEvent e) {
         kon.napuniDomenskiObjekatIzGrafickogObjekta();
         kon.obrisiDomenskiObjekat();
        
    }
}

class OsluskivacPromeni implements ActionListener
{   Kontroler1 kon;
 
    OsluskivacPromeni(Kontroler1 kon1) {kon = kon1;}
    
@Override
    public void actionPerformed(ActionEvent e) {
         kon.napuniDomenskiObjekatIzGrafickogObjekta();
         kon.promeniDomenskiObjekat();
        
    }
}

class OsluskivacNadji implements ActionListener
{   Kontroler1 kon;
 
    OsluskivacNadji(Kontroler1 kon1) {kon = kon1;}
    
@Override
    public void actionPerformed(ActionEvent e) {
         kon.napuniDomenskiObjekatIzGrafickogObjekta();
         kon.nadjiDomenskiObjekat();
        
    }
}

class OsluskivacNadji1 implements FocusListener
{   Kontroler1 kon;
 
    OsluskivacNadji1(Kontroler1 kon1) {kon = kon1;}
    

    public void focusLost(java.awt.event.FocusEvent e) {
         kon.napuniDomenskiObjekatIzGrafickogObjekta();
         kon.nadjiDomenskiObjekat();
        
    }

    @Override
    public void focusGained(FocusEvent e) {
        
    }
}

