import position.*;

import java.util.ArrayList;
import java.util.TreeSet;

public class Main
{
    public static void main(String[] args) {

        Company Apple = new Company(10000000);
        Company HP = new Company(100000);

        ArrayList<Employee> newOperator = new ArrayList<>();
        ArrayList<Employee> newTopManager = new ArrayList<>();
        ArrayList<Employee> newManager = new ArrayList<>();

        for (int a = 0; a < 180; a++)
        {
            Employee employee = new Operator(35000, Apple);
            newOperator.add(employee);
        }

        Apple.hireAll(newOperator);

        for (int a = 0; a < 80; a++)
        {
            Employee employee = new Manager(35000, Apple);
            newManager.add(employee);
        }

        Apple.hireAll(newManager);

        for (int a = 0; a < 10; a++)
        {
            Employee employee = new TopManager(35000, Apple);
            newTopManager.add(employee);
        }

        Apple.hireAll(newTopManager);

        for (int a = 0; a < 10; a++)
        {
            System.out.println(Apple.getTopSalaryStaff(10).get(a).getMonthSalary());
        }

        for (int a = 0; a < 30; a++)
        {
            System.out.println(Apple.getLowestSalaryStaff(30).get(a).getMonthSalary());
        }

        System.out.println("--------------------------");

        Apple.fire(135);

        for (int a = 0; a < 10; a++)
        {
            System.out.println(Apple.getTopSalaryStaff(10).get(a).getMonthSalary());
        }

        for (int a = 0; a < 30; a++)
        {
            System.out.println(Apple.getLowestSalaryStaff(30).get(a).getMonthSalary());
        }

    }
}
