import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main
{
    private static int newWidth = 300;

    public static void main(String[] args) throws IOException {
        String srcFolder = "/users/sortedmap/Desktop/src";
        String dstFolder = "/users/sortedmap/Desktop/dst";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите количество ядер на компьютере: ");
        String countCore = br.readLine();
        int count = Integer.parseInt(countCore);
//        или проще:
//        int count = Runtime.getRuntime().availableProcessors();

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

        int part = files.length / count;

        int[] lenthPath = new int[count];
        for (int i = 0; i < count; i++){
            if (i == count - 1){
                lenthPath[i] = files.length - part * (i - 1);
            } else {
                lenthPath[i] = part;
            }
        }

        for (int i = 0; i < count; i++){
            File[] files0 = new File[lenthPath[i]];
            System.arraycopy(files, part * i, files0, 0, part * (i + 1));
            ImageResizer resizer1 = new ImageResizer(files0, newWidth, dstFolder, start);
            new Thread(resizer1).start();
        }
    }
}
