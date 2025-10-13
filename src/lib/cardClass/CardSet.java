package lib.cardClass;

public class CardSet {

    public static String[] getSet(String setName) {
        switch (setName.toLowerCase()) {

            case "imghorse":
                return new String[]{
                    "applejack", "fluttershy", "glasses", "perple",
                    "pink", "pinkiepie", "rainbow",
                    "rainbowdash"
                };

            case "imgslm":
                return new String[]{
                    "angry", "black", "cople", "eat",
                    "ice", "wow", "lov", "kids"
                };

            default: // img
                return new String[]{
                    "duo", "hand", "heart", "lego",
                    "princess", "shine", "smile", "tail"
                };
        }
    }

    public static String getBackImage(String setName) {
        switch (setName.toLowerCase()) {
            case "imghorse":
                return "rain.jpg";
            case "imgslm":
                return "huh.jpg";
            default:
                return "back.jpg";
        }
    }
}
