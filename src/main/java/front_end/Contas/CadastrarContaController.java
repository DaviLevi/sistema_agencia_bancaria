package front_end.Contas;

import back_end.contexto.ContextoAplicacao;
import back_end.dominio.*;
import back_end.repositorio.AgenciaRepositorio;
import back_end.repositorio.ClienteRepositorio;
import back_end.repositorio.ContaRepositorio;
import front_end.Menu.Menu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class CadastrarContaController {

    @FXML
    private Label successMSg;
    public TextField agencia_field;
    public TextField cod_field;
    public TextField saldo_field;
    public ChoiceBox type_select;
    public ChoiceBox agencias_;
    public ChoiceBox clientes_;
    public TextField taxam_field;
    public TextField taxaj_field;
    public TextFlow taxa_mensal;
    public TextFlow taxa_juros;

    @FXML
    public void initialize() {
        type_select.getItems().add("Conta Corrente");
        type_select.getItems().add("Conta Poupança");
        type_select.setOnAction((event) -> {
            contaSelect();
        });
        List<Agencia> agencias = ((AgenciaRepositorio) ContextoAplicacao.getModulo("agenciaRepositorio")).listar();
        for (int i = 0; i < agencias.size(); i++){
            agencias_.getItems().add(agencias.get(i));
        }
        List<Cliente> clientes = ((ClienteRepositorio) ContextoAplicacao.getModulo("clienteRepositorio")).listar();
        for (int i = 0; i < clientes.size(); i++){
            clientes_.getItems().add(clientes.get(i));
        }

    }

    @FXML void contaSelect(){
        if(type_select.getValue().equals("Conta Corrente")){
            taxa_mensal.setVisible(true);
            taxa_juros.setVisible(false);
        }else{
            taxa_mensal.setVisible(false);
            taxa_juros.setVisible(true);
        }
    }

    @FXML
    protected void Submit() {
        Set<Cliente> clientes = new HashSet<>();
        clientes.add((Cliente) clientes_.getValue());
        Random ran = new Random();
        Long n = Long.valueOf(ran.nextLong(10));
        Conta conta;
        if(type_select.getValue().equals("Conta Corrente")){
            conta = new ContaCorrente(
                    clientes,
                    n,
                    (Agencia) agencias_.getValue(),
                    LocalDate.now(),
                    Double.valueOf(saldo_field.getText()),
                    LocalDate.now(),
                    Double.valueOf(taxam_field.getText())
            );
        }else{
            conta = new ContaPoupanca(
                    clientes,
                    n,
                    (Agencia) agencias_.getValue(),
                    LocalDate.now(),
                    Double.valueOf(saldo_field.getText()),
                    LocalDate.now(),
                    Double.valueOf(taxaj_field.getText())
            );
        }
        ((ContaRepositorio) ContextoAplicacao.getModulo("contaRepositorio")).salva(conta);
        successMSg.setText("Cadastro Realizado com Sucesso");

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
