package nl.rabobank.powerofattorney.stub.model.data;

public enum Authorization {
    DEBIT_CARD, VIEW, PAYMENT;

    public static Authorization find(CardType cardType){
        switch (cardType){
            case DEBIT_CARD:
                return DEBIT_CARD;
            case CREDIT_CARD:
                return PAYMENT;
            default:
                throw new IllegalArgumentException("Card type not existent");
        }
    }
}
