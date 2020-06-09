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
        Pattern patternSymbol = Pattern.compile("\\D");
        Pattern pattern = Pattern.compile("\\d");

            if (mas[0].equals("LIST")){
                printMap(telBook);
                continue;
            }

            if (mas.length < 2){
                Matcher matcher = pattern.matcher(mas[0]);
                Matcher matcherSymbol = patternSymbol.matcher(mas[0]);
                if (matcher.find()) {
                    if (matcherSymbol.find())
                        System.out.println("Некорректно введен номер телефона. Попробуйте еще раз");
                    else{ if (telBook.containsValue(mas[0])) {
                        System.out.println(getKeys(telBook, mas[0]) + " " + mas[0]);
                    } else {
                        System.out.print("Введите имя: ");
                        Scanner in1 = new Scanner(System.in);
                        String tel = in1.nextLine();
                        telBook.put(tel, mas[0]);
                    }
                }
                }else {
                    if (telBook.containsKey(mas[0])){
                        System.out.println(mas[0] + " " + telBook.get(mas[0]));
                    }else {
                        System.out.print("Введите номер телефона: ");
                        Scanner in1 = new Scanner(System.in);
                        String name = in1.nextLine();
                        Matcher matcherSymbol1 = patternSymbol.matcher(name);
                        if (matcherSymbol1.find())
                            System.out.println("Некорректно введен номер телефона. Попробуйте еще раз");
                        else
                            telBook.put(mas[0], name);
                    }
                }
            }else{
                Matcher matcherSymbol = patternSymbol.matcher(mas[1]);
                if (matcherSymbol.find())
                    System.out.println("Некорректно введен номер телефона. Попробуйте еще раз");
                else
                    telBook.put(mas[0], mas[1]);
            }
        }
    }

    public static Set<String> getKeys(Map<String, String> map, String value) {
        Set<String> keys = new TreeSet<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue().equals(value))
                keys.add(entry.getKey());
        }
        return keys;
    }

    private static void printMap(Map<String, String> map){
        for (String key : map.keySet()){
            System.out.println(key + " " + map.get(key));
        }
    }
}