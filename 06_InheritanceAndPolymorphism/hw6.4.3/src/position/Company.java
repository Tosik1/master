package position;

import java.util.ArrayList;
import java.util.List;

public class Company{
    String company;
    public static double incomeCompany;
    ArrayList<Employee> salaryCompany = new ArrayList<>();

    public Company(String company, double incomeCompany)
    {
        this.company = company;
        this.incomeCompany = incomeCompany;
    }

    public double getIncomeCompany() {
        return incomeCompany;
    }

    public void setIncomeCompany(double incomeCompany) {
        this.incomeCompany = incomeCompany;
    }

    public void hire(String position)
    {
        if (position == "Operator") {
            salaryCompany.add(new Operator(36000));
        }
        if (position == "Manager") {
            salaryCompany.add(new Manager(40000));
        }
        if (position == "TopManager") {
            salaryCompany.add(new TopManager(42000));
        }
    }

    public void hireAll(String position, int countStaff)
    {
        for (int a = 0; a < countStaff; a++)
        {
            if (position == "Operator") {
                salaryCompany.add(new Operator(35000));
            }
            if (position == "Manager") {
                salaryCompany.add(new Manager(40000));
            }
            if (position == "TopManager") {
                salaryCompany.add(new TopManager(42000));
            }
        }
    }

    public void fire(int countStaff)
    {
        for (int a = 0; a < countStaff; a++)
        {
            int c = (int) Math.round(Math.random() * (salaryCompany.size()));
            salaryCompany.remove(c);
        }
    }

    public List<Employee> getTopSalaryStaff(int count)
    {
        List<Employee> topSalary = new ArrayList<>();
        topSalary.addAll(salaryCompany);
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
        lowestSalary.addAll(salaryCompany);
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
