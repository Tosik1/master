import mscMetro.Line;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.*;
import org.jsoup.nodes.Document;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class Main {
    public static String to = "C:\\Users\\Kita\\java_basics\\java_basics\\09_FilesAndNetwork\\hw9.5\\mapmsc";

    public static void main(String[] args) throws IOException {

        Document doc = parseHTML("https://www.moscowmap.ru/metro.html#lines");
        HashMap<String, Line> map = WebParser.parseLines(doc);

        JSONWriter.write(map);
        readStationsOnLine(map);
    }

    public static void readStationsOnLine(HashMap<String, Line> map){
        try {
            JSONObject jo = (JSONObject) new JSONParser().parse(new InputStreamReader(new FileInputStream(to + "\\json.json")));
            JSONObject countStationsObj = (JSONObject) jo.get("stations");

            for (Map.Entry<String, Line> entry : map.entrySet()) {
                JSONArray jsonArr = (JSONArray) countStationsObj.get(entry.getValue().getNumberLine());
                int count = 0;

                while (count < jsonArr.size()) {
                    count++;
                }
                System.out.println("Количество станций на " + entry.getValue().getNumberLine() + " линии составляет: " + count);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }



    public static Document parseHTML(String site) throws IOException {
        Document document = Jsoup.connect(site).maxBodySize(0).get();
        return document;
    }
}
