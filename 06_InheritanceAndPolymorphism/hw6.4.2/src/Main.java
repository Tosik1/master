public class Main
{
    public static void main(String[] args)
    {
        System.out.println(Company.getIncomeCompany());
        Company.hireAll("Manager", 80);
        Company.hireAll("TopManager", 10);
        Company.hireAll("Operator", 180);

        System.out.println(Manager.getSalary());

        System.out.println(Company.getSize());
        System.out.println(Company.getTopSalaryStaff(10));
        System.out.println("____________________");
        System.out.println(Company.getLowestSalaryStaff(30));
        Company.fire(135);
        System.out.println(Company.getSize());
        System.out.println("new table");
        System.out.println(Company.getTopSalaryStaff(10));
        System.out.println("____________________");
        System.out.println(Company.getLowestSalaryStaff(30));
    }
}
