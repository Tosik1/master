package rabin_karp;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class RabinKarpExtended
{
    private String text;
    private TreeMap<Integer, Integer> number2position;

    public RabinKarpExtended(String text)
    {
        this.text = text;
        createIndex();
    }

    public List<Integer> search(String query)
    {
            int position = 1;
            char[] characters = query.toCharArray();
            for (int i = 1; i < query.length(); i++) {
                int j;
                for (j = 0; j < position; ++j) {
                    if (characters[i] == characters[j]) {
                        break;
                    }
                }
                if (j == position) {
                    characters[position] = characters[i];
                    ++position;
                } else {
                    characters[position] = 0;
                    ++position;
                }
            }
            if (characters.length > 10) {
                Exception ex = new Exception("The number of characters is more than 9");
                ex.printStackTrace();
            }


        ArrayList<Integer> indices = new ArrayList<>();
        int queryLength = query.length();
        int firstSymbol = 0;
        int hashQuery = query.hashCode();
        for (int i = 0; i < text.length() - queryLength; i++){
            String partText = text.substring(firstSymbol, firstSymbol + queryLength);
            int hashText = partText.hashCode();
            if (hashText == hashQuery){
                for (int a = 0; a < queryLength; a++){
                    indices.add(firstSymbol + a);
                }
            }
            firstSymbol++;
        }

        return indices;
    }

    private void createIndex()
    {

    }
}