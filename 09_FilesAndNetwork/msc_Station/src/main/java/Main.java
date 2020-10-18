
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import javax.sound.sampled.Line;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        String to = "C:\\Users\\Kita\\java_basics\\java_basics\\09_FilesAndNetwork\\hw9.5\\mapmsc";

        JSONObject mainObj = new JSONObject();
        JSONObject stations = new JSONObject();
        JSONArray arrayLines = new JSONArray();


        Document doc = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines").maxBodySize(0).get();
        Elements lines1 = doc.select("span.js-metro-line");
        Elements stationsTable = doc.select("div.js-metro-stations");

        for (Element line1 : lines1) {
            ArrayList<String> al = new ArrayList<String>();
            for (Element stations1 : stationsTable) {
                if (stations1.attr("data-line").equals(line1.attr("data-line"))) {
                    String[] text = stations1.text().split("\\d+.");
                    for (String text1 : text) {
                        if (text1.length() == 0) continue;
                        al.add(text1.trim());
                    }
                }
            }
            stations.put(line1.attr("data-line"), al);
            JSONObject lines2 = new JSONObject();
            lines2.put("number", line1.attr("data-line"));
            lines2.put("name", line1.text());
            arrayLines.add(lines2);
        }
        mainObj.put("stations", stations);
        mainObj.put("lines", arrayLines);

        try {
            FileWriter file = new FileWriter(to + "\\" + "json.json");

            file.write(mainObj.toString());
            file.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

//Чтение и вывод в консоль количество станций на каждой линии
        ArrayList<String> asd = new ArrayList<String>(stations.keySet());
        try {
            JSONObject jo = (JSONObject) new JSONParser().parse(new InputStreamReader(new FileInputStream(to + "\\json.json")));
            JSONObject countStationsObj = (JSONObject) jo.get("stations");

            for (int i = 0; i < asd.size(); i++) {
                JSONArray jsonArr = (JSONArray) countStationsObj.get(asd.get(i));
                int count = 0;

                while (count < jsonArr.size()) {
                    count++;
                }
                System.out.println("Количество станций на " + asd.get(i) + " линии составляет: " + count);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
