import mscMetro.Line;
import mscMetro.Station;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JSONWriter {
    private static JSONObject mainObj = new JSONObject();
    private static JSONObject stations = new JSONObject();
    private static JSONArray arrayLines = new JSONArray();

    public static void write(HashMap<String, Line> map) {
        selectLines(map);

        mainObj.put("stations", stations);
        mainObj.put("lines", arrayLines);

        try {
            FileWriter file = new FileWriter(Main.to + "\\" + "json.json");

            file.write(mainObj.toString());
            file.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void selectLines(HashMap<String, Line> map){
        for (Map.Entry<String, Line> entry : map.entrySet()) {
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
}
