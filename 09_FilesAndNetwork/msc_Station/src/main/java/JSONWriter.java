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
    private static JSONArray connections = new JSONArray();

    public static void write(HashMap<String, Line> map) {
        selectLines(map);

        mainObj.put("stations", stations);
        mainObj.put("lines", arrayLines);
        mainObj.put("connections", connections);

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

                //добвление connections для каждой линии без сравнения значений
                JSONArray array2 = new JSONArray();
                if (st.getCon().size() !=0) {
                    JSONObject obj0 = new JSONObject();
                        obj0.put("line", entry.getValue().getNumberLine());
                        obj0.put("station", st.getNameStation());
                        array2.add(obj0);
                        for (String key : st.getCon().keySet()) {
                            JSONObject obj1 = new JSONObject();
                            obj1.put("line", key);
                            obj1.put("station", st.getCon().get(key));
                            array2.add(obj1);
                        }
                        connections.add(array2);
                }
            }
            stations.put(entry.getValue().getNumberLine(), list);
            JSONObject lines2 = new JSONObject();
            lines2.put("number", entry.getValue().getNumberLine());
            lines2.put("name", entry.getValue().getNameLine());
            arrayLines.add(lines2);
        }
    }
}
