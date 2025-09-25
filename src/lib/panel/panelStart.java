package lib.panel;

import java.awt.*;
import javax.swing.*;

public class panelStart extends JPanel {

    private JButton startButton;
    private JButton startButton2;

    public panelStart() {
        this.setLayout(new BorderLayout());

        JLabel title = new JLabel("Welcome to Kuromi Match!", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(title, BorderLayout.CENTER);

        startButton = new JButton("Start Game");
        startButton2 = new JButton("Start Game Math");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        buttonPanel.add(startButton);
        buttonPanel.add(startButton2);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    // ✅ ให้ MainFrame มารับปุ่มนี้ไปใส่ ActionListener ได้
    public JButton getStartButton() {
        return startButton;
    }
    public JButton getStartButton2() {
        return startButton2;
    }
}
