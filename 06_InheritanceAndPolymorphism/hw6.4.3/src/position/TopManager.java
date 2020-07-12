package position;

public class TopManager implements Employee {


    public double fixedSalaryTopManager;
    public double a = Company.incomeCompany;

    public TopManager(double fixedSalary)
    {
        if (a >= 10000000)
        {
            fixedSalaryTopManager = 2.5 * fixedSalary;
        }
        else
        {
            fixedSalaryTopManager = fixedSalary;
        }
    }

    @Override
    public double getMonthSalary() {
        return fixedSalaryTopManager;
    }
}
