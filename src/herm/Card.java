package herm;

public class Card {

    private final CardOptions title;
    private final CardType cardType;

    public Card(CardType ct, CardOptions n)
    {
        cardType = ct;
        title = n;
    }

    public CardOptions getTitle()
    {
        return title;
    }

    public CardType getCardType()
    {
        return cardType;
    }
}
