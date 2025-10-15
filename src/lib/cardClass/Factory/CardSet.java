package lib.cardClass.Factory;

public class CardSet {

    public static String[] getSet(String setName) {
        switch (setName.toLowerCase()) {

            case "imgfruit":
                return new String[]{
                    "setA_fruit1", "setA_fruit2", "setA_fruit3", "setA_fruit4",
                    "setA_fruit5", "setA_fruit6", "setA_fruit7", "setA_fruit8"
                };

            case "imgfood":
                return new String[]{
                    "setA_food1", "setA_food2", "setA_food3", "setA_food4",
                    "setA_food5", "setA_food6", "setA_food7", "setA_food8"
                };
            
            case "imgdessert":
                return new String[]{
                    "setA_dessert1", "setA_dessert2", "setA_dessert3", "setA_dessert4",
                    "setA_dessert5", "setA_dessert6", "setA_dessert7", "setA_dessert8"
                };

            case "imgdrink":
                return new String[]{
                    "setA_drink1", "setA_drink2", "setA_drink3", "setA_drink4",
                    "setA_drink5", "setA_drink6", "setA_drink7", "setA_drink8"
                };

            default: // img
                return new String[]{
                    "setA_food1", "setA_food2", "setA_food3", "setA_food4",
                    "setA_food5", "setA_food6", "setA_food7", "setA_food8"
                };
        }
    }

    public static String getBackImage(String setName) {
        switch (setName.toLowerCase()) {
            case "imgfruit":
                return "set_back0.jpg";
            default:
                return "set_back0.jpg";
        }
    }

    public static String getRandomCardSet() {
        String[] allSets = {
                    "imgfood", "imgdessert", "imgfruit", "imgdrink"
                };
        int randIndex = (int) (Math.random() * allSets.length);
        System.err.println(new String(allSets[randIndex]));
        return new String(allSets[randIndex]);
    }
}
