public class Manager extends Company implements Employee
{
    public static double salary;

    public Manager(double )
    {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public double getMonthSalary() {
        double fixedSalaryManager = 40000.0;
        double salesForCompany = Math.random() * 25000.0 + 115000.0;
        this.salary = fixedSalaryManager + (salesForCompany * 0.05);
        return fixedSalaryManager + (salesForCompany * 0.05);
    }

}
