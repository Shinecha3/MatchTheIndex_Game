package lib.panel;

import java.awt.*;
import java.io.File;

import javax.swing.*;
import java.net.URL;

public class panelStart extends JPanel {

    private JButton startButton;
    private JButton start2Button;
    private Image backgroundImage;

    public panelStart() {

        setLayout(null);

        try {
            URL imageURL = getClass().getClassLoader().getResource("img/setBG.jpg");
            if (imageURL != null) {
                backgroundImage = new ImageIcon(imageURL).getImage();
                System.out.println(" Loaded background image: " + imageURL);
            } else {
                System.out.println(" Image not found at: img/setBG.png");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ปุ่ม Easy
        startButton = new JButton("Easy");
        startButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        try {
            Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Pixelette.ttf")).deriveFont(Font.BOLD, 22);
            startButton.setFont(pixelFont);
        } catch (Exception e) {
            e.printStackTrace();;
        }
        //startButton.setBackground(new Color(255, 255, 255, 255)); // โปร่งนิดหน่อย
        startButton.setFocusPainted(false);
        startButton.setBorderPainted(false);
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);
        startButton.setBounds(20, 200, 120, 55); // <<< ปรับตำแหน่งตามภาพคุณ
        add(startButton);

        // ปุ่ม Hard
        start2Button = new JButton("Hard");
        start2Button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        try {
            Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Pixelette.ttf")).deriveFont(Font.BOLD, 22);
            start2Button.setFont(pixelFont);
        } catch (Exception e) {
            e.printStackTrace();;
        }
        start2Button.setBackground(Color.BLACK);
        start2Button.setForeground(Color.WHITE);
        //start2Button.setBackground(new Color(255, 255, 255, 255));
        start2Button.setFocusPainted(false);
        start2Button.setBorderPainted(false);
        start2Button.setBounds(340, 300, 120, 50); // <<< ปรับตำแหน่งให้ตรงกับ Easy
        add(start2Button);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // วาดภาพพื้นหลังให้เต็มจอ
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    public JButton getStartButton() { return startButton; }
    public JButton getStartHorseButton() { return start2Button; }
}
