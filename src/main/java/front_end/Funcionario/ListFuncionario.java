package front_end.Funcionario;

import back_end.contexto.ContextoAplicacao;
import back_end.dominio.Conta;
import back_end.dominio.Funcionario;
import back_end.repositorio.ContaRepositorio;
import back_end.repositorio.FuncionarioRepositorio;
import front_end.Menu.Menu;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ListFuncionario extends Application implements Initializable {

    @FXML
    public TableView<Funcionario> tabela;
    public TableColumn<Funcionario,Integer> id_col;
    public TableColumn<Funcionario,String> nome_col;
    public TableColumn<Funcionario,String> agencia_col;
    public TableColumn<Funcionario, LocalDate> data_col;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id_col.setCellValueFactory(
                new PropertyValueFactory<>("numeroFuncional")
        );
        nome_col.setCellValueFactory(
                new PropertyValueFactory<>("nome")
        );
        agencia_col.setCellValueFactory(
                new PropertyValueFactory<>("agencia")
        );
        data_col.setCellValueFactory(
                new PropertyValueFactory<>("dataAdmissao")
        );
        tabela.setItems(listaFuncionario());
    }

    private ObservableList<Funcionario> listaFuncionario() {
        List<Funcionario> contas = ((FuncionarioRepositorio) ContextoAplicacao.getModulo("funcionarioRepositorio")).listar();
        return FXCollections.observableArrayList(contas);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListFuncionarioView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),600, 240);
            primaryStage.setTitle("Listagem");
            primaryStage.setScene(scene);
            primaryStage.show();
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

