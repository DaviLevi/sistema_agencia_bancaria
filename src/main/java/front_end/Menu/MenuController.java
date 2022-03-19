package front_end.Menu;

import java.io.IOException;

import front_end.Agencia.ListAgencia;
import front_end.Agencia.cadastroAgencia;
import front_end.Cliente.ListCliente;
import front_end.Cliente.cadastroCliente;
import front_end.Contas.CadastrarConta;
import front_end.Contas.ListConta;
import front_end.Funcionario.CadastrarFuncionario;
import front_end.Funcionario.ListFuncionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class MenuController {

    @FXML
    public void urlCadastroAgencia(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(cadastroAgencia.class.getResource("cadastroAgenciaView.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void urlListagemAgencia(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(ListAgencia.class.getResource("ListAgenciaView.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void urlCadastroCliente(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(cadastroCliente.class.getResource("cadastroClienteView.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void urlListagemCliente(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(ListCliente.class.getResource("ListClienteView.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void urlCadastroConta(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(CadastrarConta.class.getResource("cadastroContaView.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void urlListagemConta(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(ListConta.class.getResource("ListContaView.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void urlCadastroFuncionario(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(CadastrarFuncionario.class.getResource("cadastroFuncionarioView.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void urlListagemFuncionario(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(ListFuncionario.class.getResource("ListFuncionarioView.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}
