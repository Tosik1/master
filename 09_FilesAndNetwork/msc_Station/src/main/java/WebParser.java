import mscMetro.Line;
import mscMetro.Station;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;

public class WebParser {
    public static HashMap<String, Line> parseLines(Document doc){
        HashMap<String, Line> mapLines = new HashMap<>();
        Elements lines = doc.select("span.js-metro-line"); //номер и название линии
        Elements stationsTable = doc.select("div.js-metro-stations"); //таблица


        for (Element line1 : lines) {
            Line line11 = new Line(line1.text(), line1.attr("data-line"));
            line11.setStations(parseStations(stationsTable, line11));

            mapLines.put(line11.getNumberLine(), line11);

        }

        return mapLines;
    }

    public static ArrayList<Station> parseStations(Elements stationsTable, Line line){
        ArrayList<Station> al = new ArrayList<>();
        for (Element el : stationsTable){
            if (el.attr("data-line").equals(line.getNumberLine())) {
                Elements stationFromTable = el.select("a");
                for (Element a : stationFromTable) {
                    Station station = new Station(a.select("span.name").text());
                    if (a.select("span.t-icon-metroln").size() != 0) {
                        for (Element con : a.select("span.t-icon-metroln")){
                            String b = con.attr("title");
                            station.addCon(con.attr("class").substring(18),
                                    b.substring(b.indexOf("«") + 1, b.lastIndexOf("»")));
                        }
                    }
                    al.add(station);
                }
            }
        }
        return al;
    }
}
