import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import lib.panel.*;


public class MatchPicture extends JFrame{
    
    

    int rows = 4;
    int columns = 4;
    int cardwidth = 90;
    int cardHeight = 90;

    int boardWidth = columns * cardwidth;
    int boardHeight = rows * cardHeight;
  
    // JFrame frame = new JFrame("Kuromi match");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel restartGamePanel = new JPanel();
    JButton restartButton = new JButton();


    int errorCount = 0;
    ArrayList<JButton> board;
    Timer hideCardTimer;
    boolean gameReady = false;
    JButton card1Selected;
    JButton card2Selected;

    public void Initial(){
        // frame.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setSize(boardWidth, boardHeight);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // public void SetComponent(){
    //     JPanel table = CreateTable();
    //     JPanel gamepanel = CreateGamePanel();
    //     cp.add(table,BorderLayout.CENTER);
    //     cp.add(gamepanel,BorderLayout.EAST);
    // }
    // public  void Finally(){
    //     ImageIcon icon = new ImageIcon("./Image/pawn.jpg");
    //     this.setIconImage(icon.getImage());
    //     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     this.setLocationRelativeTo(null);
    //     this.pack();  
    //     this.setVisible(true);
    // }

    MatchPicture() {
        Initial();
        
        panelStats statsPanel = new panelStats(boardWidth);
        this.add(statsPanel, BorderLayout.NORTH);

        panelBoard boardPanel = new panelBoard(restartButton, statsPanel);
        this.add(boardPanel);

        statsPanel.setOnTimeUp(() -> {
            JOptionPane.showMessageDialog(this, "Time's up! Restarting...");
            boardPanel.restartGame();
        });

        restartButton.setFont(new Font("amela", Font.PLAIN, 16));
        restartButton.setText("Restart Game");
        restartButton.setPreferredSize(new Dimension(boardWidth, 30));
        restartButton.setFocusable(false);
        restartButton.setEnabled(false); // ✅ ให้กดได้เลย
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardPanel.restartGame();  // ✅ ใช้ method ของ panelBoard
                statsPanel.updateScore(0); // สมมติว่ามี method อัปเดต error ใน panelStats
            }
        });

        restartGamePanel.add(restartButton);
        this.add(restartGamePanel, BorderLayout.SOUTH);
        
        this.pack();
        this.setVisible(true);
    }


    
}
