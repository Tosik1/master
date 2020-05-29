import java.text.DecimalFormat;

public class loader
{
    public static void main(String args[])
    {
        int count = 0;
        double sum = 0;
        double[] pacientTemp = new double[30];
        double minTemp = 36.2;
        double maxTemp = 36.9;

        for (int i = 0; i < pacientTemp.length; i++)
        {
            pacientTemp[i] = 32 + (8 * Math.random());
            double pacientTemp1 = okr(pacientTemp[i]);
            sum = sum + pacientTemp1;
            System.out.print(pacientTemp1 + " ");
            if (pacientTemp[i] > minTemp && pacientTemp[i] < maxTemp)
            {
                count = count + 1;
            }
        }
        System.out.println();
        System.out.println("Средняя температура пациентов: " + okr(sum / pacientTemp.length));
        System.out.println("Количество здоровых пациентов: " + count);
    }

    public static double okr(double a)
    {
        double x = Math.round( a * 10.0 ) / 10.0;
        return x;
    }
}
