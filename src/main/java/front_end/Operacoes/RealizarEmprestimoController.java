package front_end.Operacoes;

import back_end.contexto.ContextoAplicacao;
import back_end.dominio.Agencia;
import back_end.dominio.Cliente;
import back_end.dominio.Cupom;
import back_end.dominio.Emprestimo;
import back_end.repositorio.AgenciaRepositorio;
import back_end.repositorio.ClienteRepositorio;
import front_end.Menu.Menu;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RealizarEmprestimoController{
    @FXML
    public TextField cod_field;
    public Label successMSg;
    public Label cuponMSG;
    public TextField valor_field;
    public TextField parcelas_field;
    public ChoiceBox clientes_select;
    public ChoiceBox agencia_select;

    @FXML
    public void initialize() {

        List<Agencia> agencias = ((AgenciaRepositorio) ContextoAplicacao.getModulo("agenciaRepositorio")).listar();
        for (int i = 0; i < agencias.size(); i++){
            agencia_select.getItems().add(agencias.get(i));
        }
        List<Cliente> clientes = ((ClienteRepositorio) ContextoAplicacao.getModulo("clienteRepositorio")).listar();
        for (int i = 0; i < clientes.size(); i++){
            clientes_select.getItems().add(clientes.get(i));
        }

    }

    private void fieldsNull(){

        cod_field.setText("");
        valor_field.setText("");
        parcelas_field.setText("");

    }

    @FXML
    public void submit(){

        Set<Cliente> clientes = new HashSet<>();
        clientes.add((Cliente) clientes_select.getValue());
        Emprestimo emprestimo = new Emprestimo(
                Long.valueOf(cod_field.getText()),
                clientes,
                (Agencia) agencia_select.getValue(),
                Double.valueOf(valor_field.getText()),
                Integer.valueOf(parcelas_field.getText())
        );
        // Gerar cupom caso a operação seja de valor superior a 5000
        if(Double.valueOf(valor_field.getText()) > 5000){
            Random ran = new Random();
            Long id = ran.nextLong(500);
            LocalDate data = LocalDate.now().plusMonths(1);
            Cupom cupom = new Cupom(id,data);
            Menu.cupons.add(cupom);
            cuponMSG.setText("!!! Um cupom foi gerado !!!");
        }
        successMSg.setText("Emprestimo Realizado com Sucesso!");
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
