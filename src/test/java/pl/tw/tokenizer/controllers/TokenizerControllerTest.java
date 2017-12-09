package pl.tw.tokenizer.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.util.NestedServletException;
import pl.tw.tokenizer.CardTokenizerApplication;

import java.nio.charset.Charset;

import static junit.framework.TestCase.fail;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CardTokenizerApplication.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class TokenizerControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    private final String correctVisCardNum = "4772820100874964";
    private final String correctMciCardNum = "5556043502674077";
    private final String incorrectLuhnCardNum = "5556043502674071";
    private final String incorrectLengCardNum = "5556043502674";
    private final String incorrectCharCardNum = "555604 502674";

    @Test
    public void tokenize_correctVisCardNum() throws Exception {
        tokenizeCardTest(correctVisCardNum)
                .andExpect(jsonPath("$.valid", is(true)))
                .andExpect(jsonPath("$.cardNumber", is(correctVisCardNum)))
                .andExpect(jsonPath("$.bin", is(correctVisCardNum.substring(0, 6))));
    }

    @Test
    public void tokenize_correctMciCardNum() throws Exception {
        tokenizeCardTest(correctMciCardNum)
                .andExpect(jsonPath("$.valid", is(true)))
                .andExpect(jsonPath("$.cardNumber", is(correctMciCardNum)))
                .andExpect(jsonPath("$.bin", is(correctMciCardNum.substring(0, 6))));
    }

    @Test
    public void tokenize_incorrectLuhnCardNum() throws Exception {
        try{
            tokenizeCardTest(incorrectLuhnCardNum);
            fail();
        } catch (NestedServletException e){
            assertThat(e.getLocalizedMessage(),
                    containsString("Card number '" + incorrectLuhnCardNum + "' is not valid luhn number"));
        }
    }

    @Test
    public void tokenize_incorrectLengCardNum() throws Exception {
        try{
            tokenizeCardTest(incorrectLengCardNum);
            fail();
        } catch (NestedServletException e){
            assertThat(e.getLocalizedMessage(),
                    containsString("Incorrect length for Card number '" + incorrectLengCardNum + "'.Should be from 15 to 19"));
        }
    }

    @Test
    public void tokenize_incorrectCharCardNum() throws Exception {
        try{
            tokenizeCardTest(incorrectCharCardNum);
            fail();
        } catch (NestedServletException e){
            assertThat(e.getLocalizedMessage(),
                    containsString("Incorrect characters in Card number '" + incorrectCharCardNum + "' value"));
        }
    }

    private ResultActions tokenizeCardTest(String cardNum) throws Exception {
        return mockMvc.perform(get("http://localhost:8080/tokenizeCard?cardNumber=" + cardNum))
                .andExpect(content().contentType(contentType));
    }
}
