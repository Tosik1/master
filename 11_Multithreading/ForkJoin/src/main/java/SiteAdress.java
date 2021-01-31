import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class SiteAdress extends RecursiveTask<HashSet> {

    private Site site;

    public SiteAdress(Site site){
        this.site = site;
    }

    @Override
    protected HashSet<String> compute() {
        HashSet<SiteAdress> taskList = new HashSet<>();
        HashSet<String> listSites = new HashSet<>();

        try {
            for (Site child : site.getChildeSite()) {
                SiteAdress task = new SiteAdress(child);
                task.fork();
                taskList.add(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (SiteAdress task : taskList){
            listSites.addAll(task.join());

//            try {
//                task.wait(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

        return listSites;
    }
}
