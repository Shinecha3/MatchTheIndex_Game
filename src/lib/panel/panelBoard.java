package lib.panel;

import java.awt.*;
import javax.sound.sampled.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import lib.cardClass.Factory.*;

public class panelBoard extends JPanel {
    

    private String mode;
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
    int hideCardDely;    // 3วิ ก่อนเริ่ม ซ่อนการ์ด
    boolean gameReady = false;
    JButton card1Selected;
    JButton card2Selected;
    ImageIcon cardBackImageIcon;

    Runnable boardRun;

    public void boardRun(Runnable boardRun) {
        this.boardRun = boardRun;
    }

    public panelBoard(JButton restartButton, panelStats statsPanel,String mode) {
        this.setBackground(Color.GRAY);
        this.mode = mode;
        this.restartButton = restartButton;
        this.statsPanel = statsPanel;
        this.setName = CardSet.getRandomCardSet();  // สุ่มเช็ตการ์ด
        if (mode.toLowerCase().equals("easy")) {
            hideCardDely = (1000) * 2; // ดีเล ก่อนปิดไพ่
        }else if (mode.toLowerCase().equals("hard")){
            hideCardDely = 0;
        }
        //  ใช้ CardSet ดึงรายชื่อการ์ดตามเซต  
        this.cardList = CardSet.getSet(setName);
        setupCards();
        shuffleCards();
        this.setOpaque(false);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5); // ช่องว่างรอบๆ JButton
        gbc.fill = GridBagConstraints.BOTH; // ให้ JButton ขยายเต็มพื้นที่

        // ไว้สร้างการ์ด ปุ่มกด ระบบจับคู้
        for (int i = 0; i < cardSet.size(); i++) {
            JButton title = new JButton();
            title.setPreferredSize(new Dimension(cardwidth, cardHeight));
            title.setOpaque(true);
            title.setIcon(cardSet.get(i).getImage());
            title.setFocusable(false);

            System.out.println((i+1)+": " + title.getPreferredSize());

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
                                statsPanel.addTime(-2);
                                hideCardTimer.start();
                                playSound("false");

                            } else {
                                playSound("true");
                                scoreCount += cardSet.get(index).getScore();
                                statsPanel.updateScore(scoreCount);
                                card1Selected = null;
                                card2Selected = null;
                                statsPanel.addTime(3);
                                if (isBoardCleared()) {
                                    reCard(CardSet.getRandomCardSet());
                                    
                                }
                            }
                        }
                    }
                }
            });

           board.add(title);
            this.add(title, gbc);

            gbc.gridx++;
            if (gbc.gridx >= columns) {
                gbc.gridx = 0;
                gbc.gridy++;
            }
            
            if (mode.toLowerCase().equals("easy")) {
                    hideCardTimer = new Timer((750), new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        hideCards();
                    }
                });
            }else if (mode.toLowerCase().equals("hard")){
                    hideCardTimer = new Timer(500, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        hideCards();
                    }
                });
            }
            hideCardTimer.setRepeats(false);
            hideCardTimer.stop();
        }
    }

    public int getScoreCount() { return this.scoreCount; }

    public void setupCards() {
        cardSet = new ArrayList<>();        
        if (scoreCount > 0 ) {
            this.cardList = CardSet.getSet(setName);
        }
        

        // โหลดภาพหลังการ์ดครั้งเดียว
        java.net.URL backUrl = getClass().getResource("/img/" + CardSet.getBackImage(setName));
        if (backUrl == null) {
            throw new RuntimeException("Back image not found for set: " + setName);
        }
        Image cardBackImg = new ImageIcon(backUrl).getImage();
        cardBackImageIcon = new ImageIcon(cardBackImg.getScaledInstance(cardwidth, cardHeight, Image.SCALE_SMOOTH));

        // โหลดภาพการ์ดแต่ละใบ
        for (String cardName : cardList) {
            java.net.URL url = getClass().getResource("/img/" + cardName + ".jpg");
            if (url == null) {
                throw new RuntimeException("Image not found: " + cardName);
            }

            Image cardImg = new ImageIcon(url).getImage();
            ImageIcon cardImageIcon = new ImageIcon(
                cardImg.getScaledInstance(cardwidth, cardHeight, Image.SCALE_SMOOTH)
            );

            Card card = new NormalCard(cardName, cardImageIcon);
            cardSet.add(card);
        }

    // ทำให้เป็นคู่ (2 ใบต่อ 1 การ์ด)
    cardSet.addAll(new ArrayList<>(cardSet));
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

    public void reCard(String newSetName) {
        hideCards();
        card1Selected = null;
        card2Selected = null;

        this.setName = newSetName;  // เปลี่ยนชื่อเซต
        setupCards();               // โหลดการ์ดตามเซตใหม่
        shuffleCards();

        for (int i = 0; i < board.size(); i++) {
            board.get(i).setIcon(cardSet.get(i).getImage());
        }

        stopTimer();
        resetTimer();
    }   

    public void startHide(){
        
        if (mode.toLowerCase().equals("easy")) {
            Delay = new Timer(hideCardDely, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    hideCards();
                }
                
            });
            Delay.setRepeats(false);
            Delay.start();
        }else if (mode.toLowerCase().equals("hard")){
            hideCards();
        }


        
    }

    public void stopTimer() {
        hideCardTimer.restart();
        hideCardTimer.stop();
        if (mode.toLowerCase().equals("easy")) {
            Delay.restart();
            Delay.stop();
        }else if (mode.toLowerCase().equals("hard")){
            hideCards();
        }

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

    public void playSound(String sound){
        try {
            // โหลดไฟล์เสียง
            File file = new File("src\\sound\\"+ sound.toLowerCase() +".wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);

            // เปิด clip
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            // เล่นเสียง
            clip.start();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
