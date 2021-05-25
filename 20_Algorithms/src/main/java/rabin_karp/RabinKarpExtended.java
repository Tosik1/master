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
        String str = text.replaceAll("(.)(?=.*\\1)", "");
        if (str.length() > 9) {
            Exception ex = new Exception("The number of characters is more than 9");
            ex.printStackTrace();
        }

    }
}