public class loader
{
    public static void main(String args[])
    {
        String[][] crest = {
                {"x", " ", " ", " ", " ", " ", "x"},
                {" ", "x", " ", " ", " ", "x", " "},
                {" ", " ", "x", " ", "x", " ", " "},
                {" ", " ", " ", "x", " ", " ", " "},
                {" ", " ", "x", " ", "x", " ", " "},
                {" ", "x", " ", " ", " ", "x", " "},
                {"x", " ", " ", " ", " ", " ", "x"}
        };

        for (int i = 0; i < crest.length; i++)
        {
            System.out.println();
            for (int j = 0; j < crest[i].length; j++)
            {
                System.out.print(crest[i][j]);
            }
        }
    }
}
