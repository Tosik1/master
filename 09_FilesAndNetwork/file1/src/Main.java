import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner;
    private static List<File> packages;
    private static List<File> files;

    public static void main(String[] args) {
        long size = 0;
        double c;
        int b;
        String kb = "Килобайт(ов)";
        String mb = "Мегабайт(ов)";
        String gb = "Гигабайт(ов)";
        String ei;

        System.out.print("Введите адрес папки: ");
        scanner = new Scanner(System.in);
        String line = scanner.nextLine().trim();

        File address = new File(line);
        packages = new ArrayList<>();
        files = new ArrayList<>();

        packages.addAll(Arrays.asList(address.listFiles()));

        try {
            for (; ; ) {
                if (packages.size() == 0) {
                    break;
                } else {
                    if (packages.get(0).isDirectory()) {
                        packages.addAll(Arrays.asList(packages.get(0).listFiles()));
                        packages.remove(0);
                    } else {
                        files.add(packages.get(0));
                        packages.remove(0);
                    }
                }

            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        for (int a = 0; a < files.size(); a++){
            size += files.get(a).length();
        }

        c = size;
        for (b = 0; c > 100; ++b){
            c /= 1024;
        }
        if (b == 0){
            ei = "Байт(ов)";
        } if (b == 1){
            ei = mb;
        } if (b == 2){
            ei = kb;
        } else {
            ei = gb;
        }


        System.out.println("Размер папки: " + c + " " + ei);
    }
}
