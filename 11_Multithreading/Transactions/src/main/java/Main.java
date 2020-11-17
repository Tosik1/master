import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Account> mapAccount;
        Bank bankTest;
        mapAccount = new HashMap<String, Account>();
        for (int i = 0; i < 20; i++) {
            mapAccount.put(Integer.toString(i), new Account((long) (Math.random() * 1000000 + 50000), Integer.toString(i)));
        }

        bankTest = new Bank(mapAccount);

        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            int finalI = i;
            new Thread(() -> {
                    bankTest.transfer(mapAccount.get(Integer.toString(finalI)).getAccNumber(),
                            mapAccount.get(Integer.toString(finalI + 1)).getAccNumber(),
                            60000, mapAccount);
            }).start();
        }
    }
}
