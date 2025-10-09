package lib.User_Section;

import java.util.List;

public class testUser {
    public static void main(String[] args) {
        // ทดสอบการ Sign Up
        System.out.println("=== Sign Up ===");
        UserManager.signUp("alice", "pass123");
        UserManager.signUp("bob", "pass456");
        UserManager.signUp("charlie", "pass789");
        
        // ทดสอบการ Login
        System.out.println("\n=== Login ===");
        User user1 = UserManager.login("alice", "pass123");
        System.out.println(user1.getUsername() + " - " + user1.getScore());
        
        // อัพเดท Score
        System.out.println("\n=== Update Score ===");
        UserManager um = new UserManager();
        um.updateScore("alice", 8500);
        um.updateScore("bob", 7200);
        um.updateScore("charlie", 50);
        
        // แสดงการจัด Ranking
        System.out.println("\n=== Ranking ===");
        // List<User> ranking = UserManager.getRanking();
        // for (int i = 0; i < ranking.size(); i++) {
        //     System.out.println((i+1) + ". " + ranking.get(i).getUsername() + " - " + ranking.get(i).getScore());
        // }
    }
}
