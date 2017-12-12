package pl.tw.tokenizer.card.luhn;

//Original source for luhn check digit generator algorithm on https://gist.github.com/josefeg/5781824

import pl.tw.tokenizer.CardTokenizerApplication;
import pl.tw.tokenizer.files.token.TokenNodeLoader;

import java.util.Random;

public class LuhnGenerator {

    private static Random random = new Random(System.currentTimeMillis());

    public static String generate(String bin, int cardLength) {

        int numbersToGenerate = cardLength - (bin.length() + 1);

        StringBuilder builderCard = new StringBuilder(bin);
        StringBuilder builderRandom;
        do {
            CardTokenizerApplication.log.debug("Generating randomBody for bin:" + bin);
            builderRandom = new StringBuilder();
            for (int i = 0; i < numbersToGenerate; i++) {
                int digit = random.nextInt(10);
                builderRandom.append(digit);
            }
        } while(TokenNodeLoader.containsBodyNode(bin, builderRandom.toString()));
        CardTokenizerApplication.log.debug("Generated randomBody:" + builderRandom.toString() + " for bin:" + bin);

        TokenNodeLoader.addBodyNode(bin, builderRandom.toString());

        builderCard.append(builderRandom);
        int checkDigit = getCheckDigit(builderCard.toString());
        CardTokenizerApplication.log.debug("Generated checkDigit:" + checkDigit + " for bin:" + bin);
        builderCard.append(checkDigit);

        return builderCard.toString();
    }

    private static int getCheckDigit(String number) {
        int sum = 0;
        for (int i = 0; i < number.length(); i++) {

            int digit = Integer.parseInt(number.substring(i, (i + 1)));

            if ((i % 2) == 0) {
                digit = digit * 2;
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10);
                }
            }
            sum += digit;
        }
        int mod = sum % 10;
        return ((mod == 0) ? 0 : 10 - mod);
    }
}
