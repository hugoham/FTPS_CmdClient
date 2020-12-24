package gui;

import gui.connection.ConnectionPanel;
import service.FTPS_Service;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class MainFrame extends JFrame {

    Logger logger = Logger.getLogger("MainFrame");
    FTPS_Service service;
    ConnectionPanel connectionPanel = new ConnectionPanel();

    public MainFrame(FTPS_Service service) {

        logger.info("MainFrame constructor");
        this.service = service;

        ImageIcon icon = new ImageIcon("ftp.png");

        this.setIconImage(icon.getImage());
        this.setTitle("FTPS Client");
        this.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
        this.setBackground(Color.white);
        this.setSize(1024,800);
        this.setMinimumSize( new Dimension(1024, 800));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(connectionPanel);
        logger.info(this.getSize().toString());
        this.setVisible(true);

    }
}
