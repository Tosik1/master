package position;

import java.util.ArrayList;
import java.util.List;

public class Company{

    public double incomeCompany;
    public ArrayList<Employee> salaryCompany = new ArrayList<>();

    public Company(double incomeCompany)
    {
        this.incomeCompany = incomeCompany;
    }

    public double getIncomeCompany() {
        return incomeCompany;
    }

    public void setIncomeCompany(double incomeCompany) {
        this.incomeCompany = incomeCompany;
    }

    public void hire(Employee type, Company company)
    {
        company.salaryCompany.add(type);
    }

    public void hireAll(List<Employee> type, Company company, int countStaff)
    {
        for (int a = 0; a < countStaff; a++)
        {
            company.salaryCompany.add(type.get(a));
        }
    }

    public void fire(int countStaff, Company company)
    {
        for (int a = 0; a < countStaff; a++)
        {
            int c = (int) Math.round(Math.random() * (company.salaryCompany.size()));
            company.salaryCompany.remove(c);
        }
    }

    public List<Employee> getTopSalaryStaff(int count, Company company)
    {
        List<Employee> topSalary = new ArrayList<>();
        topSalary.addAll(company.salaryCompany);
        topSalary.sort((o1, o2) -> {
            if (o1.getMonthSalary() > o2.getMonthSalary())
                return -1;
            if (o1.getMonthSalary() < o2.getMonthSalary())
                return 1;
            return 0;
        });
        return topSalary.subList(0, count);
    }

    public List<Employee> getLowestSalaryStaff(int count, Company company)
    {
        List<Employee> lowestSalary = new ArrayList<>();
        lowestSalary.addAll(company.salaryCompany);
        lowestSalary.sort((o1, o2) -> {
            if (o1.getMonthSalary() < o2.getMonthSalary())
                return -1;
            if (o1.getMonthSalary() > o2.getMonthSalary())
                return 1;
            return 0;
        });
        return lowestSalary.subList(0, count);
    }
}
