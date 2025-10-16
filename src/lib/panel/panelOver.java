package lib.panel;
import lib.User_Section.*;
import java.awt.*;
import java.io.File;
import java.net.URL;

import javax.swing.*;
import java.util.List;

public class panelOver extends JPanel {

    private JButton backToMenuButton;
    private JButton restartButton;
    private int currentScore;
    private int highestScore;
    
    private String mode;
    private JPanel scoreboardPanel; // สร้าง scoreboardPanel ใน class
    private JLabel gameOverLabel;
    private JLabel highestScoreLabel;
    private List<User> currentRank;
    private JLabel tmp;
    private Image backgroundImage;

    
    public panelOver(List<User> ranking,String mode) {

        this.mode = mode;
        currentRank = ranking;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false); // ทำให้ JPanel โปร่งใส
        this.add(Box.createVerticalStrut(60));
        try {
            URL imageURL = getClass().getClassLoader().getResource("img/setRank.jpg");
            if (imageURL != null) {
                backgroundImage = new ImageIcon(imageURL).getImage();
                System.out.println("✅ Loaded background image: " + imageURL);
            } else {
                System.out.println("❌ Image not found at: img/setRank.jpg");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // สร้าง scoreboardPanel ครั้งเดียว
        scoreboardPanel = new JPanel();
        scoreboardPanel.setLayout(new BoxLayout(scoreboardPanel, BoxLayout.Y_AXIS));
        scoreboardPanel.setOpaque(false); // ทำให้ scoreboardPanel โปร่งใส

        setRank(mode);

        gameOverLabel = new JLabel("Game Over! You Get "+ currentScore , SwingConstants.CENTER);
        try {
            Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Pixelette.ttf")).deriveFont(Font.BOLD, 22);
            gameOverLabel.setFont(pixelFont);
        } catch (Exception e) {
            e.printStackTrace();;
        }
        gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameOverLabel.setForeground(Color.BLACK); // ตั้งค่าสีตัวอักษรเป็นสีขาว
        gameOverLabel.setOpaque(false); // ทำให้ JLabel โปร่งใส

        
        highestScoreLabel = new JLabel("Your HightestScore : "+ highestScore , SwingConstants.CENTER);
        try {
            Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Pixelette.ttf")).deriveFont(Font.BOLD, 22);
            highestScoreLabel.setFont(pixelFont);
        } catch (Exception e) {
            e.printStackTrace();;
        }
        highestScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        highestScoreLabel.setForeground(Color.BLACK); // ตั้งค่าสีตัวอักษรเป็นสีขาว
        highestScoreLabel.setOpaque(false); // ทำให้ JLabel โปร่งใส

        this.add(Box.createVerticalStrut(50));
        this.add(scoreboardPanel);
        this.add(Box.createVerticalStrut(50));
        this.add(highestScoreLabel);
        this.add(Box.createVerticalStrut(30));
        this.add(gameOverLabel);
        this.add(Box.createVerticalStrut(30));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // ทำให้ JPanel โปร่งใส

        backToMenuButton = new JButton("giveUp?");
        backToMenuButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backToMenuButton.setPreferredSize(new Dimension(160,40));
        try {
            Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Pixelette.ttf")).deriveFont(Font.BOLD, 15);
            backToMenuButton.setFont(pixelFont);
        } catch (Exception e) {
            e.printStackTrace();;
        }
        backToMenuButton.setBackground(new Color(87 , 58 , 31));
        backToMenuButton.setForeground(Color.WHITE);

        restartButton = new JButton("Restart");
        restartButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        restartButton.setPreferredSize(new Dimension(160,40));
        try {
            Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Pixelette.ttf")).deriveFont(Font.BOLD, 15);
            restartButton.setFont(pixelFont);
        } catch (Exception e) {
            e.printStackTrace();;
        }
        restartButton.setBackground(new Color(87 , 58 , 31));
        restartButton.setForeground(Color.WHITE);

        buttonPanel.add(backToMenuButton);
        buttonPanel.add(restartButton);

        this.add(buttonPanel);
    }

    //  getters สำหรับให้ MainFrame เอาไปใส่ ActionListener
    public JButton getBackToMenuButton() {
        return backToMenuButton;
    }
    
    public void setCurrentScore(int currentScore){
        this.currentScore = currentScore;
        if (currentScore < highestScore) {
            gameOverLabel.setText("Really ? Score: "+ currentScore);
        }else{
            gameOverLabel.setText("You made NewHigh!");
        }
        
    }

    public void setHightestScore(int highestScore, int currentScore){
        this.highestScore = highestScore;
        this.currentScore = currentScore;
        if (currentScore > highestScore) {
            highestScoreLabel.setText("Your HightestScore : "+ currentScore);
        }else{
            highestScoreLabel.setText("Your HightestScore : "+ highestScore);
        }
    }

    public JButton getRestartButton() {
        return restartButton;
    }
    
    public void updateRank(List<User> newRank){
        this.currentRank = newRank;
        setRank(mode);
    }

    public void setRank(String mode){
        // ลบ JLabel เก่าทั้งหมด
        scoreboardPanel.removeAll();
        int top5 = 5;
        int rank = 1;

        // เพิ่ม JLabel ใหม่
        for(User u:currentRank){
            if (mode.toLowerCase().equals("easy")){
                tmp = new JLabel("[ Rank "+ rank + " ] " + u.getUsername() + " Score : " + u.getNormalScore());
            } else if (mode.toLowerCase().equals("hard")){
                tmp = new JLabel("[ Rank "+ rank + " ] " + u.getUsername() + " Score : " + u.getHardScore());                
            }
            try {
                Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Pixelette.ttf")).deriveFont(Font.BOLD, 15);
                tmp.setFont(pixelFont);
            } catch (Exception e) {
                e.printStackTrace();;
            }
            tmp.setHorizontalAlignment(SwingConstants.LEFT);
            tmp.setAlignmentX(Component.CENTER_ALIGNMENT);
            tmp.setForeground(Color.WHITE); // ตั้งค่าสีตัวอักษรเป็นสีขาว
            tmp.setOpaque(false); // ทำให้ JLabel โปร่งใส

            scoreboardPanel.add(tmp);
            rank++;
            top5--;
            if(top5==0){
                break;
            }
        }

        // อัปเดต UI
        scoreboardPanel.revalidate();
        scoreboardPanel.repaint();

        
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // วาดภาพพื้นหลังให้เต็มจอ
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

}
