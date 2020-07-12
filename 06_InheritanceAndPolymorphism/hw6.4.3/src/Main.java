import position.*;

import java.util.ArrayList;
import java.util.TreeSet;

public class Main
{
    public static void main(String[] args) {

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
