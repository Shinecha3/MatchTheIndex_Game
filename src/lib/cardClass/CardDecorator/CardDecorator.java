package lib.cardClass.CardDecorator;

import javax.swing.ImageIcon;

import lib.cardClass.Factory.Card;

public abstract class CardDecorator implements Card {
    protected Card wrappedCard;

    public CardDecorator(Card wrappedCard) {
        this.wrappedCard = wrappedCard;
    }

    @Override
    public String getName() { return wrappedCard.getName(); }

    @Override
    public ImageIcon getImage() { return wrappedCard.getImage(); }

    @Override
    public int getScore() { return wrappedCard.getScore(); }

    @Override
    public String getDescription() { return wrappedCard.getDescription(); }
}