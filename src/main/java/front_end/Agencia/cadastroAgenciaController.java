package front_end.Agencia;
import back_end.contexto.ContextoAplicacao;
import back_end.dominio.Agencia;

import back_end.repositorio.AgenciaRepositorio;
import back_end.repositorio.impl.AgenciaRepositorioEmMemoriaImpl;
import front_end.Menu.Menu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import back_end.modulos.CadastroAgencia;
import java.io.IOException;

public class cadastroAgenciaController {



    int id = 0;
    @FXML
    private Label teste;
    public TextField number_field;
    public TextField city_field;
    public TextField est_field;


    @FXML
    protected void Submit(){
        teste.setText("Cadastro realizado");
        Agencia agencia = new Agencia(Integer.valueOf(number_field.getText()),city_field.getText(),est_field.getText());
        ((AgenciaRepositorio)ContextoAplicacao.getModulo("agenciaRepositorio")).salva(agencia);
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
