

package AbstractFactory;

import AbstractProductA.*;
import AbstractProductB.*;
import AbstractProductC.*;

public interface Projektant {
       EkranskaForma kreirajEkranskuFormu();   
       BrokerBazePodataka kreirajBrokerBazePodataka ();
       Kontroler kreirajKontroler (EkranskaForma ef,BrokerBazePodataka dbbr);   
}
