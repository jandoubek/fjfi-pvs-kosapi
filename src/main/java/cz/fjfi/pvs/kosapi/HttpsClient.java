
package cz.fjfi.pvs.kosapi;

/**
 * Class base from http://www.mkyong.com/java/java-https-client-httpsurlconnection-example/
 * @author pvs-kosapi-team
 */


import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.io.*;
 
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.http.HTTPException;

import org.apache.commons.codec.binary.Base64;
 
public class HttpsClient{
    
    private String name;
    private String password;
    private String baseUrl;
 
    public  HttpsClient(String name, String password, String baseUrl) throws IOException
    {
        setName(name);
        setPassword(password);
        setBaseUrl(baseUrl);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    
    public String getBaseUrlWithResource(String resource){
        return getBaseUrl() + resource;
    };
 
    public String getResource(String resource) throws IOException {   
        String https_url = getBaseUrlWithResource(resource);
        HttpsURLConnection connection = establishConnection(https_url);

        //dumpl all cert info
        checkResponseCode(connection.getResponseCode());
        
        //dump all the content
        return readContent(connection);
    }
    
    private HttpsURLConnection establishConnection(String https_url) throws IOException{
        URL url = new URL(https_url);
      
        String authStringEnc = computeAuthString();
            
        HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
        connection.setRequestProperty("Authorization", "Basic " + authStringEnc);
        return connection;
    }
    
    private String computeAuthString(){
        String authString = getName() + ":" + getPassword();
        byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
        return new String(authEncBytes);
    }
    
    private void checkResponseCode(int statusCode) {
        if (statusCode >= 400 && statusCode < 600) {
            String msg = "Request ends up with status code: " + statusCode;
            throw new WebHttpException(msg, statusCode);
        }
    }
 
    private String readContent(HttpsURLConnection connection) throws IOException{
        if(connection == null){
            return "";
        }
        			
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        
        StringBuilder content = new StringBuilder();
        String line;
        
        while ((line = br.readLine()) != null){
            content.append(line);
            content.append("\n");
        }
        br.close();


       return content.toString();
 
   }
 
}
