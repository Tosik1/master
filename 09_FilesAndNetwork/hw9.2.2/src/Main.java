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
            copyFolder(lineFrom, lineTo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void copyFolder(String sourcePath, String destPath) throws IOException {
        File sourceFile = new File(sourcePath);

        for (File file : sourceFile.listFiles()) {
            String sourcePathFile = sourcePath + "/" + file.getName();
            String destPathFile = destPath + "/" + file.getName();

            if (file.isDirectory()) {
                new File(destPathFile).mkdir();
                copyFolder(sourcePathFile, destPathFile);
            } else {
                Files.copy(Paths.get(sourcePathFile), Paths.get(destPathFile), StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }
}
