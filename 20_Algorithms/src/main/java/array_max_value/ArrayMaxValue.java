package array_max_value;

public class ArrayMaxValue
{
    public static void main(String[] args) {
        int values[] = new int[3];
        values[0] = -1;
        values[1] = -3;
        values[2] = Integer.MAX_VALUE;
        System.out.println(getMaxValue(values));
    }

    public static int getMaxValue(int[] values) {
        if (values.length == 0){
            throw new NullPointerException("Пустой массив");
        }else {
            int maxValue = Integer.MIN_VALUE;
            for (int value : values) {
                if (value > maxValue) {
                    maxValue = value;
                }
            }
            return maxValue;
        }
    }
}