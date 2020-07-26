import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class RouteCalculatorTest extends TestCase
{
    List<Station> route0, route1, route2;
    RouteCalculator calculator;
    StationIndex stationIndex;
    Line line1, line2, line3;

    @Override
    protected void setUp() throws Exception {

        stationIndex = new StationIndex();

        route0 = new ArrayList<>();
        route1 = new ArrayList<>();
        route2 = new ArrayList<>();

        line1 = new Line(1, "Первая");
        line2 = new Line(2, "Вторая");
        line3 = new Line(3, "Третья");

        line1.addStation(new Station("Первая", line1));
        line1.addStation(new Station("Вторая", line1));

        line2.addStation(new Station("Николаевская", line2));
        line2.addStation(new Station("Ленинская", line2));

        line3.addStation(new Station("Столовая", line3));
        line3.addStation(new Station("Продуктовый", line3));

        ArrayList<Station> connectionStation1 = new ArrayList<>();
        connectionStation1.add(line1.getStations().get(1));
        connectionStation1.add(line2.getStations().get(0));
        stationIndex.addConnection(connectionStation1);

        ArrayList<Station> connectionStation2 = new ArrayList<>();
        connectionStation2.add(line2.getStations().get(1));
        connectionStation2.add(line3.getStations().get(0));
        stationIndex.addConnection(connectionStation2);

        route0.add(line1.getStations().get(0));
        route0.add(line1.getStations().get(1));

        route1.add(line1.getStations().get(0));
        route1.add(line1.getStations().get(1));
        route1.add(line2.getStations().get(0));
        route1.add(line2.getStations().get(1));

        route2.add(line1.getStations().get(0));
        route2.add(line1.getStations().get(1));
        route2.add(line2.getStations().get(0));
        route2.add(line2.getStations().get(1));
        route2.add(line3.getStations().get(0));
        route2.add(line3.getStations().get(1));
    }

    public void testCalculateDuration()
    {
        double actual = RouteCalculator.calculateDuration(route0);
        double expected = 2.5;

        assertEquals(expected, actual);
    }

    public void testGetShortestRoute()
    {
        calculator = new RouteCalculator(stationIndex);
        List<Station> cal = calculator.getShortestRoute(line1.getStations().get(0), line1.getStations().get(1));
        assertEquals(cal, route0);


        List<Station> cal1 = calculator.getShortestRoute(line1.getStations().get(0), line2.getStations().get(1));
        assertEquals(cal1, route1);


        List<Station> cal2 = calculator.getShortestRoute(line1.getStations().get(0), line3.getStations().get(1));
        assertEquals(cal2, route2);
    }
}
