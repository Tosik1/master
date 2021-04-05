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

    public RScoredSortedSet<String> getOnlineUsers() {
        return onlineUsers;
    }


    void init(int COUNT_USERS) {
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
    }

    String[] generRandUser(int COUNT_USERS){
        double randU1 = Math.random() * COUNT_USERS;
        int randomU1 = (int) Math.round(randU1);
        String[] rand = new String[1];
        rand[0] = String.valueOf(randomU1);
        return rand;
    }

    void generUsers(RedisStorage redis, int COUNT_USERS){
        for(int count = 0; count < COUNT_USERS; count++) {
            redis.addUserOnTurn(count);
        }
    }

    void shutdown() {
        redisson.shutdown();
    }

    void addUserOnTurn(int user_id)
    {
        onlineUsers.add(count, String.valueOf(user_id));
        count++;
    }

    void deleteOldEntries()
    {
        addUserOnTurn(Integer.parseInt(onlineUsers.first()));
        onlineUsers.remove(onlineUsers.firstScore());
    }

    String randomSystem(int i){
        return onlineUsers.valueRange(i, i).toString();
    }

    void randomSystemRemove(String i){
        onlineUsers.remove(i);
    }

    String showFirstVal()
    {
        String first = onlineUsers.first();
        randomSystemRemove(first);
        return first;
    }
}
