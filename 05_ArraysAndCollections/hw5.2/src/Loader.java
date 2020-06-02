import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader
{
    public static void main(String agrs[]) {
        ArrayList<String> todoList = new ArrayList<>();

        while (true) {
            String text = "";
            String text1 = "";

            Scanner in = new Scanner(System.in);
            System.out.print("Введите команду: ");
            String n = in.nextLine();

            String[] mas = n.split(" ");

            if (mas.length > 1) {
                Pattern pattern = Pattern.compile("\\d");
                Matcher matcher = pattern.matcher(mas[1]);

                for (int i = 1; i < mas.length; i++){
                    text1 = text1 + mas[i] + " ";
                }

                for (int i = 2; i < mas.length; i++){
                    text = text + mas[i] + " ";
                }

                if (mas[0].equals("ADD")) {
                    if (matcher.find()) {
                        int number = Integer.parseInt(mas[1]);
                        if (number > todoList.size()) {
                            todoList.add(text);
                        } else
                            todoList.add(number, text);

                    } else {
                        todoList.add(text1);
                    }
                }

                if (mas[0].equals("EDIT")) {
                    if (matcher.find()) {
                        int number = Integer.parseInt(mas[1]);
                        todoList.remove(number);
                        todoList.add(number, text);
                    } else {
                        System.out.println("Попробуйте ввести номер строки.");
                    }
                }

                if (mas[0].equals("DELETE")) {
                    if (matcher.find()) {
                        int number = Integer.parseInt(mas[1]);
                        todoList.remove(number);
                    } else {
                        System.out.println("Попробуйте ввести номер строки.");
                    }
                }
            }else {
                if (n.equals("LIST")) {
                    for (int a = 0; a < todoList.size(); a++) {
                        System.out.println(a + " - " + todoList.get(a));
                    }
                }
            }
        }
    }
}
