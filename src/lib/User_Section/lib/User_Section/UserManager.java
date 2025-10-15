package lib.User_Section;

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
                if (data.length == 4) {
                    String username = data[0];
                    String password = data[1];
                    int normalScore = Integer.parseInt(data[2]);
                    int hardScore = Integer.parseInt(data[3]);
                    users.add(new User(username, password, normalScore, hardScore));
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
        users.add(new User(username, password, 0, 0));
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

    // update normal score mode
    public static boolean updateNormalScore(String username, int newNormalScore) {
        List<User> users = loadUsers();
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                u.setNormalScore(newNormalScore);
                saveUsers(users);
                return true;
            }
        }
        return false;
    }

     // update hard score mode
    public static boolean updateHardScore(String username, int newHardScore) {
        List<User> users = loadUsers();
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                u.setHardScore(newHardScore);
                saveUsers(users);
                return true;
            }
        }
        return false;
    }

    // จัดอับดับ normal ranking score 
    public static List<User> getNormalRanking(){
        // โหลด user ทั้งหมดจากไฟล์ csv 
        List<User> users = loadUsers();
        // ทำการเรียงลำดับโดยเรียงจาก score
        users.sort((user1, user2) -> Integer.compare(user2.getNormalScore(), user1.getNormalScore()));
        // return เป็น list หลังจากเรียงเสร็จแล้ว
        return users;
    }

    // จัดอับดับ hard ranking score 
    public static List<User> getHardRanking(){
        // โหลด user ทั้งหมดจากไฟล์ csv 
        List<User> users = loadUsers();
        // ทำการเรียงลำดับโดยเรียงจาก score
        users.sort((user1, user2) -> Integer.compare(user2.getHardScore(), user1.getHardScore()));
        // return เป็น list หลังจากเรียงเสร็จแล้ว
        return users;
    }

}