import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class Parser {
    public static final List<String> STOP_WORDS = Arrays.asList("instagram", ".pdf", "twitter", "facebook", "utm", "vkontakte");

    public static Set<String> parseHTML(String site) throws InterruptedException {
        Thread.sleep(150);
        HashSet<String> set = new HashSet<>();
        Document document = null;
        try {
            document = Jsoup.connect(site).timeout(5000).maxBodySize(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Element element : document.select("a")) {
            String d = element.attr("href");
                if (!d.contains("#")) {
                    if (STOP_WORDS.stream().noneMatch(d::contains)) {
                        synchronized (Main.listSites) {
                            if (d.indexOf("/") == 0) {
                                set.add("https://skillbox.ru" + d);
//                                Main.listSites.add("https://skillbox.ru" + d);
                            } else if (d.indexOf("https://skillbox.ru") == 0) {
                                set.add(d);
//                                Main.listSites.add(d);
                            }
                        }
                    }
                }
            }
        return set;
    }
}
