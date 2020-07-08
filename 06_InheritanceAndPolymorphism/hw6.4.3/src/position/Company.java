package position;

import java.util.ArrayList;
import java.util.List;

public class Company {

    public static double incomeCompany;

    static ArrayList<Employee> salaryCompany = new ArrayList<>();

    public static double getIncomeCompany() {
        return incomeCompany;
    }

    public void setIncomeCompany(double incomeCompany) {
        this.incomeCompany = incomeCompany;
    }

    public static void hire(String position)
    {
        if (position == "Operator") {
            salaryCompany.add(new Operator());
        }
        if (position == "Manager") {
            salaryCompany.add(new Manager());
        }
        if (position == "TopManager") {
            salaryCompany.add(new TopManager());
        }
    }

    public static void hireAll(String position, int countStaff)
    {
        for (int a = 0; a < countStaff; a++)
        {
            if (position == "Operator") {
                salaryCompany.add(new Operator());
            }
            if (position == "Manager") {
                salaryCompany.add(new Manager());
            }
            if (position == "TopManager") {
                salaryCompany.add(new TopManager());
            }
        }
    }

    public static void fire(int countStaff)
    {
        for (int a = 0; a < countStaff; a++)
        {
            int c = (int) Math.round(Math.random() * (salaryCompany.size()));
            salaryCompany.remove(c);
        }
    }

    public static List<Employee> getTopSalaryStaff(int count)
    {
        return salaryCompany.subList(0, count);
    }

    public static List<Employee> getLowestSalaryStaff(int count)
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
