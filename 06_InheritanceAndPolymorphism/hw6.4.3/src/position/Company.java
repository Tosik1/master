package position;

import java.util.ArrayList;
import java.util.List;

public class Company{

    public double incomeCompany;
    public ArrayList<Employee> employeeList;

    public Company(double incomeCompany)
    {
         employeeList = new ArrayList<>();
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

    public void hireAll(List<Employee> type)
    {
        for (int a = 0; a < type.size(); a++)
        {
            employeeList.add(type.get(a));
            type.get(a).setCompany(this);
        }
    }

    public void fire(int countStaff)
    {
        for (int a = 0; a < countStaff; a++)
        {
            int c = (int) Math.round(Math.random() * (this.employeeList.size()));
            employeeList.remove(c);
        }
    }

    public List<Employee> getTopSalaryStaff(int count)
    {
        List<Employee> topSalary = new ArrayList<>();
        topSalary.addAll(this.employeeList);
        topSalary.sort((o1, o2) -> {
            if (o1.getMonthSalary() > o2.getMonthSalary())
                return -1;
            if (o1.getMonthSalary() < o2.getMonthSalary())
                return 1;
            return 0;
        });
        return topSalary.subList(0, count);
    }

    public List<Employee> getLowestSalaryStaff(int count)
    {
        List<Employee> lowestSalary = new ArrayList<>();
        lowestSalary.addAll(this.employeeList);
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
