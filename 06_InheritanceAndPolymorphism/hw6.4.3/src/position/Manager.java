package position;

public class Manager extends Company implements Employee {

    public double salary;

    public Manager()
    {
        this.salary = this.getMonthSalary();
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public double getMonthSalary() {
        double fixedSalaryManager = 40000.0;
        double salesForCompany = Math.random() * 25000.0 + 115000.0;
        this.salary = fixedSalaryManager + (salesForCompany * 0.05);
        return fixedSalaryManager + (salesForCompany * 0.05);
    }

}
