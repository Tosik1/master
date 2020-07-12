package figures;

public class Square extends Rectangle
{
    public Square(double width, double height) {
        super(width, height);
        if (width != height){
            System.out.println("Ширина не равна высоте");
        }
    }

    public Square(double width)
    {
        super(width, width);
    }

    public void setWidth(double width)
    {
        this.width = width;
        height = width;
    }

    public void setHeight(double height)
    {
        this.height = height;
        width = height;
    }
}
