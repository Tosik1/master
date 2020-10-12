import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scannerFrom;
    private static Scanner scannerTo;

    public static void main(String[] args) {

        System.out.print("Введите адрес папки откуда копируем: ");
        scannerFrom = new Scanner(System.in);
        String lineFrom = scannerFrom.nextLine().trim();

        System.out.print("Введите адрес папки куда копируем: ");
        scannerTo = new Scanner(System.in);
        String lineTo = scannerTo.nextLine().trim();

        File from = new File(lineFrom);
        File to = new File(lineTo);

        try {
            copyDirectory(from, to);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void copyDirectory(File from, File to) throws IOException {
        for (File list : from.listFiles()) {
            File file0 = new File(from + "/" + list.getName());
            File file1 = new File(to + "/" + list.getName());
            if (list.isDirectory()) {
                new File(file1.getAbsolutePath()).mkdir();
                copyDirectory(file0, file1);
            } else {
                Files.copy(Paths.get(from.toString()), Paths.get(to.toString()), StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }
}
