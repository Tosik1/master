package position;

import java.util.ArrayList;
import java.util.List;

public class Company{

    public double incomeCompany;
    public ArrayList<Employee> employeeList;

    public Company(double incomeCompany)
    {
         new ArrayList<>();
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
        employeeList.add(type);
        type.setCompany(this);
    }

    public void hireAll(List<Employee> type, Company company, int countStaff)
    {
        for (int a = 0; a < countStaff; a++)
        {
            employeeList.add(type.get(a));
            type.get(a).setCompany(this);
        }
    }

    public void fire(int countStaff, Company company)
    {
        for (int a = 0; a < countStaff; a++)
        {
            int c = (int) Math.round(Math.random() * (company.employeeList.size()));
            employeeList.remove(c);
        }
    }

    public List<Employee> getTopSalaryStaff(int count, Company company)
    {
        List<Employee> topSalary = new ArrayList<>();
        topSalary.addAll(company.employeeList);
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
        lowestSalary.addAll(company.employeeList);
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
