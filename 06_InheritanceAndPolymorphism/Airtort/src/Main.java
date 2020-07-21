import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ParseException {

        Date date0 = new Date();
        Date date1 = new Date(date0.getTime() + 2 * 3600 * 1000);
        
        ArrayList<Flight> airports = new ArrayList<>();

        for (int a = 0; a < Airport.getInstance().getTerminals().size(); a++)
        {
            airports.addAll(Airport.getInstance().getTerminals().get(a).getFlights());
        }
        airports.stream()
                .filter(d -> d.getDate().after(date0) && d.getDate().before(date1))
                .filter(t -> t.getType().toString().equals("DEPARTURE"))
                .forEach(System.out::println);
    }
}
