package pl.tw.tokenizer.card;

import pl.tw.tokenizer.card.luhn.LuhnValidator;

import java.util.regex.Pattern;

public class CardValidator {

    //Min and max length of a card number
    private static final int minCardNumberLength = 15;
    private static final int maxCardNumberLength = 19;
    //Regex for digit only characters
    private static final String cardDigitsRegex = "^[0-9]*$";
    //Is card number valid
    private boolean valid = true;

    CardValidator(String cardNumber) {
        //if valid return false then skip the rest validation checks
        if (valid) {
            validateDigits(cardNumber);
        }
        if (valid) {
            validateLength(cardNumber);
        }
        if (valid) {
            validateLuhn(cardNumber);
        }
    }

    //If cardNumber equals characters other then digits then throw exception
    private void validateDigits(String cardNumber) {
        if (!Pattern.compile(cardDigitsRegex).matcher(cardNumber).matches()) {
            valid = false;
            throw new RuntimeException("Incorrect characters in Card number '" + cardNumber + "' value");
        }
    }

    //If cardNumber length is incorrect then throw exception
    private void validateLength(String cardNumber) {
        if (cardNumber.length() < minCardNumberLength || cardNumber.length() > maxCardNumberLength) {
            valid = false;
            throw new RuntimeException("Incorrect length for Card number '" + cardNumber + "'.Should be from " + minCardNumberLength + " to " + maxCardNumberLength);
        }
    }

    //If cardNumber luhn validation failed then throw exception
    private void validateLuhn(String cardNumber) {
        if (!LuhnValidator.check(cardNumber)) {
            valid = false;
            throw new RuntimeException("Card number '" + cardNumber + "' is not valid luhn number");
        }
        valid = true;
    }

    public boolean isValid() {
        return valid;
    }
}
