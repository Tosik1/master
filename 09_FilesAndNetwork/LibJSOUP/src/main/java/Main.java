import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Main {
    public static void main(String[] args) throws IOException {
        String to = "C:\\Users\\Kita\\java_basics\\java_basics\\09_FilesAndNetwork\\hw9.4\\images";

        Document doc = Jsoup.connect("https://lenta.ru").get();
        Elements img = doc.select("img");
        for (Element a : img) {
            System.out.println(a.toString());
            URL url = new URL(a.attr("abs:src"));
            InputStream in = url.openStream();
            Files.copy(in, Paths.get(to + "\\" + a.toString().substring
                    (a.toString().lastIndexOf("/") + 1,
                            a.toString().lastIndexOf(" ", a.toString().lastIndexOf(" ") - 1) - 1)), StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
