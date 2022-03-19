package front_end.Contas;

import back_end.contexto.ContextoAplicacao;
import back_end.dominio.Agencia;
import back_end.dominio.Conta;
import back_end.repositorio.AgenciaRepositorio;
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

public class ListConta extends Application implements Initializable {

    public TableView<Conta> tabela;
    public TableColumn<Conta, Integer> col_Agencia;
    public TableColumn<Conta, Integer> col_prop;
    public TableColumn<Conta, Double> col_saldo;
    public TableColumn<Conta, Double> col_taxa;
    public TableColumn<Conta, LocalDate> col_date;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col_Agencia.setCellValueFactory(
                new PropertyValueFactory<>("agencia"));
        col_prop.setCellValueFactory(
                new PropertyValueFactory<>("proprietarios"));
        col_saldo.setCellValueFactory(
                new PropertyValueFactory<>("saldo"));
        col_taxa.setCellValueFactory(
                new PropertyValueFactory<>("tarifaMensal"));
        col_date.setCellValueFactory(
                new PropertyValueFactory<>("dataUltimoAcesso"));
        //tabela.setItems(listaContas());
    }
    /*private ObservableList<Conta> listaContas() {
        // contas =
        return FXCollections.observableArrayList(contas);
    }*/

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListContaView.fxml"));
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

