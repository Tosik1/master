import javax.swing.*;
import java.io.File;
import java.nio.channels.FileChannel;
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



        System.out.println("Размер папки: " + humanReadableByteCount(size, false));
    }

    public static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
}
