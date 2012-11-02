
package cz.fjfi.pvs.kosapi;

import javax.xml.ws.WebServiceException;

/**
 *
 * @author pvs-kosapi-team
 */
public class WebHttpException extends WebServiceException {
    
    private int statusCode;

    public WebHttpException() {
    }

    public WebHttpException(String msg) {
        super(msg);
    }
    
    public WebHttpException(String msg, int statusCode){
        super(msg);
        this.statusCode = statusCode;
    }
    
    public int getResponseCode(){
        return this.statusCode;
    }
}
