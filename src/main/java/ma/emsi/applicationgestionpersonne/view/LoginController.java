package ma.emsi.applicationgestionpersonne.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ma.emsi.applicationgestionpersonne.dao.impl.DB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {
    @FXML
    private Button cancelbtn;
    @FXML
    private Label loginmessage;
    @FXML
    private TextField emailfield;
    @FXML
    private PasswordField passwordfield;


    public void loginButtonOnAction(ActionEvent e) throws IOException {

        if(emailfield.getText().isBlank()== false && passwordfield.getText().isBlank()==false){
//            loginmessage.setText("Your Trying to login");
            validateLogin(e);

        }else{
            loginmessage.setText("Please enter email & password");
        }
    }

    public void cancelButtonOnAction(ActionEvent e){
        Stage stage=(Stage) cancelbtn.getScene().getWindow();
        stage.close();
    }



    public void validateLogin(ActionEvent e){
         Connection conn= DB.getConnection();
         String verifyLogin="SELECT COUNT(1) FROM users WHERE email LIKE '"+ emailfield.getText() +"' AND password LIKE '"+passwordfield.getText()+"'";
         try{
             Statement statement= conn.createStatement();
             ResultSet queryResult= statement.executeQuery(verifyLogin);


             while(queryResult.next()){
                 System.out.println("Hello "+queryResult.getInt(1));
                 if(queryResult.getInt(1)==1){
                     loginmessage.setText("Logged In successfully");
                     Parent homePage = FXMLLoader.load(getClass().getResource("PersonList.fxml"));
                     Scene homePageSceane = new Scene(homePage);
                     Stage listpage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                     listpage.setScene(homePageSceane);
                     listpage.show();
                 }else{
                     loginmessage.setText("Invalid login, please Try again !");
                 }
             }
         } catch (SQLException | IOException event) {
             event.printStackTrace();
             throw new RuntimeException(event);
         }

    }
}
