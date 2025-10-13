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
        UserManager userManager = new UserManager();
    
        // หน้าเริ่ม
        panelStart menuPanel = new panelStart();

        // หน้าเกม
        MatchPicture gamePanel = new MatchPicture("img", currentUser);       // Kuromi set
        MatchPicture gamePanel2 = new MatchPicture("imghorse", currentUser); // Pony set
        MatchPicture gamePanel3 = new MatchPicture("imgslm", currentUser); // slm set
        // หน้า GameOver
        panelOver gameOverPanel = new panelOver(userManager.getRanking());


        //  set callback จากหน้าเกม
        gamePanel.setOnGameOver(() -> {
            
            System.out.println(gamePanel.getCurrentScore());
            gameOverPanel.setHightestScore(currentUser.getScore(),gamePanel.getCurrentScore());
            System.out.println(currentUser.getScore());
            System.out.println(gamePanel.getCurrentScore());
            if (currentUser.getScore() < gamePanel.getCurrentScore()) {
                userManager.updateScore(currentUser.getUsername(), gamePanel.getCurrentScore());
                currentUser.setScore(gamePanel.getCurrentScore());
            }
            
            gameOverPanel.updateRank(userManager.getRanking());
            gameOverPanel.setCurrentScore(gamePanel.getCurrentScore());
            cardLayout.show(cards, "GameOver");
            
        });

        

        // gamePanel2.setOnGameOver(() -> cardLayout.show(cards, "GameOver"));

        gamePanel2.setOnGameOver(() -> {
            
            System.out.println(gamePanel.getCurrentScore());
            gameOverPanel.setHightestScore(currentUser.getScore(),gamePanel.getCurrentScore());
            System.out.println(currentUser.getScore());
            System.out.println(gamePanel.getCurrentScore());
            if (currentUser.getScore() < gamePanel.getCurrentScore()) {
                userManager.updateScore(currentUser.getUsername(), gamePanel.getCurrentScore());
                currentUser.setScore(gamePanel.getCurrentScore());
            }
            
            gameOverPanel.updateRank(userManager.getRanking());
            gameOverPanel.setCurrentScore(gamePanel.getCurrentScore());
            cardLayout.show(cards, "GameOver");
            
        });

        gamePanel3.setOnGameOver(() -> {
            
            System.out.println(gamePanel.getCurrentScore());
            gameOverPanel.setHightestScore(currentUser.getScore(),gamePanel.getCurrentScore());
            System.out.println(currentUser.getScore());
            System.out.println(gamePanel.getCurrentScore());
            if (currentUser.getScore() < gamePanel.getCurrentScore()) {
                userManager.updateScore(currentUser.getUsername(), gamePanel.getCurrentScore());
                currentUser.setScore(gamePanel.getCurrentScore());
            }
            
            gameOverPanel.updateRank(userManager.getRanking());
            gameOverPanel.setCurrentScore(gamePanel.getCurrentScore());
            cardLayout.show(cards, "GameOver");
            
        });


        //  set action ของปุ่ม Start ที่อยู่ใน panelStart
        menuPanel.getStartButton().addActionListener(e -> {
            // userManager.();
            lastGame = "Game";
            cardLayout.show(cards, "Game");
            gamePanel.startGame();
            
        });
        menuPanel.getStartHorseButton().addActionListener(e -> {
            lastGame = "Game2";
            cardLayout.show(cards, "Game2");
            gamePanel2.startGame();
        });
        menuPanel.getStartSlmButton().addActionListener(e -> {
            lastGame = "Game3";
            cardLayout.show(cards, "Game3");
            gamePanel3.startGame();
        });

        //  set action ของปุ่ม Back to Menu ที่อยู่ใน panelGameOver
        gameOverPanel.getBackToMenuButton().addActionListener(e -> {
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
            } else if (lastGame.equals("Game3")) {
                cardLayout.show(cards, "Game3");
                gamePanel3.startGame();
            }
            gamePanel.startGame(); // เริ่มเกมใหม่ทันที
        });

        // เพิ่มทุกหน้าเข้าไปใน card
        cards.add(menuPanel, "Menu");
        cards.add(gamePanel, "Game");
        cards.add(gamePanel2, "Game2");
        cards.add(gamePanel3, "Game3");
        cards.add(gameOverPanel, "GameOver");

        this.add(cards);
        this.setVisible(true);
    }


}
