package pl.tw.tokenizer.validators;

import java.util.regex.Pattern;

public class CardValidator extends ValidatorInterface {

    //Min and max length of a card number
    private static final int minCardNumberLength = 15;
    private static final int maxCardNumberLength = 19;
    //Regex for digit only characters
    private static final String cardDigitsRegex = "^[0-9]*$";

    public CardValidator(String cardNumber) {
        super(cardNumber);
        check();
    }

    //If cardNumber equals characters other then digits then throw exception
    private void validateDigits(String cardNumber) {
        if (!Pattern.compile(cardDigitsRegex).matcher(cardNumber).matches()) {
            setValid(false);
            throw new RuntimeException("Incorrect characters in Card number '" + cardNumber + "' value");
        }
    }

    //If cardNumber length is incorrect then throw exception
    private void validateLength(String cardNumber) {
        if (cardNumber.length() < minCardNumberLength || cardNumber.length() > maxCardNumberLength) {
            setValid(false);
            throw new RuntimeException("Incorrect length for Card number '" + cardNumber + "'.Should be from " + minCardNumberLength + " to " + maxCardNumberLength);
        }
    }

    //If cardNumber luhn validation failed then throw exception
    private void validateLuhn(String cardNumber) {
        LuhnValidator luhnValidator = (LuhnValidator) new LuhnValidator(cardNumber).check();
        if (!luhnValidator.isValid()) {
            setValid(false);
            throw new RuntimeException("Card number '" + cardNumber + "' is not valid luhn number");
        }
    }

    @Override
    public ValidatorInterface check() {
        //if valid return false then skip the rest validation checks
        if (isValid()) {
            validateDigits(getValue());
        }
        if (isValid()) {
            validateLength(getValue());
        }
        if (isValid()) {
            validateLuhn(getValue());
        }
        return this;
    }
}
