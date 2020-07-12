package position;

public class Operator implements Employee {

    double salary;

    @Override
    public double getMonthSalary() {
        return salary;
    }

    public Operator(double salary)
    {
        this.salary = salary;
    }
}
