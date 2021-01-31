import java.util.HashSet;
import java.util.concurrent.ForkJoinPool;

public class Main {

    static HashSet<String> listSites = new HashSet<>();

    public static void main(String[] args) {
        String sites = "https://skillbox.ru";
        Site site = new Site();
        site.setSite(sites);

        listSites.add(sites);
        HashSet<Site> qwe = new HashSet<>(new ForkJoinPool().invoke(new SiteAdress(site)));
        for (Site i : qwe){
            System.out.println(i.getSite());
        }
    }
}
