public class TestUser {
    public static void main(String[] args) {
        // สมัครสมาชิก
        if (UserManager.signUp("bogie", "112233")) {
            System.out.println("Sign up success!");
        } else {
            System.out.println("Username already exists!");
        }

        // ลอง login
        User u = UserManager.login("bogie", "112233");
        if (u != null) {
            System.out.println("Welcome " + u.getUsername() + ", score = " + u.getScore());
            
            // update score
            UserManager.updateScore("bogie", 100);
            System.out.println("Score updated!");
            System.out.print(u.getScore());
        } else {
            System.out.println("Login failed!");
        }
    }
}