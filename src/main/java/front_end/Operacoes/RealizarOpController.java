package front_end.Operacoes;

import back_end.contexto.ContextoAplicacao;
import back_end.dominio.Conta;
import back_end.dominio.Cupom;
import back_end.dominio.OperacaoBancaria;
import back_end.repositorio.ContaRepositorio;
import front_end.Menu.Menu;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class RealizarOpController {

    @FXML
    private ChoiceBox Contas_select;
    public ChoiceBox type_select;
    public Button enviar_btn;
    public TextArea descri_field;
    public TextField valor_field;
    public TextField cod_field;
    public Label successMSg;

    @FXML
    public void initialize() {

        type_select.getItems().add("CREDITO");
        type_select.getItems().add("DEBITO");
        List<Conta> contas = ((ContaRepositorio) ContextoAplicacao.getModulo("contaRepositorio")).listar();
        for (int i = 0; i < contas.size(); i++){
            Contas_select.getItems().add(contas.get(i));
        }

    }

    private void fieldsNull(){

        descri_field.setText("");
        valor_field.setText("");
        cod_field.setText("");

    }

    @FXML
    public void submit(){

        OperacaoBancaria op = new OperacaoBancaria(
                Long.valueOf(cod_field.getText()),
                (Conta) Contas_select.getValue(),
                (String)type_select.getValue(),
                descri_field.getText(),
                Double.valueOf(valor_field.getText())
        );
        ((Conta) Contas_select.getValue()).setDataUltimoAcesso(LocalDate.now());
        // Gerar cupom caso operação seja superior a 5000
        if(Double.valueOf(valor_field.getText()) > 5000){
            Random ran = new Random();
            Long id = ran.nextLong(500);
            LocalDate data = LocalDate.now().plusMonths(1);
            Cupom cupom = new Cupom(id,data);
        }
        successMSg.setText("Operação realizada com Sucesso");
        this.fieldsNull();
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
