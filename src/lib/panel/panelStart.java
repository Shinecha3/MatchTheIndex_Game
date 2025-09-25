package lib.panel;

import java.awt.*;
import javax.swing.*;

public class panelStart extends JPanel {

    private JButton startButton;

    public panelStart() {
        this.setLayout(new BorderLayout());

        JLabel title = new JLabel("Welcome to Kuromi Match!", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(title, BorderLayout.CENTER);

        startButton = new JButton("Start Game");
        this.add(startButton, BorderLayout.SOUTH);
    }

    // ✅ ให้ MainFrame มารับปุ่มนี้ไปใส่ ActionListener ได้
    public JButton getStartButton() {
        return startButton;
    }
}
