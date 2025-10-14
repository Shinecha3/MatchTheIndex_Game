package lib.cardClass.Factory;

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
                    "setA_duo", "setA_hand", "setA_heart", "setA_lego",
                    "setA_princess", "setA_shine", "setA_smile", "setA_tail"
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
