package gui.connection;

import gui.FieldBuilder;
import gui.LabelBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class KeyStorePanel extends JPanel implements ActionListener {

    Logger logger = Logger.getLogger("CertPanel");
    public JTextField keyStore;
    JButton button;

    public KeyStorePanel() {
        logger.info("UserPanel Constructor");

//        ImageIcon icon = new ImageIcon("open.png");

        button = new JButton("Open");
//        button.setIcon(icon);
        button.addActionListener(this);

        keyStore = new FieldBuilder().width(800).build();
        this.setLayout(new FlowLayout(FlowLayout.LEADING, 10,10));
        this.setPreferredSize(new Dimension(1024, 50));
        this.add(new LabelBuilder("Keystore").build());
        this.add(keyStore);
        this.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==button) {
            JFileChooser chooser = new JFileChooser();
            int response = chooser.showDialog(null, "Choose");
            if (response == JFileChooser.APPROVE_OPTION) {
                keyStore.setText(chooser.getSelectedFile().getPath());
            }
        }
    }
}
