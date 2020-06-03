import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader
{
    public static void main(String agrs[]) {

        TreeMap<String, String> telBook = new TreeMap<>();

        while (true){
        Scanner in = new Scanner(System.in);
        System.out.print("Введите имя и/или номер телефона : ");
        String n = in.nextLine();
        String[] mas = n.split(" ");

            if (mas[0].equals("LIST")){
                printMap(telBook);
                continue;
            }

            if (mas.length < 2){
                Pattern pattern = Pattern.compile("\\d");
                Matcher matcher = pattern.matcher(mas[0]);
                if (matcher.find()){

                    if (telBook.containsValue(mas[0])){
                        System.out.println(getKeys(telBook, mas[0]) + " " + mas[0]);
                    }else{
                        System.out.print("Введите номер имя: ");
                        Scanner in1 = new Scanner(System.in);
                        String tel = in1.nextLine();
                        telBook.put(tel, mas[0]);
                    }
                }else {
                    if (telBook.containsKey(mas[0])){
                        System.out.println(mas[0] + " " + telBook.get(mas[0]));
                    }else {
                        System.out.print("Введите номер телефона: ");
                        Scanner in1 = new Scanner(System.in);
                        String name = in1.nextLine();
                        telBook.put(mas[0], name);
                    }
                }
            }else{
                telBook.put(mas[0], mas[1]);
            }
        }
    }

    public static <K, V> Set<K> getKeys(Map<K, V> map, V value) {
        Set<K> keys = new TreeSet<>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    private static void printMap(Map<String, String> map){
        for (String key : map.keySet()){
            System.out.println(key + " " + map.get(key));
        }
    }
}