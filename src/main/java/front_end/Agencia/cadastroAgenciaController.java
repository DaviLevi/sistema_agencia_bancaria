package front_end.Agencia;
import back_end.dominio.Agencia;

import front_end.Menu.Menu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class cadastroAgenciaController {

    @FXML
    private Label teste;
    public TextField nome_field;
    public TextField city_field;
    public TextField end_field;


    @FXML
    protected void Submit(){
        teste.setText("Cadastro realizado");
        Agencia agencia = new Agencia(0,city_field.getText(),end_field.getText());
        System.out.println(agencia.getNumeroAgencia());
        System.out.println(agencia.encontraseNaCidade(city_field.getText()));
    }

    @FXML
    public void returnMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Menu.class.getResource("MenuView.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
