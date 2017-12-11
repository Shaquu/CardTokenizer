package pl.tw.tokenizer.card;

import pl.tw.tokenizer.validators.CardValidator;

public class CardData extends CardValidator implements CardInterface {

    private String cardNumber = null;
    private String bin = null;

    private String tokenNumber = null;

    public CardData(String cardNumber) {
        super(cardNumber);
        if (isValid())
            setCardNumber(cardNumber);
    }

    @Override
    public String getCardNumber() {
        if (this.cardNumber == null) {
            throw new RuntimeException("Cannot return null value for getCardNumber");
        }
        return this.cardNumber;
    }

    @Override
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        setBin(cardNumber.substring(0, 6));
    }

    @Override
    public String getBin() {
        if (this.bin == null) {
            throw new RuntimeException("Cannot return null value for getBin");
        }
        return this.bin;
    }

    @Override
    public void setBin(String bin) {
        this.bin = bin;
    }

    @Override
    public String getTokenNumber() {
        if (this.tokenNumber == null) {
            CardTokenizer.generateToken(this);
        }
        return this.tokenNumber;
    }

    @Override
    public void setTokenNumber(String tokenNumber) {
        this.tokenNumber = tokenNumber;
    }

    public String toString() {
        return "CardData{cardNumber='" +
                this.cardNumber +
                "', bin='" +
                this.bin +
                "', tokenNumber='" +
                this.tokenNumber + "'}";
    }
}
