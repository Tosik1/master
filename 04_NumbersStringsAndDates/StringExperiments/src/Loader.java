import java.util.Scanner;

public class Loader
{

    public static void main(String[] args)
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ";
        String alphabetru = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
        int length = alphabet.length();
        int lengthru = alphabetru.length();


        for (int x = 0; x < length; x++)
        {
            char S = alphabet.charAt(x);
            int code = (int) S;
            System.out.println(S + " code: " + code);
        }

        for (int x = 0; x < lengthru; x++)
        {
            char S = alphabetru.charAt(x);
            int code = (int) S;
            System.out.println(S + " code: " + code);
        }
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";

        System.out.println(text);

        int vasiaCash1 = Integer.parseInt(text.substring(text.indexOf(' ',13) + 1, text.indexOf(' ', text.indexOf(' ',13) + 1)));
        int petiaCash1 = Integer.parseInt(text.substring(text.indexOf('-', 33) + 2, text.indexOf(' ', text.indexOf('-', 33) + 2)));
        int mashaCash1 = Integer.parseInt(text.substring(text.lastIndexOf('-') + 2, text.indexOf(' ', text.lastIndexOf('-') + 2)));
        System.out.println(vasiaCash1 + petiaCash1 + mashaCash1);


//Задача со *
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ваши Фамилию Имя Отчество: ");
        String title = scanner.nextLine();

        int n = 0;
        int fs = title.indexOf(' ', 1);
        int ss = title.indexOf(' ', fs + 1);
        int ts = title.indexOf(' ', ss + 1);


        if (fs == -1)
        {
            System.out.println("Ваша фамилия: " + title);
            return;
        }
        else if (ss == -1)
        {
            System.out.println("Ваша фамилия: " + title.substring(n, fs));
            System.out.println("Ваш имя: " + title.substring(fs));
            return;
        }
        else if (ts == -1)
        {
            System.out.println("Ваша фамилия: " + title.substring(n, fs));
            System.out.println("Ваш имя: " + title.substring(fs, ss));
            System.out.println("Ваше отчество: " + title.substring(ss));
        }
        else {
            System.out.println("Ваша фамилия: " + title.substring(n, fs));
            System.out.println("Ваш имя: " + title.substring(fs, ss));
            System.out.println("Ваше отчество: " + title.substring(ss, ts));
        }
    }
}