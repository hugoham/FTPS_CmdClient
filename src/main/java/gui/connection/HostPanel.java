package gui.connection;

import gui.FieldBuilder;
import gui.LabelBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class HostPanel extends JPanel implements ActionListener {
    Logger logger = Logger.getLogger("HostPanel");
    public JTextField hostName;
    public JTextField portNumber;
    JButton button;

    public HostPanel() {
        logger.info("HostPanel Constructor");

        hostName = new FieldBuilder().build();
        portNumber = new FieldBuilder().width(40).build();

        button = new JButton("Connect");
        button.addActionListener(this);

        this.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
        this.setPreferredSize(new Dimension(1024, 50));
        this.add(new LabelBuilder("Host").build());
        this.add(hostName);
        this.add(new LabelBuilder("Port").width(50).build());
        this.add(portNumber);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==button) {

        }
    }
}
