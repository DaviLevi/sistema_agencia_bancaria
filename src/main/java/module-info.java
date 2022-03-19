module com.example.agencia_bancaria {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.agencia_bancaria to javafx.fxml;
    exports com.example.agencia_bancaria;
    exports front_end.Agencia;
    opens front_end.Agencia to javafx.fxml;
    exports front_end.Cliente;
    opens front_end.Cliente to javafx.fxml;
    exports front_end.Contas;
    opens front_end.Contas to javafx.fxml;
    exports front_end.Funcionario;
    opens front_end.Funcionario to javafx.fxml;
    exports front_end.Operacoes;
    opens front_end.Operacoes to javafx.fxml;
    exports front_end.Menu;
    opens front_end.Menu to javafx.fxml;
    opens back_end.dominio;


}