
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws IOException {
        String to = "C:\\Users\\Kita\\java_basics\\java_basics\\09_FilesAndNetwork\\hw9.5\\mapmsc";

        JSONObject mainObj = new JSONObject();
        JSONObject stations = new JSONObject();
        JSONArray linesOnStations = new JSONArray();


        Document doc = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines").maxBodySize(0).get();
        Elements lines1 = doc.select("span.js-metro-line");
        Element metrodata = doc.selectFirst("div");
        Elements stationsTable = doc.select("div.js-metro-stations");


            for (Element line : lines1) {
                ArrayList<String> nameStation = new ArrayList<>();
                nameStation.add(metrodata.after(line).selectFirst("div.js-metro-stations").text());
                stations.put(line.attr("data-line"), nameStation);
                for (String a : nameStation) System.out.println(a);
            }
            mainObj.put("stations", stations);

            try {
                FileWriter file = new FileWriter(to + "\\" + "json.json");

                file.write(mainObj.toJSONString());
                file.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

