import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Company
{
    static ArrayList<Double> workersOperator = new ArrayList<>();
    static ArrayList<Double> workersManager = new ArrayList<>();
    static ArrayList<Double> workersTopManager = new ArrayList<>();
    static ArrayList<Double> workersCompany = new ArrayList<>();

    static double companyIncome = 0;

    static int countHire = 0;

    public void hire(String position, Double salary)
    {
        if (position == "Оператор") {
            Operator operator = new Operator();
            workersOperator.add(operator.getMonthSalary());
            workersCompany.add(operator.getMonthSalary());
        }
        if (position == "Менеджер") {
            Manager manager = new Manager();
            workersManager.add(manager.getMonthSalary());
            workersCompany.add(manager.getMonthSalary());
        }
        if (position == "ТопМенеджер") {
            TopManager topManager = new TopManager();
            workersTopManager.add(topManager.getMonthSalary());
            workersCompany.add(topManager.getMonthSalary());
        }
    }

    public static void hireAll(String position, int a)
    {
        for (int b = 0; b < a; b++)
        {
            if (position == "Оператор") {
                Operator operator = new Operator();
                workersOperator.add(operator.getMonthSalary());
                workersCompany.add(operator.getMonthSalary());
            }
            if (position == "Менеджер") {
                Manager manager = new Manager();
                workersManager.add(manager.getMonthSalary());
                workersCompany.add(manager.getMonthSalary());
            }
            if (position == "ТопМенеджер") {
                TopManager topManager = new TopManager();
                workersTopManager.add(topManager.getMonthSalary());
                workersCompany.add(topManager.getMonthSalary());
            }
        }
    }

    public static void fire(int count)
    {
        for (int a = 0; a < count; a++)
        {
            double b = workersCompany.size() * Math.random();
            int c = (int) Math.round(b);
            workersCompany.remove(c);
        }
    }

    public void getIncome()
    {
        System.out.println("Доход компании = " + companyIncome);
    }

    public static ArrayList<Double> getTopSalaryStaff(int count)
    {
        ArrayList<Double> workersCompany = new ArrayList<>();
        workersCompany.addAll(workersManager);
        workersCompany.addAll(workersTopManager);
        workersCompany.addAll(workersOperator);
        ArrayList<Double> ada = new ArrayList<>();

        for (int a = 0; a < count; a++)
        {
            ada.add(workersCompany.get(a));
        }
        return ada;
    }

    public static ArrayList<Double> getLowestSalaryStaff(int count)
    {
        ArrayList<Double> workersCompany = new ArrayList<>((Comparator<Double>) (o1, o2) -> {
            if (o1 > o2){
                return -1;
            }
            if (o1 < o2){
                return 1;
            }
            return 0;
        });
        workersCompany.addAll(workersManager);
        workersCompany.addAll(workersTopManager);
        workersCompany.addAll(workersOperator);
        ArrayList<Double> ada = new ArrayList<>();

        for (int a = 0; a < count; a++)
        {
            ada.add(workersCompany.get(a));
        }
        return ada;
    }
}
