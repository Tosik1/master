import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class SiteAdress extends RecursiveTask<HashSet> {

    private HashSet<String> listSites = new HashSet<>();
    private HashSet<String> sites;

    private String fullMap;

    public SiteAdress(HashSet<String> sites){
        this.sites = sites;
    }

    @Override
    protected HashSet<String> compute() {
        List<SiteAdress> taskList = new ArrayList<>();
        try {
            for (String site : sites) {
                listSites = Parser.parseHTML(site);
                SiteAdress task = new SiteAdress(listSites);
                task.fork();
                taskList.add(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (SiteAdress task : taskList){
            sites.addAll(task.join());
            try {
                task.wait(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return sites;
    }

    public HashSet<String> getSites() {
        return sites;
    }
}
