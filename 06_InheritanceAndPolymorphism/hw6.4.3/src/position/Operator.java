package position;

public class Operator implements Employee {
    @Override
    public double getMonthSalary() {
        double fixedSalaryOperator = 35000;
        return fixedSalaryOperator;
    }
}
