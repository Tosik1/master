import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        String sites = "https://skillbox.ru";
        String sitesLine = new ForkJoinPool().invoke(new SiteAdress(sites));
        System.out.println(sitesLine);

    }
}
