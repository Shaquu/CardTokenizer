package pl.tw.tokenizer.cache;

import pl.tw.tokenizer.card.CardInterface;

import java.io.*;

public class CardDataCache {

    //Delimeter used to split key and value in cache file
    private static final String cacheDataFileDelimter = "\t";

    //Cache file path
    private static final String cacheDataFilePath = "cache.txt";

    //Initialize cache file
    public CardDataCache() {
        try {
            //Create cache file if not exists
            //noinspection ResultOfMethodCallIgnored
            new File(cacheDataFilePath).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Check if file contains card number record
    public boolean contains(String cardNumber) {
        BufferedReader br;
        String line;
        try {
            br = new BufferedReader(new FileReader(cacheDataFilePath));
            while ((line = br.readLine()) != null) {
                if (line.startsWith(cardNumber)) {
                    return true;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    //Load token number for given card data
    public String loadToken(CardInterface cardInterface) throws RuntimeException {
        BufferedReader br;
        String line;
        String cardNumber = cardInterface.getCardNumber();
        String tokenNumber = null;
        try {
            br = new BufferedReader(new FileReader(cacheDataFilePath));
            while ((line = br.readLine()) != null) {
                if (line.startsWith(cardNumber)) {
                    tokenNumber = line.split(cacheDataFileDelimter)[1];
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
         * If token value is null then throw exception
         * In proper run loadToken() is called only when it was checked before that cardNumber exists in a cache file
         */
        if (tokenNumber == null) {
            throw new RuntimeException("TokenNumber value is null for " + cardInterface.toString());
        }

        return tokenNumber;
    }

    //Add new line with card data as cardNumber\ttokenNumber\n
    public void append(CardInterface cardInterface) {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(cacheDataFilePath, true));

            String data = cardInterface.getCardNumber() +
                    cacheDataFileDelimter +
                    cardInterface.getTokenNumber();

            bw.write(data + "\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
