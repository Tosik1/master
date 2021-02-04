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

    public static void write(HashSet<String> set){
        List<String> list = set.stream().sorted().collect(Collectors.toList());
        String b = null;
//        Set<String> map = new TreeSet<>(set);

        try {
            FileWriter file = new FileWriter(to + "\\" + "json.txt", false);
            int count = 0;
            for (String a : list) {
                if (b != null){
                    if (a.startsWith(b)){
                        count++;
                        file.write( "\t" + a + "\n");
                        b = a;
                    }
                    file.write( a + "\n");
                    continue;
                }
                b = a;
                file.write(a + "\n");
            }
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
