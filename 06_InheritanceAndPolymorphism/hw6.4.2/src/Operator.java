public class Operator extends Company implements Employee
{
    @Override
    public double getMonthSalary() {
        double fixedSalaryOperator = 35000;
        return fixedSalaryOperator;
    }


}
