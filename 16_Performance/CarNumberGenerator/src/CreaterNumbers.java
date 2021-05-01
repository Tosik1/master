import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CreaterNumbers extends Thread {

    private char[] letters;
    private char firstLetter;
    private long start;


    public CreaterNumbers(char firstLetter, char[] letters, long start){
        this.firstLetter = firstLetter;
        this.letters = letters;
        this.start = start;
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
            for (int regionCode = 1; regionCode < 100; regionCode++) {
                for (char secondLetter : letters) {
                    for (char thirdLetter : letters) {
                        builder.append(firstLetter);
                        builder.append(padNumber(number, 3));
                        builder.append(secondLetter);
                        builder.append(thirdLetter);
                        builder.append(padNumber(regionCode, 2));
                        builder.append("\n");
                    }
                }
            }
            writer.write(builder.toString());
        }
        writer.flush();
        writer.close();
        System.out.println((System.currentTimeMillis() - start) + " ms");
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
