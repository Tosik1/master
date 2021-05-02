import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Loader {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

        ArrayList<Thread> threads = new ArrayList();

        for (char firstLetter : letters) {
                Thread createNumbers = new CreaterNumbers(firstLetter, letters);
                createNumbers.start();
                threads.add(createNumbers);
        }

        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Все потоки завершили работу за:" + (System.currentTimeMillis() - start));
    }
}
