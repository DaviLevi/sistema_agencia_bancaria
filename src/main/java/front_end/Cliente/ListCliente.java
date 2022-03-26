package front_end.Cliente;

import back_end.contexto.ContextoAplicacao;
import back_end.dominio.Agencia;
import back_end.dominio.Cliente;
import back_end.repositorio.AgenciaRepositorio;
import back_end.repositorio.ClienteRepositorio;
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
import java.util.List;
import java.util.ResourceBundle;

public class ListCliente extends Application implements Initializable {

    @FXML
    public TableView<Cliente> tabela;
    public TableColumn<Cliente,String> col_nome;
    public TableColumn<Cliente,String> col_cpf;
    public TableColumn<Cliente,String> col_cidade;
    public TableColumn<Cliente,String> col_estado;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        col_nome.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
        col_cpf.setCellValueFactory(
                new PropertyValueFactory<>("cpf"));
        col_cidade.setCellValueFactory(
                new PropertyValueFactory<>("cidade"));
        col_estado.setCellValueFactory(
                new PropertyValueFactory<>("estado"));
        tabela.setItems(listaCliente());

    }

    private ObservableList<Cliente> listaCliente() {

        List<Cliente> clientes = ((ClienteRepositorio) ContextoAplicacao.getModulo("clienteRepositorio")).listar();
        return FXCollections.observableArrayList(clientes);

    }

    @Override
    public void start(Stage primaryStage) throws IOException {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListClienteView.fxml"));
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

