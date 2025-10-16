package lib.User_Section;

public class User {
    private String username;
    private String password;
    private int normalScore;
    private int hardScore;

    public User(String username, String password, int normalScore, int hardScore){
        this.username = username;
        this.password = password;
        this.normalScore = normalScore;
        this.hardScore = hardScore;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public int getNormalScore(){
        return normalScore;
    }

    public int getHardScore(){
        return hardScore;
    }

    public void setNormalScore(int normalScore){
        this.normalScore = normalScore;
    }
    
    public void setHardScore(int hardScore){
        this.hardScore = hardScore;
    }

    @Override
    public String toString(){
        return username + "," + password + "," + normalScore + "," + hardScore;
    }

}