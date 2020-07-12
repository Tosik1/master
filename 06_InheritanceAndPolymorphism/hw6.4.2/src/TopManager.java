public class TopManager extends Company implements Employee
{
    @Override
    public double getMonthSalary() {
        double fixedSalaryTopManager = 40000.0;
        if (incomeCompany >= 10000000)
        {
            return fixedSalaryTopManager * 2.5;
        }
        return fixedSalaryTopManager;
    }
}
