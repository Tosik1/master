public class Loader
{
    public static void main(String args[])
    {
        String text = "Каждый охотник желает знать, где сидит фазан.";

        String[] colorWord = text.split(",?\\s+");

        for (int i = 0; i < colorWord.length; i++)
        {
            System.out.println(colorWord[colorWord.length - 1 - i]);
        }
    }
}
