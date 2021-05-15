import java.sql.*;

public class DBConnection {

    private static Connection connection;

    private static String dbName = "learn";
    private static String dbUser = "root";
    private static String dbPass = "Qazwsx12344321";
    private static final int MAX_VALUE_PACKAGE = 50_000;

    private static StringBuilder insertQuery = new StringBuilder();
    private static int countVoters = 0;
    private long start = System.currentTimeMillis();

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + dbName +
                        "?user=" + dbUser + "&password=" + dbPass + "&serverTimezone=UTC");
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                    "id INT NOT NULL AUTO_INCREMENT, " +
                    "name TINYTEXT NOT NULL, " +
                    "birthDate DATE NOT NULL, " +
                    "`count` INT NOT NULL, " +
                    "PRIMARY KEY(id))");
//                    "UNIQUE KEY name_date(id))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void executeMultiInsert() throws SQLException {
        String sql = "INSERT INTO voter_count(name, birthDate, `count`) VALUES" + insertQuery.toString();
//                "ON DUPLICATE KEY UPDATE `count`=`count` + 1";
        DBConnection.getConnection().createStatement().execute(sql);
    }

    public static void countVoter(String name, String birthDay) throws SQLException {
//        insertQuery.append((insertQuery.length() == 0 ? "" : ",") + "('" + name + "', '" + birthDay + "', 1)");
        insertQuery.append(insertQuery.length() == 0 ? "" : ",").append(String.format("('%s', '%s', 1)", name, birthDay));
        countVoters++;
        if (countVoters % MAX_VALUE_PACKAGE == 0) {
            System.out.println("Количество записей отправленных в бд : " + countVoters);

            long start = System.currentTimeMillis();
            executeMultiInsert();
            System.out.println("Время, потраченное на запись пакета в бд : " + (System.currentTimeMillis() - start) + "мс");

            insertQuery = new StringBuilder();
        }

    }

    public static void printVoterCounts() throws SQLException {
        if (insertQuery.length() > 0){
            System.out.println("Количество записей отправленных в бд : " + countVoters);

            long start = System.currentTimeMillis();
            executeMultiInsert();
            System.out.println("Время, потраченное на запись пакета в бд : " + (System.currentTimeMillis() - start) + "мс");
        }
        String sql1 = "SELECT `name`, `birthDate`, COUNT(`name`) AS `count` FROM `voter_count` GROUP BY `name` HAVING `count` > 1";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql1);
        while (rs.next()) {
            System.out.println("\t" + rs.getString("name") + " (" +
                rs.getString("birthDate") + ") - " + rs.getInt("count"));
        }
    }
}
