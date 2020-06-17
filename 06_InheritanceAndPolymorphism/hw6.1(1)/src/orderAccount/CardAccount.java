package orderAccount;

public class CardAccount extends BankAccount {

    public double depositMoney(double deposit){
        super.depositMoney(deposit*0.99);
        return balance;
    }
}
