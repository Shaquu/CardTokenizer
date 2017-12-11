package pl.tw.tokenizer.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.tw.tokenizer.CardTokenizerApplication;
import pl.tw.tokenizer.card.CardData;

import javax.annotation.PreDestroy;

@RestController
public class TokenizerController {

    @RequestMapping("/tokenizeCard")
    public CardData tokenizeCard(@RequestParam(value = "cardNumber", defaultValue = "") String cardNumber) {
        //System.out.println("Trying to tokenize card " + cardNumber);
        CardData cardData = new CardData(cardNumber);
        if (cardData.isValid()) {
            cardData.getTokenNumber();
            CardTokenizerApplication.log.debug("Tokenization succesfull for " + cardData.toString());
            return cardData;
        } else {
            CardTokenizerApplication.log.error("Tokenization failed for " + cardData.toString());
            return cardData;
        }
    }
}
