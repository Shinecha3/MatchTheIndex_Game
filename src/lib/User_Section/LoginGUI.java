import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
        loginTT.setFont(new Font("", Font.BOLD, 52));
        loginTT.setBounds(178, 22, 202, 70);
        cp.add(loginTT);

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

        // สร้างปุ่ม login
        JButton loginbutton = new JButton("Login");
        loginbutton.setFont(new Font("", Font.BOLD, 18));
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
                    JOptionPane.showMessageDialog(LoginGUI.this, 
                        "Login successful!\nWelcome " + user.getUsername() + 
                        "\nYour score: " + user.getScore(), 
                        "Success", 
                        JOptionPane.INFORMATION_MESSAGE);
                    
                    // ปิดหน้า Login และเปิดหน้าเกมหรือหน้าหลัก
                    LoginGUI.this.dispose();
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
        signuplabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signuplabel.setForeground(Color.BLUE);
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
                signuplabel.setForeground(Color.RED);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                signuplabel.setForeground(Color.BLUE);
            }
        });
        signuplabel.setBounds(170, 430, 200, 20);
        cp.add(signuplabel);
    }

    public void Finally() {
        setSize(500, 500);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}