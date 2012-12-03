/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fjfi.pvs.kosapi.web;

import java.io.IOException;
import java.net.MalformedURLException;
/**
 *
 * @author pvs-kosapi-team
 */
public class KosapiClient {
    private HttpsClient client;
    private String base_url;
    
    public KosapiClient(HttpsClient client, String base_url){
        this.client = client;
        setBaseUrl(base_url);
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
    
    public void setBaseUrl(String base_url){
        this.base_url = base_url;
    }
    
    public String getBaseUrl(){
        return base_url;
    }
    
    public String getBaseUrlWithResource(String resource){
        return getBaseUrl() + resource;
    };
    
    public String getResource(String resource) throws MalformedURLException, IOException{
        URL url = new URL(getBaseUrlWithResource(resource));
        return client.getUrl(url);
    }
    
}
