import org.json.*;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
            arrayLines.put(lines2);
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
    }
}
