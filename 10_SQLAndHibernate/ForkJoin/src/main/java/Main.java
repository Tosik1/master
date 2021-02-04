import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.ForkJoinPool;

public class Main {

    static HashSet<String> listSites = new HashSet<>();

    public static void main(String[] args) throws InterruptedException {
        String sites = "https://skillbox.ru";
        new ForkJoinPool().invoke(new SiteAdress(Parser.parseHTML(sites)));
        Writer.write(listSites);
    }
}
