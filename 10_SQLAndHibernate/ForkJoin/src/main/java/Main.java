import java.util.HashSet;
import java.util.concurrent.ForkJoinPool;

public class Main {

    static HashSet<String> listSites = new HashSet<>();

    public static void main(String[] args) {
        String sites = "https://skillbox.ru";
        listSites.add(sites);
        HashSet<String> qwe = new HashSet<>(new ForkJoinPool().invoke(new SiteAdress(listSites)));
        for (String i : qwe){
            System.out.println(i);
        }
    }
}
