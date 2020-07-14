package position;

import java.util.ArrayList;

public class Manager implements Employee {

    public double salary;
    Company company;

    public Manager(double fixedSalary, Company company)
    {
        double salesForCompany = Math.random() * 25000.0 + 115000.0;
        this.salary = fixedSalary + (salesForCompany * 0.05);
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public double getMonthSalary() {
        return salary;
    }

}
