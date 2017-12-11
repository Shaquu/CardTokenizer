package pl.tw.tokenizer.card;

import pl.tw.tokenizer.files.cache.CardDataCache;
import pl.tw.tokenizer.card.luhn.LuhnGenerator;

public class CardTokenizer {

    private static CardDataCache cardDataCache = new CardDataCache();

    //Return if card is already tokenized (record exists in cache file)
    private static boolean isTokenized(String cardNumber) {
        return cardDataCache.contains(cardNumber);
    }

    //Generate new token number and write it to cache file
    private static void tokenizeCard(CardInterface cardInterface) {
        String tokenNumber = LuhnGenerator.generate(cardInterface.getBin(), cardInterface.getCardNumber().length());
        cardInterface.setTokenNumber(tokenNumber);
        cardDataCache.append(cardInterface);
    }

    //If card is already tokenized then retrieve token number from cache, if not then generate new one
    public static void generateToken(CardInterface cardInterface) {
        if (isTokenized(cardInterface.getCardNumber())) {
            loadTokenFromCache(cardInterface);
        } else {
            tokenizeCard(cardInterface);
        }
    }

    //Load token number form cache file
    private static void loadTokenFromCache(CardInterface cardInterface) {
        String tokenNumber = cardDataCache.loadToken(cardInterface);
        cardInterface.setTokenNumber(tokenNumber);
    }
}
