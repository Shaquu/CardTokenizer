package pl.tw.tokenizer.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.tw.tokenizer.CardTokenizerApplication;
import pl.tw.tokenizer.card.CardData;

@RestController
public class TokenizerController {

    @RequestMapping("/tokenizeCard")
    public CardData tokenizeCard(@RequestParam(value = "cardNumber", defaultValue = "") String cardNumber) {
        //System.out.println("Trying to tokenize card " + cardNumber);
        CardData cardData = new CardData(cardNumber);
        if (cardData.isValid()) {
            cardData.getTokenNumber();
            //System.out.println("Tokenization succesfull for " + cardData.toString());
            CardTokenizerApplication.log.info("Tokenization succesfull for " + cardData.toString());
            return cardData;
        } else {
            //System.out.println("Tokenization failed for " + cardData.toString());
            CardTokenizerApplication.log.error("Tokenization succesfull for " + cardData.toString());
            return cardData;
        }
    }
}
