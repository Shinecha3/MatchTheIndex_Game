package main;
import java.awt.*;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;

import lib.panel.*;
import lib.User_Section.*;;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cards;
    private Clip bg;
    

    public MainFrame(User inputUser) {
        this.setTitle("Match Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        User currentUser = inputUser;
        // หน้าเริ่ม
        panelStart menuPanel = new panelStart();

        // หน้าเกม
        MatchPicture gamePanel = new MatchPicture(currentUser, "easy");  
        MatchPicture gamePanel2 = new MatchPicture(currentUser, "hard"); 

        // หน้า GameOver
        panelOver gameOverPanel = new panelOver(UserManager.getNormalRanking(),"easy");
        panelOver gameOverPanel2 = new panelOver(UserManager.getHardRanking(),"hard");


        //  set callback จากหน้าเกม
        gamePanel.setOnGameOver(() -> {
            // to folk แก้พวกคะแนน
            System.out.println(gamePanel.getCurrentScore());
            gameOverPanel.setHightestScore(currentUser.getNormalScore(),gamePanel.getCurrentScore());
            System.out.println(currentUser.getNormalScore());
            System.out.println(gamePanel.getCurrentScore());
            
            if (currentUser.getNormalScore() < gamePanel.getCurrentScore()) {
                UserManager.updateNormalScore(currentUser.getUsername(), gamePanel.getCurrentScore());
                currentUser.setNormalScore(gamePanel.getCurrentScore());
            }
            
            gameOverPanel.updateRank(UserManager.getNormalRanking());
            gameOverPanel.setCurrentScore(gamePanel.getCurrentScore());
            cardLayout.show(cards, "GameOver");
            this.bg.stop();

            
        });

        



        gamePanel2.setOnGameOver(() -> {
            System.out.println(gamePanel2.getCurrentScore());
            gameOverPanel2.setHightestScore(currentUser.getHardScore(),gamePanel2.getCurrentScore());
            System.out.println(currentUser.getHardScore());
            System.out.println(gamePanel2.getCurrentScore());
            if (currentUser.getHardScore() < gamePanel2.getCurrentScore()) {
                UserManager.updateHardScore(currentUser.getUsername(), gamePanel2.getCurrentScore());
                currentUser.setHardScore(gamePanel2.getCurrentScore());
            }
            
            gameOverPanel2.updateRank(UserManager.getHardRanking());
            gameOverPanel2.setCurrentScore(gamePanel2.getCurrentScore());
            cardLayout.show(cards, "GameOver2");
            this.bg.stop();
            
        });



        //  set action ของปุ่ม Start ที่อยู่ใน panelStart
        menuPanel.getStartButton().addActionListener(e -> {
            cardLayout.show(cards, "Game");
            gamePanel.startGame();
            playBGSound("bg1", -17);
            
        });
        menuPanel.getStartHorseButton().addActionListener(e -> {
            cardLayout.show(cards, "Game2");
            gamePanel2.startGame();
            playBGSound("bg1", -17);
        });

        //  set action ของปุ่ม Back to Menu ที่อยู่ใน panelGameOver
        gameOverPanel.getBackToMenuButton().addActionListener(e -> {
            cardLayout.show(cards, "Menu");
        });

        gameOverPanel2.getBackToMenuButton().addActionListener(e -> {
            cardLayout.show(cards, "Menu");
        });

        //  set action ของปุ่ม Restart Game ที่อยู่ใน panelGameOver
        gameOverPanel.getRestartButton().addActionListener(e -> {
            cardLayout.show(cards, "Game");
            gamePanel.startGame();
            playBGSound("bg1", -17);

        
        });

        gameOverPanel2.getRestartButton().addActionListener(e -> {
            cardLayout.show(cards, "Game2");
            gamePanel2.startGame();
            playBGSound("bg2", -17);

        });

        // เพิ่มทุกหน้าเข้าไปใน card
        cards.add(menuPanel, "Menu");
        cards.add(gamePanel, "Game");
        cards.add(gamePanel2, "Game2");
        cards.add(gameOverPanel, "GameOver");
        cards.add(gameOverPanel2, "GameOver2");

        this.add(cards);
        this.setVisible(true);

    
    }

    public void playBGSound(String sound, float volume){
        try {
            // โหลดไฟล์เสียง
            File file = new File("src\\sound\\"+ sound.toLowerCase() +".wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);

            // เปิด bg
            bg = AudioSystem.getClip();
            bg.open(audioStream);

            // ปรับเสียง (dB)
            FloatControl gainControl = (FloatControl) bg.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);  

            bg.loop(Clip.LOOP_CONTINUOUSLY);
            bg.start();


            // เล่นเสียง
            bg.start();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
