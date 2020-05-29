import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringExperiments {

    public static void main(String[] args) {
//        String text = "Вася заработал 5000 рублей, Петя - 7000 рубля, а Маша - 30000 рублей, Кирилл - 12, Андрей - 700";
//
//        System.out.println(text);
//
//        String s1 = "";
//        int sum = 0;
//        int numb = 0;
//        Pattern pattern = Pattern.compile(" ");
//        String[] strings = pattern.split(text);
//
//        for (int i = 0; i < strings.length; i++)
//        {
//            String s0 = strings[i].replaceAll("[^0-9]", "");
//            if (s1.equals(s0) == false)
//            {
//                numb = Integer.parseInt(s0);
//                sum = sum + numb;
//            }
//
//        }
//        System.out.println(sum);


        //Задание №2
        String engNews = "US President Donald Trump has argued it is \"a badge of honour\" that the US has the world's highest number of confirmed Covid-19 infections.\n" +
                "\n" +
                "\"I look at that as, in a certain respect, as being a good thing because it means our testing is much better,\" he said at the White House.\n" +
                "\n" +
                "The US has 1.5 million coronavirus cases and nearly 92,000 deaths, according to Johns Hopkins University.\n" +
                "\n" +
                "In second place is Russia, with nearly 300,000 confirmed cases.\n" +
                "\n" +
                "What did Trump say?\n" +
                "On Monday, Mr Trump was hosting his first cabinet meeting since the US outbreak began.\n" +
                "\n" +
                "\"By the way, you know when you say that we lead in cases, that's because we have more testing than anybody else,\" he told reporters.\n" +
                "\n" +
                "\"So when we have a lot of cases,\" he continued, \"I don't look at that as a bad thing, I look at that as, in a certain respect, as being a good thing because it means our testing is much better.\"";

        String[] newText = engNews.split(" ");
        for (int a = 0; a < newText.length; a++)
        {
            String withoutZnack = newText[a].replaceAll("[^A-z]", "");
            System.out.println(withoutZnack);
        }
//

//        //Задание №3
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Введите ваши Фамилию Имя Отчество: ");
//        String title = scanner.nextLine();
//
//        Pattern pattern = Pattern.compile(" ");
//        String[] strings = pattern.split(title);
//
//        for (int a = 0; a < strings.length; a++)
//        {
//            if (a == 0)
//            System.out.println("Ваша фамилия: " + strings[a]);
//            if (a == 1)
//            System.out.println("Ваше имя: " + strings[a]);
//            if (a == 2)
//            System.out.println("Ваше отчество: " + strings[a]);
//        }

//        //Задание №4
//
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Введите номера телефонов: ");
//        String title = scanner.nextLine();
//
//        String a = title.replaceAll("[^0-9]", "");
//
//        if (a.indexOf('8') == 0 || a.indexOf('7') == 0)
//        {
//            System.out.println("+7 " + a.substring(1, 4) + " " + a.substring(4, 7) + "-" + a.substring(7, 9) + "-" + a.substring(9, 11));
//        }
//
//        if (a.length() == 10)
//        {
//            System.out.println("+7 " + a.substring(0, 3) + " " + a.substring(3, 6) + "-" + a.substring(6, 8) + "-" + a.substring(8, 10));
//        }

    }
}