import java.io.*;
import java.nio.file.*;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        File from = new File("C:\\Users\\Kita\\Desktop\\from");
        File to = new File("C:\\Users\\Kita\\Desktop\\to");

        if(!from.exists()){
            System.exit(0);
        }else {
            try {
                vozvratFile(from, to);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }


    public static void vozvratFile(File from, File to) throws IOException {
        if (from.isDirectory()) {
            if (!to.exists()) {
                to.mkdir();
            }

            String[] from1 = from.list();

            for (String from2 : from1){
                File from3 = new File(from, from2);

                File to3 = new File(to, from2);
                vozvratFile(from3, to3);
            }
        }
        else {
            InputStream in = new FileInputStream(from);
            OutputStream out = new FileOutputStream(to);

            byte[]buffer = new byte[1024];

            int length;
            while ((length = in.read(buffer)) > 0){
                out.write(buffer, 0, length);
            }

            in.close();
            out.close();
        }
    }
}



