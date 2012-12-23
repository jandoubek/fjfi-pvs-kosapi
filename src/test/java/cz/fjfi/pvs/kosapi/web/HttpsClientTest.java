/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fjfi.pvs.kosapi.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.net.ssl.HttpsURLConnection;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author edariedl
 */
public class HttpsClientTest {
    @BeforeClass
    public static void setup() {
        java.net.URL log4Jresource = HttpsClientTest.class.getResource("/cz/fjfi/pvs/kosapi/logger.properties");
        PropertyConfigurator.configure(log4Jresource);
    }

    /**
     * Test of getName method, of class HttpsClient.
     */
    @Test
    public void shouldGetExpectedDataForCorrectCredentials() throws IOException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<atom:feed><atom:id>https://kosapi.fit.cvut.cz/api/3b/courses</atom:id><atom:updated>2012-12-02T14:32:19.343</atom:updated></atom:feed>";
        InputStream is = new ByteArrayInputStream(xml.getBytes());
        URL url = mock(URL.class);
        HttpsURLConnection connection = mock(HttpsURLConnection.class);
        when(url.openConnection()).thenReturn(connection);
        when(connection.getResponseCode()).thenReturn(200);
        when(connection.getInputStream()).thenReturn(is);
        HttpsClient client = new HttpsClient("login", "password");
        Assert.assertEquals(xml + "\n", client.getUrl(url));
    }
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void shouldRaiseExceptionIfCredentialsAreWrong() throws IOException{
        thrown.expect(WebHttpException.class);
        thrown.expectMessage("Request ends up with status code: 401");
        
        URL url = mock(URL.class);
        HttpsURLConnection connection = mock(HttpsURLConnection.class);
        when(connection.getResponseCode()).thenReturn(401);
        when(url.openConnection()).thenReturn(connection);
        HttpsClient client = new HttpsClient("bad_login", "bad_password");
        String data = client.getUrl(url);
    }
    
    @Test
    public void shouldRaiseExceptionIfpageNotFound() throws IOException {
        thrown.expect(WebHttpException.class);
        thrown.expectMessage("Request ends up with status code: 404");
            
        URL url = mock(URL.class);
        HttpsURLConnection connection = mock(HttpsURLConnection.class);
        when(connection.getResponseCode()).thenReturn(404);
        when(url.openConnection()).thenReturn(connection);
        HttpsClient client = new HttpsClient("login", "password");
        String data = client.getUrl(url);
    }
}
