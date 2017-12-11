package pl.tw.tokenizer.validators;

public abstract class ValidatorInterface {

    private String value;
    private boolean valid = true;

    ValidatorInterface(String value){
        this.value = value;
    }

    public abstract ValidatorInterface check();

    public boolean isValid() {
        return valid;
    }

    void setValid(boolean valid) {
        this.valid = valid;
    }

    String getValue() {
        return value;
    }
}
