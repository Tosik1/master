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
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class Main {
    public static ArrayList<String> numberLines = new ArrayList<>();
    public static JSONObject mainObj = new JSONObject();
    public static JSONObject stations = new JSONObject();
    public static JSONArray arrayLines = new JSONArray();
    public static String to = "C:\\Users\\Kita\\java_basics\\java_basics\\09_FilesAndNetwork\\hw9.5\\mapmsc";

    public static void main(String[] args) throws IOException {

        Document doc = parseHTML("https://www.moscowmap.ru/metro.html#lines");
        HashMap<String, Line> map = WebParser.parseLines(doc);

        selectLines(map);

        mainObj.put("stations", stations);
        mainObj.put("lines", arrayLines);

        writeToJSON(mainObj);
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

    public static void selectLines(HashMap<String, Line> map){
        for (Map.Entry<String, Line> entry : map.entrySet()) {
            JSONArray ja = new JSONArray();
            ArrayList<String> list = new ArrayList<>();
            for (Station st : entry.getValue().getStations()){
                list.add(st.getNameStation());
            }
            stations.put(entry.getValue().getNumberLine(), list);
            JSONObject lines2 = new JSONObject();
            lines2.put("number", entry.getValue().getNumberLine());
            lines2.put("name", entry.getValue().getNameLine());
            arrayLines.add(lines2);
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
