package lib.cardClass.Factory;

import javax.swing.ImageIcon;

public class NormalCard implements Card {
    private String name;
    private ImageIcon image;
    private int score = 10;

    public NormalCard(String name, ImageIcon image) {
        this.name = name;
        this.image = image;
    }

    @Override
    public String getName() { return name; }

    @Override
    public ImageIcon getImage() { return image; }

    @Override
    public int getScore() { return score; }

    @Override
    public String getDescription() { return name; }
}