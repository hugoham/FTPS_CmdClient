package be.district09.sf.gui;

import be.district09.sf.config.Settings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class ConfigWindow {
    public static Settings display() {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Configuration settings");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets( 10,10,10,10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setGridLinesVisible(true); // for testing

        Label remoteHostLabel = new Label("Remote Host");
        gridPane.add(remoteHostLabel, 0,0);
        Label remotePortLabel = new Label("Remote Port");
        gridPane.add(remotePortLabel, 2,0);
        Label userNameLabel = new Label("Username");
        gridPane.add(userNameLabel, 0,1);
        Label keystoreLabel = new Label("Keystore");
        gridPane.add(keystoreLabel, 0,2);

        TextField remoteHost = new TextField();
        remoteHost.setPrefWidth(300);
        gridPane.add(remoteHost, 1,0);
        TextField remotePort = new TextField();
        remotePort.setPrefWidth(50);
        remotePort.setText("21");
        gridPane.add(remotePort, 3,0);
        TextField userName = new TextField();
        userName.setPrefWidth(300);
        gridPane.add(userName, 1,1);
        TextField keystore = new TextField();
        keystore.setPrefWidth(300);
        gridPane.add(keystore, 1,2);



        Scene scene = new Scene(gridPane);
        window.setScene(scene);
        window.showAndWait();

        Settings settings = new Settings();
        settings.setRemoteHost(remoteHost.getText());

        return settings;
    }

}
