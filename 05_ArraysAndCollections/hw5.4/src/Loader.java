import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader
{
    public static void main(String agrs[]) {
//        XYZ — различные буквы, N — цифры, R — регион (от 01 до 199);
//        XNNNYZR — пример, A111BC197, Y777HC66
        String[][] symbol = {
                {"E", "T", "Y", "O", "P", "A", "H", "K", "X", "C", "B", "M"},
                {"E", "T", "Y", "O", "P", "A", "H", "K", "X", "C", "B", "M"},
                {"E", "T", "Y", "O", "P", "A", "H", "K", "X", "C", "B", "M"}
        };

        for (int r = 1; r < 200; r++) {
            for (int n = 000; n < 1000; n++){
                for (int i = 0; i < 3; i++){
                    for (int j = 0; j < 12; j++){
                        System.out.println(symbol[i][j] + "" + n + "" + symbol[i][j] + "" + symbol[i][j] + "" + r);
                    }
                }
            }
        }

    }
}