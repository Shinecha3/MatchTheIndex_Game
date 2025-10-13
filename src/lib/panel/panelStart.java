package lib.panel;

import java.awt.*;
import javax.swing.*;

public class panelStart extends JPanel {

    private JButton startButton;
    private JButton startHorseButton;
    private JButton startSlmButton;

    public panelStart() {
        this.setLayout(new BorderLayout());

        JLabel title = new JLabel("Welcome to Kuromi Match!", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(title, BorderLayout.CENTER);

        startButton = new JButton("Start Game ");
        startHorseButton = new JButton(" Pony");
        startSlmButton = new JButton("Selamoon");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(startButton);
        buttonPanel.add(startHorseButton);
        buttonPanel.add(startSlmButton);

        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    public JButton getStartButton() { return startButton; }
    public JButton getStartHorseButton() { return startHorseButton; }
    public JButton getStartSlmButton() { return startSlmButton; }
}
