package cz.fjfi.pvs.kosapi;

import java.io.IOException;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        Properties settings = loadProperties("credentials");
        
        String name = settings.getProperty("kosapi.name");
        String password =  settings.getProperty("kosapi.password");
        String base_url = settings.getProperty("kosapi.base_url");
      
        HttpsClient webClient = new HttpsClient(name, password, base_url);
        String response = webClient.getResource("divisions");
        AtomParser atomParser = new AtomParser(response);
    }
    
    public static Properties loadProperties(String properties) throws IOException{
        String fullPropertiesName = properties + ".properties";
        Properties settings = new Properties();
        settings.load(App.class.getResourceAsStream(fullPropertiesName));
        return settings;
    }
}
