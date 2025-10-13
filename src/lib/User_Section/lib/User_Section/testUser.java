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
        System.out.println(user1.getUsername() + " - " + "Normal = " + user1.getNormalScore() + ", Hard = " + user1.getHardScore());
        
        // อัพเดท Normal Score
        System.out.println("\n=== Update Normal Score ===");
        UserManager.updateNormalScore("alice", 100);
        UserManager.updateNormalScore("bob", 250);
        UserManager.updateNormalScore("charlie", 75);

        // อัพเดท Hard Score
        System.out.println("\n=== Update Normal Score ===");
        UserManager.updateHardScore("alice", 62);
        UserManager.updateHardScore("bob", 12);
        UserManager.updateHardScore("charlie", 95);

        // แสดงการจัด Ranking
        System.out.println("\n=== Ranking Normal mode ===");
        List<User> rankingNormal = UserManager.getNormalRanking();
        for (int i = 0; i < rankingNormal.size(); i++) {
            System.out.println((i+1) + ". " + rankingNormal.get(i).getUsername() + " - " + rankingNormal.get(i).getNormalScore());
        }
        System.out.println("\n=== Ranking Hard mode ===");
        List<User> rankingHard = UserManager.getHardRanking();
        for (int i = 0; i < rankingHard.size(); i++) {
            System.out.println((i+1) + ". " + rankingHard.get(i).getUsername() + " - " + rankingHard.get(i).getHardScore());
        }
    }
}
