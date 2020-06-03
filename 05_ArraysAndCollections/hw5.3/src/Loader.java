
import java.util.HashSet;
import java.util.Scanner;
import java.util.function.DoubleToIntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader
{
    public static void main(String agrs[]) {
        HashSet<String> emails = new HashSet<>();
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{1,}" + "((\\.|\\_|-{0,1})[a-zA-Z0-9]{1,})*" + "@"
                + "[a-zA-Z0-9]{1,}" + "((\\.|\\_|-{0,1})[a-zA-Z0-9]{1,})*" + "\\.[a-zA-Z]{2,}$");

        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.print("Введите email: ");
            String n = in.nextLine();

            String[] mas = n.split(" ");

            if (mas[0].equals("ADD")) {
                Matcher matcher = pattern.matcher(mas[1]);
                if (matcher.matches())
                        emails.add(mas[1]);
                else
                    System.out.println("Неверно введен Email.");
            }


            if (mas[0].equals("LIST")) {
                for (String email : emails) {
                    System.out.println(email);
                }
            }
        }
    }
}