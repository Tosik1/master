import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "Qazwsx12344321";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement0 = connection.createStatement();
            ResultSet resultSet0 = statement0.executeQuery("SELECT course_name  FROM PurchaseList group by course_name;");
            while(resultSet0.next()){
                String courseName = resultSet0.getString("course_name");
                int countSub = 0;
                int countMaxMonth = 0;
                int countMinMonth = 0;
                ArrayList<Integer> al = new ArrayList<Integer>();
                for (int i = 1; i < 13; i++) {
                    Statement statement = connection.createStatement();

                    ResultSet resultSet = statement.executeQuery("SELECT course_name, subscription_date  FROM PurchaseList " +
                            "WHERE course_name = '" + courseName +
                            "' AND MONTH(subscription_date) = " + i + ";");
                    while (resultSet.next()) {
                        String columnName = resultSet.getString(2);

                        if (columnName != null) {
                            al.add(i);
                            countSub++;
                            countMaxMonth = i;
                        }
                    }
                    statement.close();
                    resultSet.close();
                }
                System.out.println("Среднее количество покупок курса \"" + courseName + "\" в месяц за 2018: " + (double) countSub / (countMaxMonth - al.get(0)));
            }
            statement0.close();
            resultSet0.close();
            connection.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
