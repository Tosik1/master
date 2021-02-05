import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Writer {
    private static String to = "C:\\Users\\Kita\\Desktop";
    private static String sites = "https://skillbox.ru";

    public static void write(Set<String> set){
        List<String> list = set.stream().sorted().collect(Collectors.toList());
//        Set<String> map = new TreeSet<>(set);

        try {
            FileWriter file = new FileWriter(to + "\\" + "json.txt", false);
            for (String a : list) {
                String b = a.replace("/", "");
                int count = a.length() - b.length() - 3;
                if (a.contains("?")){
                    count++;
                }
                for (int i = 0; i < count; i++){
                    file.write("\t");
                }
                file.write(a + "\n");
            }
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
