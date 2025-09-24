package lib.panel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import lib.cardClass.Factory.*;

public class panelBoard extends JPanel {

    String[] cardList = {
        "duo", "hand", "heart", "lego",
        "princess", "shine", "smile", "tail"
    };

    int rows = 4;
    int columns = 4;
    int cardwidth = 90;
    int cardHeight = 90;
    private JButton restartButton;
    private panelStats statsPanel;
    public ArrayList<Card> cardSet;

    int scoreCount = 0;
    ArrayList<JButton> board = new ArrayList<>(); // ✅ new ArrayList
    Timer hideCardTimer;
    boolean gameReady = false;
    JButton card1Selected;
    JButton card2Selected;
    ImageIcon cardBackImageIcon;

    public panelBoard(JButton restartButton,panelStats statsPanel) {
        this.restartButton = restartButton;
        this.statsPanel = statsPanel;
        setupCards();
        shuffleCards();
        statsPanel.startTimer();

        this.setLayout(new GridLayout(rows, columns));
        for (int i = 0; i < cardSet.size(); i++) {
            JButton title = new JButton();
            title.setPreferredSize(new Dimension(cardwidth, cardHeight));
            title.setOpaque(true);
            title.setIcon(cardSet.get(i).getImage());
            title.setFocusable(false);

            title.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!gameReady) {
                        return;
                    }
                    JButton tile = (JButton) e.getSource();
                    if (tile.getIcon() == cardBackImageIcon) {
                        if (card1Selected == null) {
                            card1Selected = tile;
                            int index = board.indexOf(card1Selected);
                            card1Selected.setIcon(cardSet.get(index).getImage());
                        } else if (card2Selected == null) {
                            card2Selected = tile;
                            int index = board.indexOf(card2Selected);
                            card2Selected.setIcon(cardSet.get(index).getImage());

                            if (card1Selected.getIcon() != card2Selected.getIcon()) {
                                hideCardTimer.start();
                            } else {
                                scoreCount += cardSet.get(index).getScore();
                                statsPanel.updateScore(scoreCount); 
                                card1Selected = null;
                                card2Selected = null;
                            }
                        }
                    }
                }
            });

            board.add(title);
            this.add(title);
            
            hideCardTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideCards();
            }
            } );
            hideCardTimer.setRepeats(false);
            hideCardTimer.start();
        }
    }

    public int getScoreCount() {
        return this.scoreCount;
    }

    public void setupCards() {
        cardSet = new ArrayList<>();
        for (String cardName : cardList) {
            java.net.URL url = getClass().getResource("/img/" + cardName + ".jpg");
            if (url == null) {
                throw new RuntimeException("Image not found: " + cardName);
            }

            Image cardImg = new ImageIcon(url).getImage();
            ImageIcon cardImageIcon = new ImageIcon(
                cardImg.getScaledInstance(cardwidth, cardHeight, java.awt.Image.SCALE_SMOOTH)
            );

            // ✅ ใช้ Factory ในการสร้างการ์ด (สุ่มสถานะ)
            Card card = new NormalCard(cardName, cardImageIcon);
            cardSet.add(card);
 

        }

        // cardSet.addAll(new ArrayList<>(cardSet));
        cardSet.addAll(cardSet);
    }

    public void shuffleCards() {
        // System.out.println(cardSet);

        for (int i = 0; i < cardSet.size(); i++) {
            int j = (int) (Math.random() * cardSet.size());
            Card temp = cardSet.get(i);
            cardSet.set(i, cardSet.get(j));
            cardSet.set(j, temp);
        }
        // System.out.println(cardSet);
    }

    public void hideCards() {
        if (gameReady && card1Selected != null && card2Selected != null) {
            card1Selected.setIcon(cardBackImageIcon);
            card1Selected = null;
            card2Selected.setIcon(cardBackImageIcon);
            card2Selected = null;
        } else {
            for (int i = 0; i < board.size(); i++) {
                board.get(i).setIcon(cardBackImageIcon);
            }
            gameReady = true;
            restartButton.setEnabled(true);
        }
    }
    
    public void restartGame() {
        gameReady = false;
        card1Selected = null;
        card2Selected = null;
        scoreCount = 0;

        shuffleCards();

        // รีเซ็ตไอคอนทุกใบกลับไปเป็น cardBackImageIcon
        for (int i = 0; i < board.size(); i++) {
            board.get(i).setIcon(cardBackImageIcon);
        }
        
        statsPanel.startTimer(); // ✅ เริ่มนับเวลาใหม่
        hideCardTimer.start();
    }
}
