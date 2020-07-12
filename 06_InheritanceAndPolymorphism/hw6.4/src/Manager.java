public class Manager implements Employee
{

    @Override
    public double getMonthSalary() {
        double salary = (115000 + Math.random() * 25000) * 0.05 + 40000;
        return salary;
    }
}
