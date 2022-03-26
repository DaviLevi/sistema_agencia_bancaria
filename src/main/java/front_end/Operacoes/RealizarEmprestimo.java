package front_end.Operacoes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RealizarEmprestimo extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("realizarEmprestimo.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),600, 240);
        primaryStage.setTitle("Cadastro de Cliente");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
