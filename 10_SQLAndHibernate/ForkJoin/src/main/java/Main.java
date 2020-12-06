import java.util.HashSet;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        String sites = "https://skillbox.ru";
        HashSet<String> allSites = new HashSet<>();
        allSites.add(sites);
//        allSites.addAll(new ForkJoinPool().invoke(new SiteAdress(allSites)));
        HashSet<String> qwe = new HashSet<>(new ForkJoinPool().invoke(new SiteAdress(allSites)));
        for (String i : qwe){
            System.out.println(i);
        }
    }
}
