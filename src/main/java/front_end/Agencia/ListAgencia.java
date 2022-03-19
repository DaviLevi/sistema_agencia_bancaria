package front_end.Agencia;

import back_end.contexto.ContextoAplicacao;
import back_end.dominio.Agencia;
import back_end.repositorio.AgenciaRepositorio;
import back_end.repositorio.impl.AgenciaRepositorioEmMemoriaImpl;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class ListAgencia extends Application implements Initializable {

    @FXML
    public TableView<Agencia> tabela;
    public TableColumn<Agencia,String> lstEsta;
    public TableColumn<Agencia,String> lstCity;
    public TableColumn<Agencia,Integer> lstNumber;

    public static void main(String[] args) {launch(args);}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lstNumber.setCellValueFactory(
                new PropertyValueFactory<>("numeroAgencia"));
        lstCity.setCellValueFactory(
                new PropertyValueFactory<>("cidade"));
        lstEsta.setCellValueFactory(
                new PropertyValueFactory<>("estado"));
        tabela.setItems(listaAgencia());
    }

    private ObservableList<Agencia> listaAgencia() {
        List<Agencia> agencias = ((AgenciaRepositorio) ContextoAplicacao.getModulo("agenciaRepositorio")).listar();
        return FXCollections.observableArrayList(agencias);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListAgenciaView.fxml"));
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
