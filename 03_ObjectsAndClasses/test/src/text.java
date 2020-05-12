import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;

import java.io.*;

public class text {
    public static void main(String[] args)
    {
        Airport airport = Airport.getInstance();
        System.out.println("Количество самолетов: " + airport.getAllAircrafts().size());


    }
}
