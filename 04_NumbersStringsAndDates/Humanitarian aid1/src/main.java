import java.time.zone.ZoneRules;

public class main
{
    public static void main(String args[])
    {
        int countBox = 325;


// Вместимость контейнера и машины
        int maxBoxOnContainer = 27;
        int maxContainerOnCar = 12;

//Начало нумерации
        int numberContainer = 1;
        int numberBox = 1;
        int numberCar = 1;

//  Подсчет количества контейнеров и машин
        double countContainer = countBox / maxBoxOnContainer;
        if (countBox % maxBoxOnContainer != 0)
        {
            countContainer++;
        }

        double countCar = countContainer / maxContainerOnCar;
        if (countContainer % maxContainerOnCar != 0)
        {
            countCar++;
        }


//Вывод получается:
        for (int x = 1; x <= countCar; x++)
        {
            System.out.println("Машина " + numberCar);
            for (int y = 1; y <= countContainer && y <= 12; y++)
            {
                System.out.println("\tКонтейнер " + numberContainer);
                for (int z = 1; z <= countBox && z <= 27; z++)
                {
                    System.out.println("\t\tЯщик" + numberBox);
                    numberBox++;
                    if (numberBox > countBox) return;
                }
                numberContainer++;
            }
            numberCar++;
        }
    }
}
