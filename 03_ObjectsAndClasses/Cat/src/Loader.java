import java.sql.SQLOutput;
import java.util.Scanner;

public class Loader
{
    private String getKitten;
    public static void main(String[] args)
    {
//homework done
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        Cat cat3 = new Cat();
        Cat cat4 = new Cat();
        Cat cat5 = new Cat();


        System.out.println("Cat1 weight: " + cat1.getWeight());
        System.out.println("Cat2 weight: " + cat2.getWeight());
        System.out.println("Cat3 weight: " + cat3.getWeight());
        System.out.println("Cat4 weight: " + cat4.getWeight());
        System.out.println("Cat5 weight: " + cat5.getWeight() + "\n");

        cat1.drink(5.0);
        System.out.println("Cat1 weight after drink: " + cat1.getWeight());

        cat2.feed(200.0);
        System.out.println("Cat2 weight after eat: " + cat2.getWeight());
        System.out.println();

        System.out.println("Status cat3: " + cat3.getStatus());
        do {
            cat3.feed(5.0);
        } while (cat3.getStatus() != "Exploded");
        System.out.println("After feed cat3 so much food, her status: " + cat3.getStatus());
        System.out.println();

        //мог сделать через цикл do - while, решил попробовать через for
        System.out.println("Status cat4: " + cat4.getStatus());
        for ( ; cat4.getStatus() != "Dead"; cat4.meow())
        {}
        System.out.println("Status cat4 after meowMeowMeow (x9999): " + cat4.getStatus());
        System.out.println();

        System.out.println("I give cat5: " + cat5.food() + "g food");
        cat5.feed(150.0);
        cat5.pee();
        cat5.pee();
        cat5.pee();
        System.out.println("After feed cat " + cat5.food() + "g food and do 3 pee, her weight: " + cat5.getWeight());
        System.out.println();

        System.out.println("Count cat: " + Cat.getCount());
        System.out.println();

        System.out.println("weight deadly cat4: " + cat4.getWeight());
        cat4.feed(150.0);
        System.out.println("weight deadly cat after feed4: " + cat4.getWeight());
        System.out.println();

        Cat cat6 = new Cat(2000.0);
        System.out.println("Weight cat6: " + cat6.getWeight());
        System.out.println();

        System.out.println("weight kitten: " + getKitten().getWeight());
        System.out.println();

        Cat cat7 = new Cat(4000.0);
        cat7.setColorCat(ColorCat.BLUE);
        Cat cat8 = new Cat(cat7);
        System.out.println("cat9 weight: " + cat8.getWeight() + " color: " + cat8.getColor());
    }

    private static Cat getKitten()
    {
        Cat kitten = new Cat(1100.0);
        return kitten;
    }


}