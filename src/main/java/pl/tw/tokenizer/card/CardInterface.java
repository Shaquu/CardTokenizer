package pl.tw.tokenizer.card;

public interface CardInterface {

    String getCardNumber();

    void setCardNumber(String cardNumber);

    //First 6 digits
    String getBin();

    void setBin(String bin);

    String getTokenNumber();

    void setTokenNumber(String tokenNumber);
}
