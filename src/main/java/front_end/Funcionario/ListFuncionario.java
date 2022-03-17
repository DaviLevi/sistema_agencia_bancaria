package front_end.Funcionario;

import front_end.Menu.Menu;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ListFuncionario extends Application {

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

