import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FTPS_Frame extends JFrame {

    FTPS_Frame() {
        ImageIcon icon = new ImageIcon("ftp.png");

        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(Color.ORANGE);
        loginPanel.setSize(1024, 100);
        loginPanel.setLayout(new GridLayout(0,2));

        JLabel userLabel = new JLabel("User");
        loginPanel.add(userLabel);

        Dimension textFieldSize = new Dimension();
        textFieldSize.height = 25;
        textFieldSize.width = 125;

        JTextField userText = new JTextField();
        userText.setMaximumSize(textFieldSize);
        userText.setMinimumSize(textFieldSize);
        loginPanel.add(userText);


        this.setIconImage(icon.getImage());
        this.setTitle("FTPS Client");
        this.setSize(1024,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.add(loginPanel);
    }

}
