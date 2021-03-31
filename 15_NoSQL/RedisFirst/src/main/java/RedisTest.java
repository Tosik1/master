import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static java.lang.System.out;

public class RedisTest {

    // Запуск докер-контейнера:
    // docker run --rm --name skill-redis -p 127.0.0.1:6379:6379/tcp -d redis

    // Также мы добавим задержку между посещениями
    private static final int SLEEP = 1000; // 1 миллисекунда

    private static final SimpleDateFormat DF = new SimpleDateFormat("HH:mm:ss");

    private static void log(String UsersOnline) {
        String log = String.format(DF.format(new Date()) + " на главной: " + UsersOnline);
        out.println(log);
    }

    public static void main(String[] args) throws InterruptedException {

        RedisStorage redis = new RedisStorage();
        redis.init();
        for(int count = 1; count <= 20; count++) {
            redis.logPageVisit(count);
        }
        for(;;) {
            String usersOnline = redis.calculateUsersNumber();
            log(usersOnline);
            redis.deleteOldEntries();
            Thread.sleep(SLEEP);
        }
    }
}
