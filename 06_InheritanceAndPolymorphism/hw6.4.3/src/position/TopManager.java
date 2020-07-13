package position;

public class TopManager implements Employee {

    Company company;
    public double fixedSalaryTopManager;

    public TopManager(double fixedSalary, Company company)
    {
        if (company.getIncomeCompany() >= 10000000)
        {
            fixedSalaryTopManager = 2.5 * fixedSalary;
        }
        else
        {
            fixedSalaryTopManager = fixedSalary;
        }
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public double getMonthSalary() {
        return fixedSalaryTopManager;
    }
}
