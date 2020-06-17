package orderAccount;

import java.time.Period;
import java.util.Date;

public class DepositAccount extends BankAccount{
    private long timeDeposit;
    private long timeWithdraw;

    public double withdrawMoney(double withdraw){
        timeWithdraw = System.currentTimeMillis();
        if (timeWithdraw - timeDeposit > 2.628e+9){
            super.withdrawMoney(withdraw);
        }
        else {
            System.out.println("Прошло меньше месяца с последнего взноса");
        }
        return balance;
    }

    public double depositMoney ( double deposit){
        timeDeposit = System.currentTimeMillis();
        balance = balance + deposit;
        return balance;
    }
}
