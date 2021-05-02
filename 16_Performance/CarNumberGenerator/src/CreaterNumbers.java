import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CreaterNumbers extends Thread {

    private char[] letters;
    private char firstLetter;
    private long start;


    public CreaterNumbers(char firstLetter, char[] letters){
        this.firstLetter = firstLetter;
        this.letters = letters;
    }

    @Override
    public void run() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("res/numbers" + firstLetter + ".txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int number = 1; number < 1000; number++) {
            StringBuffer builder = new StringBuffer();
            String number1 = padNumber(number, 3);
            for (int regionCode = 1; regionCode < 100; regionCode++) {
                String regionCod = padNumber(regionCode, 2);
                for (char secondLetter : letters) {
                    for (char thirdLetter : letters) {
                        builder.append(firstLetter);
                        builder.append(number1);
                        builder.append(secondLetter);
                        builder.append(thirdLetter);
                        builder.append(regionCod);
                        builder.append("\n");
                    }
                }

            }
            writer.write(builder.toString());
        }
        writer.flush();
        writer.close();
        System.out.println(Thread.currentThread().getName());
    }

    private static String padNumber(int number, int numberLength) {
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < padSize; i++) {
            builder.append("0");
        }
        builder.append(numberStr);
        return builder.toString();
    }
}
