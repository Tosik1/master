import java.util.Scanner;

public class loader
{
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите нечетный номер строк и столбцов: ");
        int n = in.nextInt();

        String[][] crest = new String[n][n];

        for (int i = 0; i < crest.length; i++)
        {
            System.out.println();
            for (int j = 0; j < crest[i].length; j++)
            {
                if (i == j || i + j == (n - 1))
                    System.out.print(crest[i][j] = "x");
                else
                    System.out.print(crest[i][j] = " ");
            }
        }
    }
}
