package front_end.Funcionario;

import back_end.contexto.ContextoAplicacao;
import back_end.dominio.Agencia;
import back_end.dominio.Funcionario;
import back_end.repositorio.AgenciaRepositorio;
import back_end.repositorio.FuncionarioRepositorio;
import front_end.Menu.Menu;
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

public class CadastrarFuncionarioController {
    @FXML
    public TextField nome_field;
    public TextField document_field;
    public TextField telefone_field;
    public TextField id_field;
    public TextField dependente_field;
    public ChoiceBox agencia_select;
    public ChoiceBox supervisor_select;
    public ChoiceBox dependente_select;
    public DatePicker date_field;
    public Label successMSg;


    @FXML
    public void initialize() {

        List<Agencia> agencias = ((AgenciaRepositorio) ContextoAplicacao.getModulo("agenciaRepositorio")).listar();
        for (int i = 0; i < agencias.size(); i++){
            agencia_select.getItems().add(agencias.get(i));
        }
        supervisor_select.getItems().add("Bruno");

    }

    @FXML
    void adicionaDependente(){

        dependente_select.getItems().add(dependente_field.getText());
        dependente_field.setText("");

    }

    private void fieldsNull(){

        nome_field.setText("");
        document_field.setText("");
        telefone_field.setText("");
        id_field.setText("");
        dependente_field.setText("");

    }

    @FXML
    protected void submit(){

        Funcionario supervisor = new Funcionario(1);
        Funcionario funcionario = new Funcionario(
                Integer.valueOf(id_field.getText()),
                nome_field.getText(),
                telefone_field.getText(),
                supervisor,
                date_field.getValue(),
                (Agencia) agencia_select.getValue()
        );

        ((FuncionarioRepositorio)ContextoAplicacao.getModulo("funcionarioRepositorio")).salva(funcionario);
        successMSg.setText("Cadastro realizado");
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
