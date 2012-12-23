package cz.fjfi.pvs.kosapi.web;

import java.io.IOException;
/**
 *
 * @author pvs-kosapi-team
 */
public class KosapiClient {
    private HttpsClient client;
    private String baseUrl;
    
    public KosapiClient(HttpsClient client, String baseUrl){
        this.client = client;
        setBaseUrl(baseUrl);
    }
    
    public void setName(String name){
        client.setName(name);
    }
    
    public String getName(){
        return client.getName();
    }
    
    public void setPassword(String password){
        client.setPassword(password);
    }
    
    public final void setBaseUrl(String baseUrl){
        this.baseUrl = baseUrl;
    }
    
    public String getBaseUrl(){
        return baseUrl;
    }
    
    public String getBaseUrlWithResource(String resource){
        return getBaseUrl() + resource;
    };
    
    public String getResource(String resource) throws IOException{
        URL url = new URL(getBaseUrlWithResource(resource));
        return client.getUrl(url);
    }
    
}
