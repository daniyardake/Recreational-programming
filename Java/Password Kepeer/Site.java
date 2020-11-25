/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package passkeeper;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author daniyar
 */
public class Site {
    
    int siteId;
    String website;
    String login;
    String password;
    
    
    public Site() {
    }
    
    
    
    public Site(int siteId, String website, String login, String password) {
        this.siteId = siteId;
        this.website = website;
        this.login = login;
        this.password = password; 
    }
    
    
    String getSiteURL() {
        return website;
    }
    
    String getSiteLogin() {
        return login;
    }
    
    String getSitePassword() {
        return password;
    }
    
    int getSiteId(){
        return siteId;
    }
    
    void setSiteURL(String website){
        this.website = website;
    }
    
    void setSiteLogin(String login){
        this.login = login;
    }
    
    void setSitePassword(String password){
        this.password = password;
    }
    
    void setSiteId(int siteId){
        this.siteId = siteId;
    }

}
