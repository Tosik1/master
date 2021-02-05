import java.util.*;
import java.util.concurrent.RecursiveTask;

public class SiteAdress extends RecursiveTask<Set<String>> {

    private Set<String> listSites;

    public SiteAdress(Set<String> listSites){
        this.listSites = listSites;
    }

    @Override
    protected Set<String> compute() {
        HashSet<SiteAdress> taskList = new HashSet<>();
        Set<String> main = new HashSet<>();
        try {
            for (String str1 : listSites) {
                Set<String> first = Parser.parseHTML(str1);
                //удаляем все ссылки из нового списка все ссылки которые есть в общем статике
                first.removeAll(Main.listSites);
                //добавляем в общий статик все "эксклюзивные" ссылки
                Main.listSites.addAll(first);
                //делаем задачу из новых ссылок
                SiteAdress task = new SiteAdress(first);
                task.fork();
                taskList.add(task);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (SiteAdress task : taskList){
            main.addAll(task.join());
        }
        return main;
    }
}
