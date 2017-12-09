package pl.tw.tokenizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.apache.log4j.Logger;

@SpringBootApplication
@EnableCaching
public class CardTokenizerApplication {
    public static Logger log = Logger.getLogger(CardTokenizerApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(CardTokenizerApplication.class, args);
    }
}
