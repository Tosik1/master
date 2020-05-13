public class Cat
{

    //homework done
    

    private double originWeight;
    private double weight;

    private double food = 0.0;

    public static int count = 0;

    public static final double MIN_WEIGHT = 1000.0;
    public static final double MAX_WEIGHT = 9000.0;
    public static final int EYES = 2;


    public ColorCat color;



    public Cat()
    {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        count++;
    }

    public Cat(double getWeight)
    {
        this();
        this.weight = getWeight;
    }

    public Cat(Cat other) {
        this.color = other.color;
        this.weight = other.weight;
    }

    public void setColorCat(ColorCat type)
    {
        this.color = type;
    }

    public ColorCat getColor()
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
        if (weight < MIN_WEIGHT) count--;
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
        if (weight > MAX_WEIGHT) count--;
    }

    public void drink(Double amount)
    {
        if (this.deadCat() == "dead")
        {
            System.out.println("she dead");
            return;
        }
        weight = weight + amount;
        if (weight > MAX_WEIGHT) count--;
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
        if(weight < MIN_WEIGHT) {
            return "Dead";
        }
        else if(weight > MAX_WEIGHT) {
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
        if (weight < MIN_WEIGHT) count--;
    }

    public static int getCount()
    {
        return count;
    }


}