package front_end.Contas;

import back_end.dominio.Cliente;
import back_end.dominio.Conta;
import back_end.dominio.ContaCorrente;
import back_end.dominio.ContaPoupanca;
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
import java.util.Set;

public class CadastrarContaController {

    @FXML
    private Label successMSg;
    public TextField agencia_field;
    public TextField cod_field;
    public TextField saldo_field;
    public ChoiceBox type_select;
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
        successMSg.setText("Cadastro Realizado com Sucesso");
        /*Set<Integer>
        if(type_select.getValue().equals("Conta Corrente")){
            ContaCorrente conta = new ContaCorrente(
                    clientes,
                    0,
                    agencia_field.getText(),
                    LocalDate.now(),
                    Double.valueOf(saldo_field.getText()),
                    LocalDate.now(),
                    Double.valueOf(taxam_field.getText())
            );
        }else{
            ContaPoupanca conta = new ContaPoupanca(
                    clientes,
                    0,
                    agencia_field.getText(),
                    LocalDate.now(),
                    Double.valueOf(saldo_field.getText()),
                    LocalDate.now(),
                    Double.valueOf(taxaj_field.getText())
            );
        }*/

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
