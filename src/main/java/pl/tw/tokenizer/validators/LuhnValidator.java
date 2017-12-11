package pl.tw.tokenizer.validators;

//Source on https://github.com/eix128/gnuc-credit-card-checker/blob/master/CCCheckerPro/src/com/gnuc/java/ccc/Luhn.java

public class LuhnValidator extends ValidatorInterface {

    LuhnValidator(String value) {
        super(value);
    }

    @Override
    public ValidatorInterface check() {
            int sum = 0;
            boolean alternate = false;
            for (int i = getValue().length() - 1; i >= 0; i--) {
                int n = Integer.parseInt(getValue().substring(i, i + 1));
                if (alternate) {
                    n *= 2;
                    if (n > 9) {
                        n = (n % 10) + 1;
                    }
                }
                sum += n;
                alternate = !alternate;
            }
            setValid(sum % 10 == 0);
            return this;
    }

}
