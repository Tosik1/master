import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Cat
{

    //homework done
    

    private double originWeight;
    private double weight;

    private double minWeight;
    private double maxWeight;

    private double food = 0.0;

    public static int count = 0;

    public static final double MIN_WEIGHT = 1000.0;
    public static final double MAX_WEIGHT = 9000.0;
    public static final int EYES = 2;

    private String color;

    public void setCopy()
    {
        weight = weight;
        color = getColor();
    }

    public Cat()
    {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;
        count++;
    }

    public Cat(double getWeight)
    {
        this();
        this.weight = getWeight;
    }

    public Cat(double weight, String color)
    {
        this();
        this.weight = weight;
        this.color = color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public String getColor()
    {
        return color;
    }

    public String deadCat() {
        if (this.getStatus() == "Dead" || this.getStatus() == "Exploded")
            return "dead";
        return "";
    }

    public void meow()
    {
        if (this.deadCat() == "dead")
        {
            System.out.println("she dead");
            return;
        }
        else
        weight = weight - 1;
        if (weight < minWeight) count--;
    }

    public void feed(Double amount)
    {
        if (this.deadCat() == "dead")
        {
            System.out.println("she dead");
            return;
        }
        else
        weight = weight + amount;
        food = food + amount;
        if (weight > maxWeight) count--;
    }

    public void drink(Double amount)
    {
        if (this.deadCat() == "dead")
        {
            System.out.println("she dead");
            return;
        }
        weight = weight + amount;
        if (weight > maxWeight) count--;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Double getWeight()
    {
        return weight;
    }

    public String getStatus()
    {
        if(weight < minWeight) {
            return "Dead";
        }
        else if(weight > maxWeight) {
            return "Exploded";
        }
        else if(weight > originWeight) {
            return "Sleeping";
        }
        else {
            return "Playing";
        }

    }

    public double food() {
        return food;
    }

    public void pee() {
        if (this.deadCat() == "dead")
        {
            System.out.println("she dead");
            return;
        }
        else
        weight = weight - 2;
        System.out.println("cat do pee");
        if (weight < minWeight) count--;
    }

    public static int getCount()
    {
        return count;
    }


}