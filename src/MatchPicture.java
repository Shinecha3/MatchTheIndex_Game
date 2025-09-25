import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.function.Consumer;
import javax.swing.*;

public class MatchPicture extends JPanel {

    int rows = 4;
    int columns = 4;
    int cardWidth = 90;
    int cardHeight = 90;

    int boardWidth = columns * cardWidth;
    int boardHeight = rows * cardHeight;

    private JLabel timeLabel;
    private JLabel scoreLabel;
    private Timer gameTimer;
    private int timeStart = 10;
    private int timeLeft;
    private int score = 0;

    private ArrayList<JButton> board = new ArrayList<>();
    private ArrayList<ImageIcon> cardSet = new ArrayList<>();

    private JButton card1Selected;
    private JButton card2Selected;
    private Timer hideCardTimer;
    private ImageIcon cardBackImage;
    private Consumer<Integer> onGameOver;

    public MatchPicture() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(boardWidth, boardHeight));

        // Stats Panel
        JPanel statsPanel = new JPanel(new GridLayout(1, 2));
        timeLabel = new JLabel("Time: " + timeLeft, SwingConstants.CENTER);
        scoreLabel = new JLabel("Score: " + score, SwingConstants.CENTER);
        statsPanel.add(timeLabel);
        statsPanel.add(scoreLabel);
        this.add(statsPanel, BorderLayout.NORTH);

        // Board Panel
        JPanel boardPanel = new JPanel(new GridLayout(rows, columns));
        this.add(boardPanel, BorderLayout.CENTER);

        setupCards();

        for (int i = 0; i < cardSet.size(); i++) {
            JButton tile = new JButton();
            tile.setPreferredSize(new Dimension(cardWidth, cardHeight));
            tile.setIcon(cardBackImage); // เริ่มต้นเป็นหลังการ์ด
            tile.setFocusable(false);

            int index = i;
            tile.addActionListener(e -> handleCardClick(tile, index));

            board.add(tile);
            boardPanel.add(tile);
        }

        // Timer จับเวลา
        gameTimer = new Timer(1000, e -> {
            timeLeft--;
            timeLabel.setText("Time: " + timeLeft);
            if (timeLeft <= 0) {
                gameTimer.stop();
                if (onGameOver != null) {
                    onGameOver.accept(score);
                }
            }
        });

        // Timer สำหรับคว่ำการ์ดหลังเปิดโชว์
        hideCardTimer = new Timer(1000, e -> {
            hideCards();   // คว่ำการ์ดทั้งหมด
            startGame();   // เริ่มนับเวลาหลังคว่ำเสร็จ
        });
        hideCardTimer.setRepeats(false);
    }

    private void setupCards() {
        cardSet.clear();

        String[] names = {"duo", "hand", "heart", "lego",
                          "princess", "shine", "smile", "tail"};

        for (String name : names) {
            java.net.URL url = getClass().getResource("/img/" + name + ".jpg");
            if (url == null) throw new RuntimeException("Image not found: " + name);

            Image cardImg = new ImageIcon(url).getImage();
            ImageIcon cardIcon = new ImageIcon(cardImg.getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH));
            cardSet.add(cardIcon);
            cardSet.add(cardIcon);
        }

        Image backImg = new ImageIcon(getClass().getResource("/img/back.jpg")).getImage();
        cardBackImage = new ImageIcon(backImg.getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH));

        // สุ่มการ์ด
        for (int i = 0; i < cardSet.size(); i++) {
            int j = (int)(Math.random() * cardSet.size());
            ImageIcon temp = cardSet.get(i);
            cardSet.set(i, cardSet.get(j));
            cardSet.set(j, temp);
        }
    }

    private void handleCardClick(JButton tile, int index) {
        if (tile.getIcon() != cardBackImage) return;

        tile.setIcon(cardSet.get(index));

        if (card1Selected == null) {
            card1Selected = tile;
        } else if (card2Selected == null) {
            card2Selected = tile;
            if (card1Selected.getIcon().equals(card2Selected.getIcon())) {
                score += 10;
                scoreLabel.setText("Score: " + score);
                card1Selected = null;
                card2Selected = null;
                checkWin();
            } else {
                hideCardTimer.start();
            }
        }
    }

    private void hideCards() {
        if (card1Selected != null && card2Selected != null) {
            // กรณีเลือกผิด → คว่ำกลับ
            card1Selected.setIcon(cardBackImage);
            card2Selected.setIcon(cardBackImage);
            card1Selected = null;
            card2Selected = null;
        } else {
            // กรณีเริ่มเกม → คว่ำทุกใบ
            for (JButton btn : board) {
                btn.setIcon(cardBackImage);
            }
        }
    }

    public void restartGame() {
        score = 0;
        scoreLabel.setText("Score: " + score);
        timeLeft = timeStart;
        timeLabel.setText("Time: " + timeLeft);

        setupCards();
        for (int i = 0; i < board.size(); i++) {
            board.get(i).setIcon(cardSet.get(i)); // เปิดหน้าแท้ก่อน
        }

        // เปิดโชว์ก่อนแล้วค่อยคว่ำ + เริ่มนับเวลา
        hideCardTimer.start();
    }

    private void startGame() {
        if (gameTimer.isRunning()) {
            gameTimer.stop();
        }
        timeLeft = timeStart;
        timeLabel.setText("Time: " + timeLeft);
        gameTimer.start();
    }

    private void checkWin() {
        boolean allOpen = board.stream().allMatch(btn -> btn.getIcon() != cardBackImage);
        if (allOpen) {
            gameTimer.stop();
            if (onGameOver != null) {
                onGameOver.accept(score);
            }
        }
    }

    public void setOnGameOver(Consumer<Integer> onGameOver) {
        this.onGameOver = onGameOver;
    }
}
