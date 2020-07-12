import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class Company
{

    public  double incomeCompany = 10000000.0;

    public  List<Employee> salaryCompany = new ArrayList<>();

    public  double getIncomeCompany() {
        return incomeCompany;
    }

    public void setIncomeCompany(double incomeCompany) {
        this.incomeCompany = incomeCompany;
    }

    public  void hire(String position)
    {
        if (position == "Operator") {
            salaryCompany.add(new Operator());
        }
        if (position == "Manager") {
            salaryCompany.add(new Manager(Manager.salary));
        }
        if (position == "TopManager") {
            salaryCompany.add(new TopManager());
        }
    }

    public  void hireAll(String position, int countStaff)
    {
        for (int a = 0; a < countStaff; a++)
        {
            if (position == "Operator") {
                salaryCompany.add(new Operator());
            }
            if (position == "Manager") {
                salaryCompany.add(new Manager(Manager.getSalary()));
            }
            if (position == "TopManager") {
                salaryCompany.add(new TopManager());
            }
        }
    }

    public  void fire(int countStaff)
    {
        for (int a = 0; a < countStaff; a++)
        {
            int c = (int) Math.round(Math.random() * (salaryCompany.size()));
            salaryCompany.remove(c);
        }
    }

    public  List<Employee> getTopSalaryStaff(int count)
    {
        return salaryCompany.subList(0, count);
    }

    public  List<Employee> getLowestSalaryStaff(int count)
    {
        List<Employee> lowestSalary = new ArrayList<>();
        lowestSalary.addAll(salaryCompany);
        lowestSalary.sort((o1, o2) -> {
            if (o1.getMonthSalary() > o2.getMonthSalary())
                return -1;      
            if (o1.getMonthSalary() < o2.getMonthSalary())
                return 1;
            return 0;
        });
        return lowestSalary.subList(0, count);
    }

    public  int getSize(){
        return salaryCompany.size();
    }
}
