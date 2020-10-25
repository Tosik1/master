
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main {
    public static ArrayList<String> numberLines = new ArrayList<>();
    public static JSONObject mainObj = new JSONObject();
    public static JSONObject stations = new JSONObject();
    public static JSONArray arrayLines = new JSONArray();
    public static String to = "C:\\Users\\Kita\\java_basics\\java_basics\\09_FilesAndNetwork\\hw9.5\\mapmsc";

    public static void main(String[] args) throws IOException {

        Document doc = parseHTML("https://www.moscowmap.ru/metro.html#lines");

        selectLines(doc);

        mainObj.put("stations", stations);
        mainObj.put("lines", arrayLines);

        writeToJSON(mainObj);
        readStationsOnLine();
    }

    public static void readStationsOnLine(){
        try {
            JSONObject jo = (JSONObject) new JSONParser().parse(new InputStreamReader(new FileInputStream(to + "\\json.json")));
            JSONObject countStationsObj = (JSONObject) jo.get("stations");

            for (int i = 0; i < numberLines.size(); i++) {
                JSONArray jsonArr = (JSONArray) countStationsObj.get(numberLines.get(i));
                int count = 0;

                while (count < jsonArr.size()) {
                    count++;
                }
                System.out.println("Количество станций на " + numberLines.get(i) + " линии составляет: " + count);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void selectLines(Document doc){
        Elements lines = doc.select("span.js-metro-line");
        Elements stationsTable = doc.select("div.js-metro-stations");
        for (Element line1 : lines) {
            ArrayList<String> al = new ArrayList<String>();
            numberLines.add(line1.attr("data-line"));

            selectStations(stationsTable, line1, al);

            stations.put(line1.attr("data-line"), al);
            JSONObject lines2 = new JSONObject();
            lines2.put("number", line1.attr("data-line"));
            lines2.put("name", line1.text());
            arrayLines.add(lines2);
        }
    }

    public static void selectStations(Elements stations, Element line1, ArrayList<String> masStations){
        for (Element stations1 : stations) {
            if (stations1.attr("data-line").equals(line1.attr("data-line"))) {
                String[] text = stations1.text().split("\\d+.");
                for (String text1 : text) {
                    if (text1.length() == 0) continue;
                    masStations.add(text1.trim());
                }
            }
        }
    }

    public static Document parseHTML(String site) throws IOException {
        Document document = Jsoup.connect(site).maxBodySize(0).get();
        return document;
    }

    public static void writeToJSON(JSONObject obj){
        try {
            FileWriter file = new FileWriter(to + "\\" + "json.json");

            file.write(obj.toString());
            file.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
