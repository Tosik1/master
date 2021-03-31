import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static java.lang.System.out;

public class RedisTest {

    // Запуск докер-контейнера:
    // docker run --rm --name skill-redis -p 127.0.0.1:6379:6379/tcp -d redis

    // Также мы добавим задержку между посещениями
    private static final int SLEEP = 1000; // 1 миллисекунда

    private static final int COUNT_USERS = 20;

    private static final SimpleDateFormat DF = new SimpleDateFormat("HH:mm:ss");

    private static void log(String UsersOnline) {
        String log = String.format(DF.format(new Date()) + " на главной: " + UsersOnline);
        out.println(log);
    }

    private static void log1(String UsersOnline) {
        out.println("Залез вне очереди!");
        String log = String.format(DF.format(new Date()) + " на главной: " + UsersOnline);
        out.println(log);
    }

    public static void main(String[] args) throws InterruptedException {

        RedisStorage redis = new RedisStorage();
        redis.init(redis, COUNT_USERS);

        for(;;) {
            double randT1 = Math.random() * COUNT_USERS;
            int randomT1 = (int) Math.round(randT1);

            double randU1 = Math.random() * COUNT_USERS;
            int randomU1 = (int) Math.round(randU1);

            for (int i = 1; i <= COUNT_USERS; i++) {
                if (i == randomT1){
                    String usersOnline = redis.randomSystem(randomU1);
                    log1(usersOnline);
                    redis.randomSystemRemove(randomU1);
                    Thread.sleep(SLEEP);
                }
                else {
                    String usersOnline = redis.calculateUsersNumber();
                    log(usersOnline);
                    redis.deleteOldEntries();
                    Thread.sleep(SLEEP);
                }
            }
        }
    }
}
