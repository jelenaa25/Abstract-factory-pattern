
package AbstractProductB;



import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

// Promenljivo!!!
public class SetProperties {
  public static void main(String[] args) {

	Properties prop = new Properties();
	OutputStream output = null;

	try {

		output = new FileOutputStream("config.properties");

		// set the properties value
		prop.setProperty("driver", "com.mysql.jdbc.Driver");
		prop.setProperty("url", "jdbc:mysql://localhost:3306/paf44");
		prop.setProperty("dbusername", "root");
                prop.setProperty("dbpassword", "root");

		// save properties to project root folder
		prop.store(output, null);

	} catch (IOException io) {
		
	} finally {
		if (output != null) {
			try {
				output.close();
			} catch (IOException e) {
				
			}
		}

	}
  }
}
    
