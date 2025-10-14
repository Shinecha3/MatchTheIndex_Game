package lib.panel;

import java.awt.*;
import javax.swing.*;

public class panelStart extends JPanel {

    private JButton startButton;
    private JButton start2Button;


    public panelStart() {
        this.setLayout(new BorderLayout());

        JLabel title = new JLabel("Welcome to Kuromi Match!", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(title, BorderLayout.CENTER);

        startButton = new JButton(" Easy ");
        start2Button = new JButton(" HARD");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(startButton);
        buttonPanel.add(start2Button);

        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    public JButton getStartButton() { return startButton; }
    public JButton getStartHorseButton() { return start2Button; }
}
