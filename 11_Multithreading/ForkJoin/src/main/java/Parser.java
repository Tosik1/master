import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Parser {
    public static HashSet<Site> parseHTML(String site) throws IOException {
        HashSet<Site> set = new HashSet<>();
        Document document = Jsoup.connect(site).ignoreContentType(true).ignoreHttpErrors(true)
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:49.0) Gecko/20100101 Firefox/49.0")
                .followRedirects(true).timeout(100000).maxBodySize(0).get();
        List<Element> abs = document.children();
        for (Element a : abs){
            Elements b = a.select("a");
            for (Element c : b){
                String d = c.attr("href");
                if (!d.contains("#")) {
                    if (d.indexOf("/") == 0) {
                        Site site1 = new Site();
                        site1.setSite("https://skillbox.ru" + d);
                        set.add(site1);
                    } else if (d.indexOf("https://skillbox.ru") == 0) {
                        Site site1 = new Site();
                        site1.setSite(d);
                        set.add(site1);
                    }
                }
            }
        }
        return set;
    }
}
