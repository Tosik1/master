import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static java.lang.System.out;

public class RedisTest {

    // Запуск докер-контейнера:
    // docker run --rm --name skill-redis -p 127.0.0.1:6379:6379/tcp -d redis

    // Также мы добавим задержку между посещениями
    private static final int SLEEP = 1000;

    private static final int COUNT_USERS = 6;

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
        redis.init(COUNT_USERS);

        for(;;) {
                if (redis.getOnlineUsers().isEmpty()){
                    redis.generUsers(redis, COUNT_USERS);
                }
                String[] randUser = redis.generRandUser(COUNT_USERS);
                for (String rand : randUser) {
                    out.println(" > Пользователь " + rand + " оплатил платную услугу");
                    out.println(" - На главной странице показываем " + rand);
                    redis.randomSystemRemove(rand);
                    Thread.sleep(SLEEP);
                }
                for (int i = 0; i < COUNT_USERS - 1; i++) {
                    String userName = redis.showFirstVal();
                    out.println(" - На главной странице показываем " + userName);
                    Thread.sleep(SLEEP);
                }
//                {
//                    String usersOnline = redis.randomSystem(randomU1);
//                    log1(usersOnline);
//                    redis.randomSystemRemove(redis, randomU1);
//                    Thread.sleep(SLEEP);
//                }
//                else {
//                    String usersOnline = redis.showFirstVal();
//                    log(usersOnline);
//                    redis.deleteOldEntries();
//                    Thread.sleep(SLEEP);
//                }
        }
    }
}
