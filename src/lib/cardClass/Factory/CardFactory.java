package lib.cardClass.Factory;

import javax.swing.ImageIcon;

import lib.cardClass.CardDecorator.StatusCard;

import java.util.Random;

public class CardFactory {
    private static Random rand = new Random();

    public static Card createCard(String cardName, ImageIcon icon) {
        // เริ่มด้วยการ์ดปกติ
        Card card = new NormalCard(cardName, icon);

        // โอกาสสุ่มเพิ่มสถานะ
        int roll = rand.nextInt(100);

        if (roll < 30) { // 30% ได้ Poison
            card = new StatusCard(card, "Poison", 10);
        }
        if (roll < 15) { // 15% ได้ Freeze (ห่อซ้อน)
            card = new StatusCard(card, "Freeze", 15);
        }
        if (roll < 5) { // 5% ได้ Buff (ห่อซ้อนอีก)
            card = new StatusCard(card, "Buff", 20);
        }

        return card;
    }
}