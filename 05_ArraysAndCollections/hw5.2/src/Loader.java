import java.util.ArrayList;
import java.util.Scanner;

public class Loader
{
    public static void main(String agrs[]) {
        ArrayList<String> todoList = new ArrayList<>();

        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.print("Введите команду: ");
            String n = in.nextLine();


                if (n.indexOf('A') == 0) {
                    String bb = n.substring(2, 7).replaceAll("[^0-9]", "");
                    if (bb.length() == 0){
                        todoList.add(n.substring(4));
                    }else {
                        int number = Integer.parseInt(n.substring(4, n.indexOf(' ', 4)));
                        if (number > todoList.size()){
                            todoList.add(n.substring(n.indexOf(' ', 4) + 1));
                        }else
                            todoList.add(number, n.substring(n.indexOf(' ', 4) + 1));
                    }
                }

                if (n.indexOf('E') == 0) {
                    String bb = n.substring(3, 8).replaceAll("[^0-9]", "");
                    if (bb.length() != 0) {
                        int number = Integer.parseInt(n.substring(5, n.indexOf(' ', 5)));
                        todoList.remove(number);
                        todoList.add(number, n.substring(7));
                    } else {
                        System.out.println("Попробуйте ввести номер строки.");
                    }
                }

                if (n.indexOf('D') == 0) {
                    String bb = n.substring(6).replaceAll("[^0-9]", "");
                    if (bb.length() != 0) {
                        int number = Integer.parseInt(n.substring(7));
                        todoList.remove(number);
                    } else {
                        System.out.println("Попробуйте ввести номер строки.");
                    }
                }

                if (n.indexOf('L') == 0) {
                    for (int a = 0; a < todoList.size(); a++) {
                        System.out.println(a + " - " + todoList.get(a));
                    }
                }

        }
    }
}
