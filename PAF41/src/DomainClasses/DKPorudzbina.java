

package DomainClasses;


import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

// Promenljivo!!!
public class DKPorudzbina implements Serializable, GeneralDObject {
  
    private int SifraPorudzbine;
    private String Palacinka;
    private String Preliv;
    private String Voce;
   // private java.util.Date Datum;
    
    public DKPorudzbina() 
    {   SifraPorudzbine=0;			   
        Palacinka="000000";			   
        Preliv = "000";			   
        Voce = "000";			   
        //SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        //Date dDatum = new Date();
        //Datum = java.sql.Date.valueOf(sm.format(dDatum)); 
    }

    public DKPorudzbina(int SifraPorudzbine, String Palacinka, String Preliv, String Voce)  	
    {   this.SifraPorudzbine = SifraPorudzbine;
        this.Palacinka=Palacinka;
        this.Preliv = Preliv;
        this.Voce = Voce;
    }   
    
    // Primarni kljuc		
    public DKPorudzbina(int SifraPorudzbine)  	
    {   this.SifraPorudzbine = SifraPorudzbine;
    }

    public int getSifraPorudzbine(){ return SifraPorudzbine;} 
    public String getPalacinka(){ return Palacinka;} 
    public String getPreliv(){ return Preliv;} 
    public String getVoce(){ return Voce;} 
   // public Date getDatum(){ return Datum;} 

    public void setSifraPorudzbine(int SifraPorudzbine){this.SifraPorudzbine = SifraPorudzbine;}
    public void setPalacinka(String palacinka){this.Palacinka = palacinka;}
    public void setPreliv(String preliv){this.Preliv = preliv;}
    public void setVoce(String voce){this.Voce = voce;}
    //public void setDatum(Date Datum){this.Datum = Datum;}
    @Override
    public String getNameByColumn(int column)
        { String names[] = {"SifraPorudzbine","Palacinka","Preliv","Voce"}; 
          return names[column];
        }		
 
    @Override
    public GeneralDObject getNewRecord(ResultSet rs)  throws SQLException
    {return new DKPorudzbina(rs.getInt("SifraPorudzbine"),rs.getString("Palacinka"),rs.getString("Preliv"),rs.getString("Voce"));} 
    @Override
    public String getAtrValue() {return SifraPorudzbine + ", " + (Palacinka == null ? null : "'" + Palacinka + "'") + ", " + (Preliv == null ? null : "'" + Preliv + "'") + ", " + (Voce == null ? null : "'" + Voce + "'") + "";}
    @Override
    public String setAtrValue(){return "SifraPorudzbine=" + SifraPorudzbine + ", " + "Palacinka=" + (Palacinka == null ? null : "'" + Palacinka + "'") + ", " + "Preliv=" + (Preliv == null ? null : "'" + Preliv + "'") + ", " + "Voce=" + (Voce == null ? null : "'" + Voce + "'") + "";}
    @Override
    public String getClassName(){return "DKPorudzbina";}
    @Override
    public String getWhereCondition(){return "SifraPorudzbine = " + SifraPorudzbine;}
}



    
    
    
