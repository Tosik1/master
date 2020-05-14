public class Main
{

    public static void main(String[] args)
    {
//        Container container = new Container();
//        container.count += 7843;
        Integer xx = sumDigits(115111);
        System.out.println(xx);
    }

    public static Integer sumDigits(Integer number)
    {
        int sum = 0;
        int x = Integer.toString(number).length() - 1;
        String text = Integer.toString(number);

        while (0 <= x)
        {

            char c = text.charAt(x);
            String g = String.valueOf(c);
            Integer t = Integer.parseInt(g);
            sum = sum + t;
            x--;
        }

        return sum;

    }
}
