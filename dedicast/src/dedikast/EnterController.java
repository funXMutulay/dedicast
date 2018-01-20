/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dedikast;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author macbookpro
 */
public class EnterController implements Initializable {

    /**
     * Initializes the controller class.
     */
        /**
     * Initializes the controller class.
     */
   @FXML
   private Label connLabel;
   @FXML
   private Label insLabel;
   @FXML
   private Label alloLabel;
   @FXML
   private Label statusConLabel;
   @FXML
   private Label emailLabel;
   @FXML
   private Label passLabel;
   @FXML
   private Label confirmPassLabel;
   @FXML
   private TextField idConChamp;
   @FXML
   private String idConChampVar;
    
   @FXML
   private TextField passConChamp;
   @FXML
   private String passConChampVar;
   
   @FXML
   private TextField alloChamp;
   @FXML
   private String alloConChampVar;
   
   @FXML
   private TextField emailChamp;
   @FXML
   private String emailChampVar;
   @FXML
   private TextField passChamp;
   @FXML
   private String passChampVar;
   @FXML
   private TextField confirmPassChamp;
   @FXML
   private String confirmPassChampVar;
   @FXML
   private Button enterButton;
   @FXML
   private Button inscriptionButton;
     
   
   
   @FXML
   private void registerCustomer(ActionEvent event)throws Exception{
       
       
    }    
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Enter.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
     public void enterKingdom(ActionEvent event)throws Exception{
       Stage stage ;
       Parent root ;
       
       
        // TODO
   if( event.getSource()== "enterButton"){
       stage = (Stage) enterButton.getScene().getWindow();
       root = FXMLLoader.load(getClass().getResource("/dedikast/Enter.fxml"));
       
       
   }    else{
       stage = (Stage) enterButton.getScene().getWindow();
       root = (Parent) FXMLLoader.load(getClass().getResource("/dedikast/dedikast.fxml"));
       
       
       statusConLabel.setText("Combinaison invalide , réessayez ou inscrivez vous ! ");
   }     
  
       Scene scene = new Scene(root);
       stage.setTitle("Dedikast Kingdom");
       stage.show();
   
   } 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    
    }    

    public String getIdConChampVar() {
        return idConChampVar = idConChamp.getText();
    }

    public void setIdConChampVar(String idConChampVar) {
        this.idConChampVar = idConChampVar;
    }

    public String getPassConChampVar() {
        return passConChampVar = passConChamp.getText();
    }

    public void setPassConChampVar(String passConChampVar) {
        this.passConChampVar = passConChampVar;
    }

    public String getAlloConChampVar() {
        return alloConChampVar = alloChamp.getText();
    }

    public void setAlloConChampVar(String alloConChampVar) {
        this.alloConChampVar = alloConChampVar;
    }

    public String getEmailChampVar() {
        return emailChampVar = emailChamp.getText();
    }

    public void setEmailChampVar(String emailChampVar) {
        this.emailChampVar = emailChampVar;
    }

    public String getPassChampVar() {
        return passChampVar=passChamp.getText();
    }

    public void setPassChampVar(String passChampVar) {
        this.passChampVar = passChampVar;
    }

    public String getConfirmPassChampVar() {
        return confirmPassChampVar=confirmPassChamp.getText();
    }

    public void setConfirmPassChampVar(String confirmPassChampVar) {
        this.confirmPassChampVar = confirmPassChampVar;
    }

    public Label getStatusConLabel() {
        return statusConLabel;
    }

    public void setStatusConLabel(Label statusConLabel) {
        this.statusConLabel = statusConLabel;
    }
    
}
