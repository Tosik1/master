import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class SiteAdress extends RecursiveTask<String> {

    private String siteAdress;
    private ArrayList<String> listSites = new ArrayList<>();

    public SiteAdress(String siteAdress){
        this.siteAdress = siteAdress;
    }

    @Override
    protected String compute() {
        List<SiteAdress> taskList = new ArrayList<>();
        String fullMap = "";
        try {
            Document doc = parseHTML(siteAdress);
            Elements sites = doc.select("a");
            for (Element e : sites) {
                String a = e.attr("href");
                if (a.substring(0,1).equals("/")) {
                    if (!a.contains("#")) {
                        listSites.add(a);
                        SiteAdress task = new SiteAdress("https://skillbox.ru" + a);
                        task.fork();
                        taskList.add(task);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (SiteAdress task : taskList){
            fullMap = fullMap.concat(task.join()) ;
            try {
                task.wait(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return fullMap;
    }

    public static Document parseHTML(String site) throws IOException {
        Document document = Jsoup.connect(site).ignoreContentType(true).ignoreHttpErrors(true).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:49.0) Gecko/20100101 Firefox/49.0").followRedirects(true).timeout(100000).maxBodySize(0).get();
        return document;
    }
}
