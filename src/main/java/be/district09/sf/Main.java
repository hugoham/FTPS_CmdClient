package be.district09.sf;

import be.district09.sf.config.Settings;
import be.district09.sf.gui.ConfigWindow;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<ActionEvent> {

    Settings settings;
    Button connectButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FTPS Client App");

        connectButton = new Button();
        connectButton.setText("connect");

        connectButton.setOnAction(e -> settings = ConfigWindow.display());

        StackPane layout = new StackPane();
        layout.getChildren().add(connectButton);

        Scene scene = new Scene(layout, 1024,800 );

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {

    }
}
