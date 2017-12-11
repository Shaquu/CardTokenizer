package pl.tw.tokenizer.files.token;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TokenNodeLoader {

    //Tokens file path
    private static final String tokensFilePath = "tokens.txt";

    private static HashMap<String, TokenNode> nodes = new HashMap<>();

    public static void load(){

        try {
            //noinspection ResultOfMethodCallIgnored
            new File(tokensFilePath).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();

        try {
            List<TokenNode> nodesList = mapper.readValue(new File(tokensFilePath), new TypeReference<List<TokenNode>>(){});
            for(TokenNode tokenNode : nodesList){
                nodes.put(tokenNode.getBin(), tokenNode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<TokenNode> nodesList = new ArrayList<>(nodes.values());
            mapper.writeValue(new File(tokensFilePath), nodesList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addBodyNode(String bin, String bodyNode){
        if(nodes.containsKey(bin)){
            if(!nodes.get(bin).getBody().contains(bodyNode)){
                nodes.get(bin).getBody().add(bodyNode);
            }
        } else {
            TokenNode tokenNode = new TokenNode();
            tokenNode.setBin(bin);
            tokenNode.getBody().add(bodyNode);
            nodes.put(bin, tokenNode);
        }
    }

    public static boolean containsBodyNode(String bin, String bodyNode) {
        return nodes.containsKey(bin) && nodes.get(bin).getBody().contains(bodyNode);
    }
}
