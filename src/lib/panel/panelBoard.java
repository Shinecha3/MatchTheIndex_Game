package lib.panel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import lib.cardClass.Factory.*;
import lib.cardClass.Factory.NormalCard;

public class panelBoard extends JPanel {

    private String setName;
    String[] cardList;

    int rows = 4;
    int columns = 4;
    int cardwidth = 90;
    int cardHeight = 90;
    private JButton restartButton;
    private panelStats statsPanel;
    public ArrayList<Card> cardSet;
    Timer Delay;

    int scoreCount = 0;
    ArrayList<JButton> board = new ArrayList<>(); //  new ArrayList
    Timer hideCardTimer;
    int hideCardDely = (1000) * 2;    // 3วิ ก่อนเริ่ม ซ่อนการ์ด
    boolean gameReady = false;
    JButton card1Selected;
    JButton card2Selected;
    ImageIcon cardBackImageIcon;

    Runnable boardRun;

    public void boardRun(Runnable boardRun) {
        this.boardRun = boardRun;
    }

    public panelBoard(JButton restartButton, panelStats statsPanel, String setName) {
        this.restartButton = restartButton;
        this.statsPanel = statsPanel;
        this.setName = setName;

        // ✅ ใช้ CardSet ดึงรายชื่อการ์ดตามเซต
        this.cardList = CardSet.getSet(setName);
        setupCards();
        shuffleCards();

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
                    if (!gameReady) return;

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
                                if (isBoardCleared()) {
                                    reCard();
                                    statsPanel.addTime(15);
                                }
                            }
                        }
                    }
                }
            });

           board.add(title);
            this.add(title);

            hideCardTimer = new Timer((1000 / 2), new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    hideCards();
                }
            });
            hideCardTimer.setRepeats(false);
            hideCardTimer.stop();
        }
    }

    public int getScoreCount() { return this.scoreCount; }

    public void setupCards() {
        cardSet = new ArrayList<>();
        for (String cardName : cardList) {
            java.net.URL url = getClass().getResource("/img/" + cardName + ".jpg");
            if (url == null) {
                throw new RuntimeException("Image not found: " + cardName);
            }

            Image cardImg = new ImageIcon(url).getImage();
            ImageIcon cardImageIcon = new ImageIcon(
                cardImg.getScaledInstance(cardwidth, cardHeight, Image.SCALE_SMOOTH)
            );

            // ✅ ใช้ CardSet เลือกภาพหลังการ์ด
            Image cardBackImg = new ImageIcon(
                getClass().getResource("/img/" + CardSet.getBackImage(setName))
            ).getImage();
            cardBackImageIcon = new ImageIcon(cardBackImg.getScaledInstance(cardwidth, cardHeight, Image.SCALE_SMOOTH));

            Card card = new NormalCard(cardName, cardImageIcon);
            cardSet.add(card);
        }

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
            board.get(i).setIcon(cardSet.get(i).getImage());
        }
        
        statsPanel.startTimer(); //  เริ่มนับเวลาใหม่
        startHide();
    }

    public void reCard() {
        hideCards();
        card1Selected = null;
        card2Selected = null;

        shuffleCards();

        for (int i = 0; i < board.size(); i++) {
            board.get(i).setIcon(cardSet.get(i).getImage());
        }

        stopTimer();
        resetTimer();
    }    

    public void startHide(){
        Delay = new Timer(hideCardDely, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                hideCardTimer.start();
            }
            
        });
        Delay.setRepeats(false);
        Delay.start();
        
    }

    public void stopTimer() {
        hideCardTimer.restart();
        hideCardTimer.stop();
        Delay.restart();
        Delay.stop();
    }

    public void resetTimer() {
        stopTimer();
        startHide();
        
    }

    public boolean isBoardCleared() {
    for (JButton tile : board) {
        if (tile.getIcon() == cardBackImageIcon) {
            return false; // ยังมีการ์ดที่ยังไม่เปิด
        }
    }
    return true; // การ์ดเปิดหมดแล้ว
}
}
