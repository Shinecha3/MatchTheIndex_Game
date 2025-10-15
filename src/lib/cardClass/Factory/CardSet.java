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
            
            case "imgfruit":
                return new String[]{
                    "setD_fruit1", "setD_fruit2", "setD_fruit3", "setD_fruit4",
                    "setD_fruit5", "setD_fruit6", "setD_fruit7", "setD_fruit8"
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
            case "imgfruit":
                return "setD_fruit0.jpg";
            default:
                return "back.jpg";
        }
    }

    public static String getRandomCardSet() {
        String[] allSets = {
                    "imgfruit", "imgfruit", "imgfruit"
                };
        int randIndex = (int) (Math.random() * allSets.length);
        System.err.println(new String(allSets[randIndex]));
        return new String(allSets[randIndex]);
    }
}
