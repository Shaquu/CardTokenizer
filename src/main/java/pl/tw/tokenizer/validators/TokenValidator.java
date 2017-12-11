package pl.tw.tokenizer.validators;

public class TokenValidator extends ValidatorInterface {

    TokenValidator(String value) {
        super(value);
    }

    @Override
    public ValidatorInterface check() {
        return this;
    }
}
