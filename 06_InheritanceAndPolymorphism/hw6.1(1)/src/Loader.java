import orderAccount.bankAccount;
import orderAccount.cardAccount;
import orderAccount.depositAccount;

public class Loader {

    public static void main(String[] args) {
        bankAccount bank = new bankAccount();
        bank.depositMoney(1000);
        System.out.println("Остаток " + bank.accountBalance());
        bankAccount bank1 = new bankAccount();
        bank.send(bank1, 6100);
        System.out.println(bank.accountBalance());
        System.out.println(bank1.accountBalance());
        depositAccount deposit = new depositAccount();
        deposit.withdrawMoney();
        cardAccount card = new cardAccount();
        card.depositMoney(1000);
        System.out.println(card.accountBalance());
    }
}
