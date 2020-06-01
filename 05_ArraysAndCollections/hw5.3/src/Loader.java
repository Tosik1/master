
import java.util.HashSet;
import java.util.Scanner;
import java.util.function.DoubleToIntFunction;

public class Loader
{
    public static void main(String agrs[]) {
        HashSet<String> emails = new HashSet<>();

        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.print("Введите email: ");
            String n = in.nextLine();


            if (n.indexOf('A') == 0) {
                if (n.indexOf('@') != -1){
                    if (n.indexOf('.') > n.indexOf('@') + 1){
                        emails.add(n.substring(4));
                    }
                    else
                        System.out.println("Пропущен символ \".\" после символа \"@\", попробуйте еще раз.");
                }
                else
                    System.out.println("Пропущен символ \"@\", попробуйте еще раз.");
            }


            if (n.indexOf('L') == 0) {
                for (String email : emails) {
                    System.out.println(email);
                }
            }
        }
    }
}