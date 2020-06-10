import java.util.*;

public class Loader
{
    public static void main(String agrs[]) {

                String[] symbol1 = {"E", "T", "Y", "O", "P", "A", "H", "K", "X", "C", "B", "M"};

        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> list1 = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        TreeSet<String> tree = new TreeSet<>();


        for (int i = 0; i < 12; i++) {
                    for (int n = 0; n < 10; n++) {
                        for (int i2 = 0; i < 12; i++) {
                            for (int i3 = 0; i < 12; i++) {
                                for (int r = 1; r < 200; r++) {
                                    StringBuilder builder = new StringBuilder();
                                    if (r < 10)
                                        list.add(builder.append(symbol1[i]).append(n).append(n).append(n).append(symbol1[i2]).append(symbol1[i3]).append("0").append(r).toString());
                                    else list.add(builder.append(symbol1[i]).append(n).append(n).append(n).append(symbol1[i2]).append(symbol1[i3]).append(r).toString());
                        }
                    }
                }
            }
        }

        set.addAll(list);
        tree.addAll(list);
        list1.addAll(list);
        Collections.sort(list);

        for (;;){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите блатной номер: ");
        String scanner1 = scanner.nextLine();

        long start1 = System.nanoTime();
        list1.contains(scanner1);
        long end1 = System.nanoTime();
        long start2 = System.nanoTime();
        int i = Collections.binarySearch(list, scanner1);
        long end2 = System.nanoTime();
        long start3 = System.nanoTime();
        set.contains(scanner1);
        long end3 = System.nanoTime();
        long start4 = System.nanoTime();
        tree.contains(scanner1);
        long end4 = System.nanoTime();

        System.out.println("Поиск перебором: " + (end1 - start1));
        System.out.println("Бинарный поиск: " + (end2 - start2));
        System.out.println("Поиск в HashSet: " + (end3 - start3));
        System.out.println("Поиск в TreeSet: " + (end4 - start4));
    }
}}