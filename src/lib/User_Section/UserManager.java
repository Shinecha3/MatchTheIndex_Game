import java.io.*;
import java.util.*;

public class UserManager {
    private static final String FILE_NAME = "./src/lib/User_Section/File/user-information.csv";

    // อ่าน user ที่ login ไว้ทั้งหมดจากไฟล์ที่เก็บ 
    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>(); // โหลด user ทั้งหมดจากไฟล์ .csv มาเก็บใน List<User>
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    String username = data[0];
                    String password = data[1];
                    int score = Integer.parseInt(data[2]);
                    users.add(new User(username, password, score));
                }
            }
        } catch (FileNotFoundException e) {
            // ไฟล์ยังไม่มี - return list ว่าง
            System.out.println("User data file not found. Creating new file.");
        } catch (IOException e) {
            System.err.println("Error reading user data: " + e.getMessage());
        }
        return users;
    }
    
    // บันทึก user ทั้งหมดกลับไปที่ไฟล์
    private static void saveUsers(List<User> users) {
        try (FileWriter fw = new FileWriter(FILE_NAME)) {
            for (User u : users) {
                fw.write(u.toString() + "\n");
            }
            System.out.println("User data saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving user data: " + e.getMessage());
        }
    }

    // สมัคร user ใหม่
    public static boolean signUp(String username, String password) {
        // ตรวจสอบว่ามีเครื่องหมาย comma ไหม ถ้ามีมันจะทำให้ file csv เจ๋ง
        if (username.contains(",") || password.contains(",")) {
            return false;
        }
        
        List<User> users = loadUsers();
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return false; // มี user อยู่แล้ว
            }
        }
        users.add(new User(username, password, 0));
        saveUsers(users);
        return true;
    }

    // ตรวจสอบ login
    public static User login(String username, String password) {
        if (username == null || password == null) {
            return null;
        }

        List<User> users = loadUsers();
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                System.out.println("User logged in: " + username);
                return u; // login สำเร็จ
            }
        }
        return null; // login fail
    }

    // update score (เช่นหลังจบเกม)
    public  boolean updateScore(String username, int newScore) {
        List<User> users = loadUsers();
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                u.setScore(newScore);
                saveUsers(users);
                return true;
            }
        }
        return false;
    }
}