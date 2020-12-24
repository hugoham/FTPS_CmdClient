package gui.connection;

import gui.FieldBuilder;
import gui.LabelBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class UserPanel extends JPanel {

    Logger logger = Logger.getLogger("UserPanel");
    public JTextField userName;

    public UserPanel() {
        logger.info("UserPanel Constructor");

        userName = new FieldBuilder().build();
        this.setLayout(new FlowLayout(FlowLayout.LEADING, 10,10));
        this.setPreferredSize(new Dimension(1024, 50));
        this.add(new LabelBuilder("User").build());
        this.add(userName);
    }
}
