package main;
import java.awt.*;
import javax.swing.*;

import lib.panel.*;
import lib.User_Section.*;;

public class MainFrame extends JFrame {
    CardLayout cardLayout;
    JPanel cards;
    String lastGame = "";
    

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
        MatchPicture gamePanel = new MatchPicture(currentUser, "easy");       // Kuromi set
        MatchPicture gamePanel2 = new MatchPicture(currentUser, "hard"); // Pony set

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
                currentUser.setHardScore(gamePanel.getCurrentScore());
            }
            
            gameOverPanel.updateRank(UserManager.getNormalRanking());
            gameOverPanel.setCurrentScore(gamePanel.getCurrentScore());
            cardLayout.show(cards, "GameOver");
            
        });

        

        // gamePanel2.setOnGameOver(() -> cardLayout.show(cards, "GameOver"));

        gamePanel2.setOnGameOver(() -> {
            System.out.println(gamePanel2.getCurrentScore());
            gameOverPanel2.setHightestScore(currentUser.getHardScore(),gamePanel2.getCurrentScore());
            System.out.println(currentUser.getHardScore());
            System.out.println(gamePanel2.getCurrentScore());
            if (currentUser.getHardScore() < gamePanel2.getCurrentScore()) {
                UserManager.updateHardScore(currentUser.getUsername(), gamePanel2.getCurrentScore());
                currentUser.setNormalScore(gamePanel2.getCurrentScore());
            }
            
            gameOverPanel2.updateRank(UserManager.getHardRanking());
            gameOverPanel2.setCurrentScore(gamePanel2.getCurrentScore());
            cardLayout.show(cards, "GameOver2");
            
        });



        //  set action ของปุ่ม Start ที่อยู่ใน panelStart
        menuPanel.getStartButton().addActionListener(e -> {
            lastGame = "Game";
            cardLayout.show(cards, "Game");
            gamePanel.startGame();
            
        });
        menuPanel.getStartHorseButton().addActionListener(e -> {
            lastGame = "Game2";
            cardLayout.show(cards, "Game2");
            gamePanel2.startGame();
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
            if (lastGame.equals("Game")) {
                cardLayout.show(cards, "Game");
                gamePanel.startGame();
            } else if (lastGame.equals("Game2")) {
                cardLayout.show(cards, "Game2");
                gamePanel2.startGame();
            }
            gamePanel.startGame(); // เริ่มเกมใหม่ทันที
        });

        gameOverPanel2.getRestartButton().addActionListener(e -> {
            if (lastGame.equals("Game")) {
                cardLayout.show(cards, "Game");
                gamePanel.startGame();
            } else if (lastGame.equals("Game2")) {
                cardLayout.show(cards, "Game2");
                gamePanel2.startGame();
            }
            gamePanel.startGame(); // เริ่มเกมใหม่ทันที
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


}
