package lib.cardClass.Factory;

public class CardSet {

    public static String[] getSet(String setName) {
        switch (setName.toLowerCase()) {

            case "imghorse":
                return new String[]{
                    "setC_applejack", "setC_fluttershy", "setC_glasses", "setC_perple",
                    "setC_pink", "setC_pinkiepie", "setC_rainbow",
                    "setC_rainbowdash"
                };

            case "imgslm":
                return new String[]{
                    "setB_angry", "setB_black", "setB_cople", "setB_eat",
                    "setB_ice", "setB_wow", "setB_lov", "setB_kids"
                };

            default: // img
                return new String[]{
                    "setA_duo", "setA_hand", "setA_heart", "setA_lego",
                    "setA_princess", "setA_shine", "setA_smile", "setA_tail2"
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
