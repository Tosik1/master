public class TopManager implements Employee
{

    @Override
    public double getMonthSalary() {
        double salary;
        if (Company.companyIncome > 10000000)
        {
            salary = 40000 + 40000 * 1.5;
        }
        else
        {
            salary = 40000;
        }
        return salary;
    }
}
