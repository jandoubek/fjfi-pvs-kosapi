
package cz.fjfi.pvs.kosapi.web;

/**
 * Class base from http://www.mkyong.com/java/java-https-client-httpsurlconnection-example/
 * @author pvs-kosapi-team
 */


import java.io.*;
 
import javax.net.ssl.HttpsURLConnection;
import org.apache.commons.codec.binary.Base64;

import org.apache.log4j.Logger;
 
public class HttpsClient{
    
    static Logger logger = Logger.getLogger(HttpsClient.class);
    
    private String name;
    private String password;
    
    
 
    public  HttpsClient(String name, String password) throws IOException
    {
        setName(name);
        setPassword(password);
    }

    public String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public final void setPassword(String password) {
        this.password = password;
    }

    public String getUrl(URL url) throws IOException {
        HttpsURLConnection connection = establishConnection(url);
        checkResponseCode(connection.getResponseCode());
        return readContent(connection);
    }
    
    private HttpsURLConnection establishConnection(URL url) throws IOException{
        logger.info("Connecting to url: " + url.toString());
        logger.info("Connecting with username: " + getName());
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
        logger.info("Checking status code: " + statusCode);
        if (statusCode >= 400 && statusCode < 600) {
            String msg = "Request ends up with status code: " + statusCode;
            throw new WebHttpException(msg, statusCode);
        }
    }
 
    private String readContent(HttpsURLConnection connection) throws IOException{
        logger.info("Reading content from response;");
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
