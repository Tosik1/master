import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.Date;

import static java.lang.System.out;

public class RedisStorage {

    // Объект для работы с Redis
    private RedissonClient redisson;

    // Объект для работы с ключами
    private RKeys rKeys;

    // ЭТО SCORE
    private int count = 0;

    // Объект для работы с Sorted Set'ом
    private RScoredSortedSet<String> onlineUsers;

    private final static String KEY = "ONLINE_USERS";

    public void listKeys() {
        Iterable<String> keys = rKeys.getKeys();
        for(String key: keys) {
            out.println("KEY: " + key + ", type:" + rKeys.getType(key));
        }
    }

    void init(RedisStorage redis, int COUNT_USERS) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redisson = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            out.println("Не удалось подключиться к Redis");
            out.println(Exc.getMessage());
        }
        rKeys = redisson.getKeys();
        onlineUsers = redisson.getScoredSortedSet(KEY);
        rKeys.delete(KEY);

        for(int count = 1; count <= COUNT_USERS; count++) {
            redis.logPageVisit(count);
        }
    }

    void shutdown() {
        redisson.shutdown();
    }

    void logPageVisit(double user_id)
    {
        onlineUsers.add(count, String.valueOf(user_id));
        count++;
    }

    void deleteOldEntries()
    {
        logPageVisit(Double.parseDouble(onlineUsers.first()));
        onlineUsers.remove(onlineUsers.firstScore());
    }

    String randomSystem(int i){
        return onlineUsers.valueRange(i, i).toString();
    }

    void randomSystemRemove(int i){
        logPageVisit(i);
        onlineUsers.removeRangeByRank(i, i);
    }

    String calculateUsersNumber()
    {
        return onlineUsers.first();
    }
}
