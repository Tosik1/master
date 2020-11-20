import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Bank
{
    private static final Object tieLock = new Object();
    private HashMap<String, Account> accounts;
    private final Random random = new Random();

    Bank(HashMap<String, Account> map){
        this.accounts = map;
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException
    {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    public void transfer(String fromAccountNum, String toAccountNum, long amount, HashMap<String, Account> accounts)
    {
        this.accounts = accounts;
        if (amount > accounts.get(fromAccountNum).getMoney()){
            System.out.println("Недостаточно средств для перевода");
        }else {
            try {
                if (isFraud(fromAccountNum, toAccountNum, amount)) {
                    System.out.println("Счета " + fromAccountNum + ", " + toAccountNum + " заблокированы, обнаружено мошенничество.");
                    accounts.remove(fromAccountNum);
                    accounts.remove(toAccountNum);
                }
                else {
                    Account from = accounts.get(fromAccountNum);
                    Account to = accounts.get(toAccountNum);
                    long a = from.getMoney() - amount;
                    long b = to.getMoney() + amount;
                    int toHash = System.identityHashCode(toAccountNum);
                    int fromHash = System.identityHashCode(fromAccountNum);
                    if (fromHash < toHash) {
                        synchronized (from) {
                            synchronized (to) {
                                from.setMoney(a);
                                to.setMoney(b);
                            }
                        }
                    } else if (fromHash > toHash){
                        synchronized (to){
                            synchronized (from){
                                from.setMoney(a);
                                to.setMoney(b);
                            }
                        }
                    } else {
                        synchronized (tieLock){
                            synchronized (from){
                                synchronized (to){
                                    from.setMoney(a);
                                    to.setMoney(b);
                                }
                            }
                        }
                    }
                    System.out.println(amount + " руб. со счета " + fromAccountNum + " переведены на счет " + toAccountNum);
                }
            } catch (Exception e) {
                System.out.println("Возможно счет заблокирован.");
            }
        }
    }

    public long getBalance(String accountNum)
    {
        return accounts.get(accountNum).getMoney();
    }

    public HashMap<String, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(HashMap<String, Account> accounts) {
        this.accounts = accounts;
    }
}
