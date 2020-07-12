import figures.*;

import java.util.ArrayList;
import java.util.TreeSet;

public class Loader
{
    public static void main(String[] args)
    {
        TreeSet<Square> squares = new TreeSet<>();
        squares.add(new Square(45));
        squares.add(new Square(45));
        squares.add(new Square(45));
        squares.add(new Square(45));
        squares.add(new Square(45));
        squares.add(new Square(45));

        for (Square square : squares)
        {
            System.out.println(square.getWidth());
        }
    }
}
