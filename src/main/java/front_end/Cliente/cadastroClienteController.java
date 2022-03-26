package front_end.Cliente;

import back_end.contexto.ContextoAplicacao;
import back_end.dominio.Agencia;
import back_end.dominio.Cliente;
import back_end.dominio.Funcionario;
import back_end.repositorio.AgenciaRepositorio;
import back_end.repositorio.ClienteRepositorio;
import front_end.Menu.Menu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class cadastroClienteController {
    @FXML
    private Label teste;
    public TextField nome_field;
    public TextField city_field;
    public TextField end_field;
    public TextField estado_field;
    public TextField document_field;
    public DatePicker date_field;
    public ChoiceBox gerente_field;

    @FXML
    public void initialize() {
        gerente_field.getItems().add("Eduardo");
        gerente_field.getItems().add("Gustavo");
        gerente_field.getItems().add("Fabricia");
    }
    @FXML
    protected void onSend(){
        LocalDate data = date_field.getValue();
        LocalDate date = LocalDate.now();
        List<Agencia> agencias = ((AgenciaRepositorio) ContextoAplicacao.getModulo("agenciaRepositorio")).listar();
        Funcionario funcionario = new Funcionario(10,"Funcionario Teste","8747332",null,date,agencias.get(0));
        Cliente cliente = new Cliente(
                nome_field.getText(),
                document_field.getText(),
                data,
                end_field.getText(),
                city_field.getText(),
                estado_field.getText(),
                funcionario
        );
        ((ClienteRepositorio) ContextoAplicacao.getModulo("clienteRepositorio")).salva(cliente);
        teste.setText("Cadastro realizado");
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
