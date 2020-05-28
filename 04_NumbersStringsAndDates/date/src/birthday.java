import java.text.SimpleDateFormat;
import java.util.Calendar;

public class birthday {
    public static void main(String[] args)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d.MM.yyyy - EEEE");
        Calendar calendar = Calendar.getInstance();
        calendar.set(1996, 3, 22);

        for (int i = 0; i <= 24; i++)
        {
            System.out.println(i + " - " + dateFormat.format(calendar.getTime()));
            calendar.add(Calendar.YEAR, 1);
        }
    }
}
