import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scannerFrom;
    private static Scanner scannerTo;

    public static void main(String[] args){


        System.out.print("Введите адрес папки откуда копируем: ");
        scannerFrom = new Scanner(System.in);
        String lineFrom = scannerFrom.nextLine().trim();

        System.out.print("Введите адрес папки куда копируем: ");
        scannerTo = new Scanner(System.in);
        String lineTo = scannerTo.nextLine().trim();


        File from = new File(lineFrom);
        File to = new File(lineTo);

        try{
            copyDirectory(from, to);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void copyDirectory(File from, File to) throws IOException {
        if (from.isDirectory()){
            if (!from.exists()){
                to.mkdir();
            }

            File[] fromList = from.listFiles();

            for (File list : fromList){
                File file0 = new File(from + "/" + list.getName());
                File file1 = new File(to + "/" + list.getName());
                copyDirectory(file0, file1);
            }
        }else{
//            Files.copy(Paths.get(from.toString()), Paths.get(to.toString()), StandardCopyOption.REPLACE_EXISTING);
            //Попробовал 2 метода, выдает одну и ту же ошибку.
            List<String> reader = Files.readAllLines(Paths.get(from.toString()));
            Files.write(Paths.get(to.toString()), reader);
        }
    }
}
