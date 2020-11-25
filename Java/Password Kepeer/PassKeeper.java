package passkeeper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

public class PassKeeper extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Pass Keeper | Login");

        // Create the registration form grid pane
        GridPane gridPane = loginFormPane();
        // Add UI controls to the registration form grid pane
        loginGUI(gridPane);
        // Create a scene with registration form grid pane as the root node
        Scene scene = new Scene(gridPane, 800, 500);
        // Set the scene in primary stage	
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }

    
    
    private void personalAreaGUI(User user, GridPane gridPane) throws FileNotFoundException, IOException {
        Label headerLabel = new Label("Welcome to your personal Area: ");
        gridPane.add(headerLabel, 0,0);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        headerLabel.setFont(Font.font("Times new Roman", FontWeight.BOLD, 24));
        
        Button addWebsiteButton = new Button ("Add");
        // Add a new site, login, and password, and notes
        //List all logins, passwords, and notes
        //Search for a login based on a site


        gridPane.add(addWebsiteButton, 7, 1);
        user.addWebsite(gridPane, 1, addWebsiteButton);
        
        
        /*
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));


        // Add Email Label
        Label loginLabel = new Label("Login: ");
        gridPane.add(loginLabel, 0, 2);

        // Add Email Text Field
        TextField loginField = new TextField();
        loginField.setPrefHeight(40);
        gridPane.add(loginField, 1, 2);

        // Add Password Label
        Label passwordLabel = new Label("Password : ");
        gridPane.add(passwordLabel, 0, 3);

        // Add Password Field
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gridPane.add(passwordField, 1, 3);

        // Add Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 4, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0,20,0));
*/
        
    }


    

    private void showPrivateArea(User user) throws FileNotFoundException, IOException{
       Stage newStage = new Stage();
       newStage.setTitle(user.getUserLogin()+" | Personal Area ");
       
       
       GridPane gridPane = new GridPane();
       personalAreaGUI(user, gridPane);
       
       Scene newScene = new Scene(gridPane, 1200, 600);
       newStage.setScene(newScene); 
       
       newStage.show();
       
       
 
    }    
    
    
    
    
    
    
    
    
    
    
    private GridPane loginFormPane() {
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        gridPane.setHgap(10);

        // Set the vertical gap between rows
        gridPane.setVgap(10);

        // Add Column Constraints

        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    private void loginGUI(GridPane gridPane) {
        // Add Header
        Label headerLabel = new Label("Login");
        headerLabel.setFont(Font.font("Times new Roman", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));


        // Add Email Label
        Label loginLabel = new Label("Login: ");
        gridPane.add(loginLabel, 0, 2);

        // Add Email Text Field
        TextField loginField = new TextField();
        loginField.setPrefHeight(40);
        gridPane.add(loginField, 1, 2);

        // Add Password Label
        Label passwordLabel = new Label("Password : ");
        gridPane.add(passwordLabel, 0, 3);

        // Add Password Field
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gridPane.add(passwordField, 1, 3);

        // Add Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 4, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0,20,0));

        submitButton.setOnAction((ActionEvent event) -> {
            if(loginField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Oops", "Please enter your Login");
                return;
            }
            else if(passwordField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Oops..."," Please enter your password");
                return;
            }
            else try {
                if(isMatchingCombination(loginField.getText(),passwordField.getText())){
                    //showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Login Successful!", "Welcome "+loginField.getText());
                    
                    FileReader fr = new FileReader("users.txt");
                    Scanner scan = new Scanner(fr);
                    String temp = scan.nextLine();
                    temp = scan.nextLine();
                    int i = 1;
                    while ( !loginField.getText().equals(temp)){
                        temp = scan.nextLine();
                        temp = scan.nextLine();
                        temp = scan.nextLine();
                        i = i+1;
                    }
                    
                    int newId = i;
                    String tempLogin = scan.next();
                    String tempPass = scan.next();
                    
                    Password pass = new Password(tempPass);
                    User user = new User(i,tempLogin, pass);
                    
                    
                    showPrivateArea(user);
                    
                    //MAKE NEW FUNCTION THAT MAKES A NEW STAGE, SCENE, PANE
                    //
                
                
                }
                else {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Oops..."," There is no matching Login or Password in our database");
                    return;
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PassKeeper.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PassKeeper.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    
    private boolean isMatchingCombination(String login, String password) throws FileNotFoundException, IOException {
        
        boolean isMatchingCombination = false;
        
        //java.io.File usersFile = new java.io.File("users.txt");
        
        FileReader fr = new FileReader("users.txt");
        
        Scanner scan = new Scanner(fr);
        int i = 1;

        while(scan.hasNextLine()){
            String tempLogin = scan.nextLine();
            if(i%3==2){
                if(tempLogin.equals(login)){
                    String tempPass = scan.nextLine();
                    if(tempPass.equals(password)){ isMatchingCombination = true; }
                }
            }
            i++;
        }
        
        
        return isMatchingCombination;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}