package gui.connection;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class ConnectionPanel extends JPanel {

//    Logger logger = Logger.getLogger("ConnectionPanel");
    public HostPanel hostPanel = new HostPanel();
    public UserPanel userPanel = new UserPanel();
    public KeyStorePanel keyPanel = new KeyStorePanel();

    public ConnectionPanel() {
//        logger.info("ConnectionPanel constructor");

        this.setBackground(Color.lightGray);
        this.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0 ));
        this.setPreferredSize(new Dimension( 1024, 200));
        this.add(hostPanel);
        this.add(userPanel);
        this.add(keyPanel);
    }
}

