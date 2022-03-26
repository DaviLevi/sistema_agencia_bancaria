package front_end.Menu;

import back_end.contexto.ContextoAplicacao;
import back_end.recuperacao.Recuperaveis;
import back_end.recuperacao.Recuperavel;
import back_end.repositorio.AgenciaRepositorio;
import back_end.repositorio.ClienteRepositorio;
import back_end.repositorio.ContaRepositorio;
import back_end.repositorio.FuncionarioRepositorio;
import back_end.repositorio.impl.AgenciaRepositorioEmMemoriaImpl;
import back_end.repositorio.impl.ClienteRepositorioEmMemoriaImpl;
import back_end.repositorio.impl.ContaRepositorioEmMemoriaImpl;
import back_end.repositorio.impl.FuncionarioRepositorioEmMemoriaImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Menu extends Application {

    private static Recuperaveis recuperaveis;

    public static void main(String[] args) {

        AgenciaRepositorio agenciaDb = new AgenciaRepositorioEmMemoriaImpl();
        ContaRepositorio contasDb = new ContaRepositorioEmMemoriaImpl();
        FuncionarioRepositorio funcionariosDb = new FuncionarioRepositorioEmMemoriaImpl(agenciaDb);
        ClienteRepositorio clientesDb = new ClienteRepositorioEmMemoriaImpl(funcionariosDb);

        recuperaveis = new Recuperaveis(
                List.of(
                        (Recuperavel) agenciaDb,
                        (Recuperavel) contasDb,
                        (Recuperavel) funcionariosDb,
                        (Recuperavel) clientesDb
                )
        );

        recuperaveis.recuperaBackup();

        ContextoAplicacao.insereModulo("agenciaRepositorio", agenciaDb);
        ContextoAplicacao.insereModulo("contaRepositorio", contasDb);
        ContextoAplicacao.insereModulo("funcionarioRepositorio", funcionariosDb);
        ContextoAplicacao.insereModulo("clienteRepositorio", clientesDb);

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),600, 400);
        primaryStage.setTitle("Sistema Agencia Bancaria");
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(event -> {
            recuperaveis.realizaBackup();
        });
        primaryStage.show();
    }
}
