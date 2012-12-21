package cz.fjfi.pvs.kosapi.web;

import java.io.IOException;
import java.util.Properties;

public class KosAtomReader {
	private String response;
	
	public KosAtomReader(String resource) throws IOException
	{
		Properties settings = loadProperties("credentials");
        
        String name = settings.getProperty("kosapi.name");
        String password =  settings.getProperty("kosapi.password");
        String baseUrl = settings.getProperty("kosapi.base_url");
        
        HttpsClient webClient = new HttpsClient(name, password);
        KosapiClient kosapiClient = new KosapiClient(webClient, baseUrl);
        
        response = kosapiClient.getResource(resource);
	}
	
	public String getKosResponse()
	{
		return response;
	}

	private Properties loadProperties(String properties) throws IOException{
        String fullPropertiesName = properties + ".properties";
        Properties settings = new Properties();
        settings.load(this.getClass().getResourceAsStream(fullPropertiesName));
        return settings;
    }
}
