package lib.User_Section;

import main.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class LoginGUI extends JFrame {
    Container cp;
    private JTextField usernamefield;
    private JPasswordField passwordfield;
    

    public LoginGUI() {
        super("Login");
        Initial();
        setComponent();
        Finally();
    }

    public void Initial() {
        cp = getContentPane();
        cp.setLayout(null);

    }

    public void setComponent() {
        // สร้างคำ Login
        JLabel loginTT = new JLabel("Login");
        try {
            Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Pixelette.ttf")).deriveFont(Font.BOLD, 42);
            loginTT.setFont(pixelFont);
        } catch (Exception e) {
            e.printStackTrace();;
        }
        loginTT.setBounds(178, 50+10, 202, 70);
        loginTT.setForeground(Color.BLACK);
        cp.add(loginTT);

        // สร้าง Label ของ username
        JLabel usernamelabel = new JLabel("Username");
        usernamelabel.setBounds(100, 115+40, 400, 25);
        try {
            Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Pixelette.ttf")).deriveFont(Font.BOLD, 22);
            usernamelabel.setFont(pixelFont);
        } catch (Exception e) {
            e.printStackTrace();;
        }
        usernamelabel.setForeground(Color.BLACK);
        cp.add(usernamelabel);

        // สร้าง TextField ของ username
        usernamefield = new JTextField();
        usernamefield.setBounds(100, 150+40, 300, 35);
        usernamefield.setForeground(Color.DARK_GRAY);
        usernamefield.setFont(new Font("",Font.BOLD,17));
        cp.add(usernamefield);

        // สร้าง Label ของ password
        JLabel passwordlabel = new JLabel("Password");
        passwordlabel.setBounds(100, 210+40, 400, 25);
        passwordlabel.setForeground(Color.BLACK);
        try {
            Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Pixelette.ttf")).deriveFont(Font.BOLD, 22);
            passwordlabel.setFont(pixelFont);
        } catch (Exception e) {
            e.printStackTrace();;
        }
        cp.add(passwordlabel);

        // สร้าง PasswordField ของ password
        passwordfield = new JPasswordField();
        passwordfield.setBounds(100, 245+40, 300, 35);
        passwordfield.setForeground(Color.BLACK);
        passwordfield.setFont(new Font("", Font.PLAIN, 15));
        cp.add(passwordfield);

        // สร้างปุ่ม login
        JButton loginbutton = new JButton("Login");
        try {
            Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Pixelette.ttf")).deriveFont(Font.BOLD, 15);
            loginbutton.setFont(pixelFont);
        } catch (Exception e) {
            e.printStackTrace();;
        }
        loginbutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginbutton.setBackground(Color.BLACK);
        loginbutton.setForeground(Color.WHITE);
        // เพิ่ม ActionListener สำหรับปุ่ม Login
        loginbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernamefield.getText();
                String password = new String(passwordfield.getPassword());
                
                // ถ้าไม่ใส่อะไรเลยใน textfield มันจะขึ้นแจ้งเตือนให้
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginGUI.this, 
                        "Please enter both username and password!", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // ตรวจสอบ login ผ่าน UserManager
            User user = UserManager.login(username, password);
            if (user != null) {
                // JOptionPane.showMessageDialog(LoginGUI.this, 
                //     "Login successful!\nWelcome " + user.getUsername() + 
                //     "\nYour score: " + user.getScore(), 
                //     "Success", 
                //     JOptionPane.INFORMATION_MESSAGE);

                // ปิดหน้า Login
                LoginGUI.this.dispose();
                // เปิด MainFrame (หน้าเกมหลัก)
                new MainFrame(user).setVisible(true);

            } else {
                JOptionPane.showMessageDialog(LoginGUI.this, 
                    "Invalid username or password!", 
                    "Login Failed", 
                    JOptionPane.ERROR_MESSAGE);
            }
            }
        });
        loginbutton.setBounds(150, 340, 200, 40);
        cp.add(loginbutton);

        // สร้าง label signup
        JLabel signuplabel = new JLabel("You are new? Signup Here!");
        try {
            Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/Pixelette.ttf")).deriveFont(Font.BOLD, 12);
            signuplabel.setFont(pixelFont);
        } catch (Exception e) {
            e.printStackTrace();;
        }
        signuplabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signuplabel.setForeground(Color.YELLOW);
        signuplabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // ปิดหน้า login
                LoginGUI.this.dispose();
                // เปิดหน้า signup
                new SignUpGUI().setVisible(true);
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                signuplabel.setForeground(Color.GREEN);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                signuplabel.setForeground(Color.YELLOW);
            }
        });
        signuplabel.setBounds(145, 430, 222, 20);
        cp.add(signuplabel);

        ImageIcon bgIcon = new ImageIcon("src/img/setLogin.jpg");
        Image scaledImage = bgIcon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(scaledImage));
        background.setBounds(0, 0, 500, 500);
        cp.add(background);
        cp.setComponentZOrder(background, cp.getComponentCount() - 1); // ล่างสุดจริง
    }

    public void Finally() {
        setSize(500, 500);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}