public class Loader
{
    public static void main(String[] args)
    {
        Company.hireAll("Оператор", 180);
        Company.hireAll("Менеджер", 80);
        Company.hireAll("ТопМенеджер", 10);

        System.out.println(Company.getTopSalaryStaff(15));
        System.out.println(Company.getLowestSalaryStaff(30));

        Company.fire(135);

        System.out.println(Company.getTopSalaryStaff(15));
        System.out.println(Company.getLowestSalaryStaff(30));
    }
}
