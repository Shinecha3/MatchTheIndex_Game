package lib.cardClass.CardDecorator;

import lib.cardClass.Factory.Card;

public class StatusCard extends CardDecorator {
    private String status;
    private int bonusScore;

    public StatusCard(Card wrappedCard, String status, int bonusScore) {
        super(wrappedCard);
        this.status = status;
        this.bonusScore = bonusScore;
    }

    @Override
    public int getScore() {
        return super.getScore() + bonusScore;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " [" + status + "]";
    }
}