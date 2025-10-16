package lib.User_Section;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignUpGUI extends JFrame {
    Container cp;
    private JTextField usernamefield;
    private JPasswordField passwordfield;

    public SignUpGUI() {
        super("Signup");
        Initial();
        setComponent();
        Finally();
    }

    public void Initial() {
        cp = getContentPane();
        cp.setLayout(null);
    }

    public void setComponent() {
        // สร้างคำ Signup
        JLabel signupTT = new JLabel("Signup");
        signupTT.setFont(new Font("", Font.BOLD, 52));
        signupTT.setBounds(163, 22, 202, 70);
        cp.add(signupTT);

        // สร้าง Label ของ username
        JLabel usernamelabel = new JLabel("Username");
        usernamelabel.setBounds(100, 115, 400, 25);
        usernamelabel.setFont(new Font("", Font.PLAIN, 20));
        cp.add(usernamelabel);

        // สร้าง TextField ของ username
        usernamefield = new JTextField();
        usernamefield.setBounds(100, 150, 300, 35);
        usernamefield.setForeground(Color.BLACK);
        usernamefield.setFont(new Font("", Font.PLAIN, 15));
        cp.add(usernamefield);

        // สร้าง Label ของ password
        JLabel passwordlabel = new JLabel("Password");
        passwordlabel.setBounds(100, 210, 400, 25);
        passwordlabel.setFont(new Font("", Font.PLAIN, 20));
        cp.add(passwordlabel);

        // สร้าง PasswordField ของ password
        passwordfield = new JPasswordField();
        passwordfield.setBounds(100, 245, 300, 35);
        passwordfield.setForeground(Color.BLACK);
        passwordfield.setFont(new Font("", Font.PLAIN, 15));
        cp.add(passwordfield);

        // สร้างปุ่ม Signup
        JButton signupbutton = new JButton("Signup");
        signupbutton.setFont(new Font("", Font.BOLD, 18));
        signupbutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signupbutton.setBackground(Color.BLACK);
        signupbutton.setForeground(Color.WHITE);
        signupbutton.setBounds(150, 340, 200, 40);
        
        // เพิ่ม ActionListener สำหรับปุ่ม Signup
        signupbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernamefield.getText();
                String password = new String(passwordfield.getPassword());
                
                // ตรวจสอบว่ากรอกข้อมูลครบหรือไม่
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(SignUpGUI.this, 
                        "Please fill in all fields!", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // ตรวจสอบความยาวของ username และ password
                if (username.length() < 4) {
                    JOptionPane.showMessageDialog(SignUpGUI.this, 
                        "Username must be at least 4 characters long!", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (password.length() < 6) {
                    JOptionPane.showMessageDialog(SignUpGUI.this, 
                        "Password must be at least 6 characters long!", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // สมัครสมาชิกผ่าน UserManager
                boolean success = UserManager.signUp(username, password);
                if (success) {
                    JOptionPane.showMessageDialog(SignUpGUI.this, 
                        "Signup successful! You can now login.", 
                        "Success", 
                        JOptionPane.INFORMATION_MESSAGE);
                    
                    // ปิดหน้า Signup และเปิดหน้า Login
                    SignUpGUI.this.dispose();
                    new LoginGUI().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(SignUpGUI.this, 
                        "Username already exists! Please choose another username.", 
                        "Signup Failed", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        cp.add(signupbutton);

        // สร้าง label login
        JLabel loginlabel = new JLabel("Have an account? Login Here!");
        loginlabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginlabel.setForeground(Color.BLUE);
        loginlabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // ปิดหน้า signup
                SignUpGUI.this.dispose();
                // เปิดหน้า login
                new LoginGUI().setVisible(true);
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                loginlabel.setForeground(Color.RED);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                loginlabel.setForeground(Color.BLUE);
            }
        });
        loginlabel.setBounds(163, 430, 200, 20);
        cp.add(loginlabel);

        
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