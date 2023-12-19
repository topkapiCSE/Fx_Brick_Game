module com.example.fx_game {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fx_game to javafx.fxml;
    exports com.example.fx_game;
}