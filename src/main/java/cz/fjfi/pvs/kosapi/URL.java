/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fjfi.pvs.kosapi;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLConnection;

/**
 *
 * @author pvs-kosapi-team
 */
public class URL {
    private java.net.URL url;
    
    public URL(String url) throws MalformedURLException{
        this.url = new java.net.URL(url);
    }
    
    public URLConnection openConnection() throws IOException{
        return url.openConnection();
    }
}
