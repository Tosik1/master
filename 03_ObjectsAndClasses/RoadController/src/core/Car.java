package core;

public class Car
{
    //homework done
    //Переменная текст
    public String number;
    //Переменная целочисленная
    public int height;
    //Переменная с плавающей запятой
    public double weight;
    //Переменная булиева
    public boolean hasVehicle;
    //Переменная булиева
    public boolean isSpecial;



    public void setNumber(String number)
    {
        this.number = number;
    }
    public String getNumber()
    {
        return number;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }
    public int getHeight()
    {
        return height;
    }

    public void setWeight(double weight)
    {
        this.weight = weight;
    }
    public double getWeight()
    {
        return weight;
    }

    public void hasVehicle(boolean hasVehicle)
    {
        this.hasVehicle = hasVehicle;
    }
    public boolean hasVehicle()
    {
        return hasVehicle;
    }

    public void isSpecial(boolean isSpecial)
    {
        this.isSpecial = isSpecial;
    }
    public boolean getHasVehicle()
    {
        return hasVehicle;
    }


    //Переменная текст
    public String toString()
    {
        //Переменная текст
        String special = isSpecial ? "СПЕЦТРАНСПОРТ " : "";
        return "\n=========================================\n" +
            special + "Автомобиль с номером " + number +
            ":\n\tВысота: " + height + " мм\n\tМасса: " + weight + " кг";
    }

    Cat cat;
}