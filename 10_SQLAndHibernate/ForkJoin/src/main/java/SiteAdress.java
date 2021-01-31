import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class SiteAdress extends RecursiveTask<HashSet> {

    private HashSet<String> listSites;

    public SiteAdress(HashSet<String> listSites){
        this.listSites = listSites;
    }

    @Override
    protected HashSet<String> compute() {
        HashSet<SiteAdress> taskList = new HashSet<>();
        try {
            for (String str1 : listSites) {
                HashSet<String> newList = new HashSet<>(Parser.parseHTML(str1));
                newList.removeAll(listSites);
                listSites.addAll(newList);
                SiteAdress task = new SiteAdress(newList);
                task.fork();
                taskList.add(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (SiteAdress task : taskList){
                listSites.addAll(task.join());
            try {
                task.wait(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return listSites;
    }

    public HashSet<String> getSites() {
        return listSites;
    }
}
