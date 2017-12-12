package pl.tw.tokenizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import pl.tw.tokenizer.files.token.TokenNodeLoader;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@SpringBootApplication
@EnableCaching
public class CardTokenizerApplication {
    public static Logger log = Logger.getLogger(CardTokenizerApplication.class.getName());

    @Bean
    ApplicationShutdownListener appShutdownListener() {
        return new ApplicationShutdownListener();
    }

    public static void main(String[] args) {
        SpringApplication sa = new SpringApplication(CardTokenizerApplication.class);
        ConfigurableApplicationContext context = sa.run(args);
        context.getBean(ApplicationShutdownListener.class);
    }

    private static class ApplicationShutdownListener {

        @PostConstruct
        public void init() {
            CardTokenizerApplication.log.info("TokenNodeLoader started loading data");
            TokenNodeLoader.load();
        }

        @PreDestroy
        public void destroy() {
            CardTokenizerApplication.log.info("TokenNodeLoader started writing data");
            TokenNodeLoader.write();
        }
    }
}
