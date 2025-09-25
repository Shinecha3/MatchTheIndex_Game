import java.io.*;
import java.util.*;

public class UserManager {
    private static final String FILE_NAME = "./lib/User_Section/File/user-information.csv";

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
        } catch (IOException e) {
            // ถ้าไฟล์ยังไม่มีให้ return list ว่างๆ
        }
        return users;
    }

    // บันทึก user ทั้งหมดกลับไปที่ไฟล์
    private static void saveUsers(List<User> users) {
        try (FileWriter fw = new FileWriter(FILE_NAME)) {
            for (User u : users) {
                fw.write(u.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // สมัคร user ใหม่
    public static boolean signUp(String username, String password) {
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
        List<User> users = loadUsers();
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u; // login สำเร็จ
            }
        }
        return null; // login fail
    }

    // update score (เช่นหลังจบเกม)
    public static boolean updateScore(String username, int newScore) {
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