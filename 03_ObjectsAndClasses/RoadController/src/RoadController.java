import core.*;
import core.Camera;

import java.util.Scanner;

public class RoadController
{
    //Переменная с плавающей точкой
    private static double passengerCarMaxWeight = 3500.0; // kg
    //Переменная целочисленная
    private static int passengerCarMaxHeight = 2000; // mm
    //Переменная целочисленная
    private static int controllerMaxHeight = 4000; // mm
    //Переменная целочисленная

    //Переменная целочисленная
    private static int passengerCarPrice = 100; // RUB
    //Переменная целочисленная
    private static int cargoCarPrice = 250; // RUB
    //Переменная целочисленная
    private static int vehicleAdditionalPrice = 200; // RUB

    public static void main(String[] args)
    {
        System.out.println("Сколько автомобилей сгенерировать?");

        Scanner scanner = new Scanner(System.in);
        //Переменная целочисленная
        int carsCount = scanner.nextInt();

        //Переменная целочисленная
        for(int i = 0; i < carsCount; i++)
        {
            Car car = Camera.getNextCar();
            System.out.println(car);

            //Пропускаем автомобили спецтранспорта бесплатно
            if (car.isSpecial) {
                openWay();
                continue;
            }

            //Проверяем высоту и массу автомобиля, вычисляем стоимость проезда
            //Переменная целочисленная
            int price = calculatePrice(car);
            if(price == -1) {
                continue;
            }

            System.out.println("Общая сумма к оплате: " + price + " руб.");
        }
    }

    /**
     * Расчёт стоимости проезда исходя из массы и высоты
     */
    private static int calculatePrice(Car car)
    {
        //Переменная целочисленная
        int carHeight = car.height;
        //Переменная целочисленная
        int price = 0;
        if (carHeight > controllerMaxHeight)
        {
            blockWay("высота вашего ТС превышает высоту пропускного пункта!");
            return -1;
        }
        else if (carHeight > passengerCarMaxHeight)
        {
            //Переменная с плавающей точкой
            double weight = car.weight;
            //Грузовой автомобиль
            if (weight > passengerCarMaxWeight)
            {
                price = passengerCarPrice;
                if (car.hasVehicle) {
                    price = price + vehicleAdditionalPrice;
                }
            }
            //Легковой автомобиль
            else {
                price = cargoCarPrice;
            }
        }
        else {
            price = passengerCarPrice;
        }
        return price;
    }

    /**
     * Открытие шлагбаума
     */
    private static void openWay()
    {
        System.out.println("Шлагбаум открывается... Счастливого пути!");
    }

    /**
     * Сообщение о невозможности проезда
     */
    private static void blockWay(String reason)
    {
        System.out.println("Проезд невозможен: " + reason);
    }
}