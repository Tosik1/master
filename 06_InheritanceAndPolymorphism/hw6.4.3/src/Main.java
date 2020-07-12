import position.*;

import java.util.ArrayList;
import java.util.TreeSet;

public class Main
{
    public static void main(String[] args) {

//        Manager manager1 = new Manager();
//        System.out.println(manager1.getMonthSalary());
//        ArrayList<Double> managers = new ArrayList<>();
//        managers.add(manager1.getMonthSalary());
//        managers.add((new Manager()).getMonthSalary());
//
//        for (int a = 0; a < 2; a++)
//        {
//            System.out.println(managers.get(a));
//        }

        Company company = new Company("Apple",100000);
        company.hireAll("Operator", 180);
        company.hireAll("Manager", 80);
        company.hireAll("TopManager", 10);

        for (int a = 0; a < 10; a++)
        {
            System.out.println(company.getTopSalaryStaff(10).get(a).getMonthSalary());
        }

        System.out.println("------------------------------------------");

        for (int a = 0; a < 30; a++)
        {
            System.out.println(company.getLowestSalaryStaff(30).get(a).getMonthSalary());
        }
        System.out.println("------------------------------------------");
        System.out.println("------------------------------------------");
        company.fire(135);

        for (int a = 0; a < 10; a++)
        {
            System.out.println(company.getTopSalaryStaff(10).get(a).getMonthSalary());
        }

        System.out.println("------------------------------------------");

        for (int a = 0; a < 30; a++)
        {
            System.out.println(company.getLowestSalaryStaff(30).get(a).getMonthSalary());
        }

    }
}
