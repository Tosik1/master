package position;

public class Operator implements Employee {

    double salary;
    Company company;

    public Operator(double salary, Company company)
    {
        this.company = company;
        this.salary = salary;
    }

    @Override
    public double getMonthSalary() {
        return salary;
    }

    public Operator(double salary)
    {
        this.salary = salary;
    }
}
