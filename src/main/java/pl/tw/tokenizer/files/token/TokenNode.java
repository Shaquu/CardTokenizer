package pl.tw.tokenizer.files.token;

import java.util.ArrayList;
import java.util.List;

public class TokenNode {

    //First 6 digits
    private String bin;
    //Let's assume tokenNumber is bin+body+luhnControlSign, ex. 4772821231232 = bin is 477282 and body is 123123
    //So lets take all those bodies and put them into list :)
    private List<String> body;

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public List<String> getBody() {
        if(body == null){
            body = new ArrayList<>();
        }
        return body;
    }
}
