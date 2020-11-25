/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package passkeeper;

import java.io.*;
import java.util.Scanner;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author daniyar
 */
public class User {
    int id;
    String login;
    Password password; //TODO: Password validation
    java.io.File file = new java.io.File("user"+Integer.toString(id)+ ".txt");//when user added file added
    
    
    public User() {
    }
    
    /*
       java.io.File file = new java.io.File("name.txt");
    
    
    
    */

    public User (int id, String login, Password password) throws IOException {
        this.id = id;
        this.login = login;
        this.password = password;
       
        
        java.io.File newUserFile = new java.io.File("user"+id+".txt");
        
        
        
        FileWriter newUserAddtoDatabse = new FileWriter("users.txt", true);
        newUserAddtoDatabse.write(id);
        newUserAddtoDatabse.write(login);
        newUserAddtoDatabse.write(password.toString());
    }
        
    
     
        //add user to file users.txt
        
       
    
    String getUserLogin(){
        return login;
    }
    
    Password getUserPassword(){
        return password;
    }
    
    int getUserId(){
        return id;
    }
    
    void setUserId() throws FileNotFoundException{
        FileReader fr = new FileReader("users.txt");
        
        Scanner scan = new Scanner(fr);
        int i = 1;

        while(scan.hasNextLine()){
            i++;
        }
        id = i/3;
    }
    
    void setUserLogin(String login){
        this.login = login;
    }
    
    void setUserPassword(Password password){
        this.password = password;
    }
    
    void changeUserPassword(Password newPassword){
        password = newPassword;
        replaceLine((id-1)*3+3,newPassword.toString());
    }
    
    void replaceLine (int lineNumber, String newData){
        //TODO: IT OPENS users.txt and replaces line lineNumber by newData
        //TODO: Use FileWriter newUserAddtoDatabse for that
    }
    
    public void addWebsite(GridPane gridPane, int row, Button button) throws FileNotFoundException, IOException{
        
        gridPane.add(new Label ("Add new Website: "), 0, row); 
        
        TextField url = new TextField();
        TextField login = new TextField();
        PasswordField password = new PasswordField();
        
        gridPane.add(new Label ("URL:  "), 1, row); 
        gridPane.add(url, 2, row);
        gridPane.add(new Label ("Login:  "), 3, row); 
        gridPane.add(login, 4, row);
        gridPane.add(new Label ("Password:  "), 5, row); 
        gridPane.add(password, 6, row);
        
        
        Site newSite = new Site(this.getNumberOfWebsites()+1, url.getText(), login.getText(), password.getText());
        //FileWriter newUserAddtoDatabse = new FileWriter( "user" + ((this.getNumberOfWebsites()+1.txt).toString()) +".txt" , true);
        
        
        
        
    }
    
    public void listWebsites(GridPane gridPane){
    //list all websites from userID.txt
    
    
    }
    
    int getNumberOfWebsites() throws FileNotFoundException{
    int number = 0;
    Scanner scan = new Scanner(file);
    
    while(scan.hasNextLine()){
     number++;
    }
    
    number = number / 4;
    return number;
    }
    
    void addSite(Site site) throws IOException{
        
        java.io.File newUserFile = new java.io.File("user"+id+".txt");
        FileWriter writer = new FileWriter("user"+id+".txt");
        
        
        int siteID = numOfLines(newUserFile)/4 + 1;
        writer.write(siteID);
        
        
    //TODO: ADD a new site info to userID.txt     
    }
    
    public int numOfLines(File file) throws FileNotFoundException, IOException{
        
        FileReader find = new FileReader(file);
        
        LineNumberReader lnr = new LineNumberReader(find);
        int lines = 0;
        while (lnr.readLine() != null) {lines++; }
        find.close();
     
        return lines;
    }
    
    
}
