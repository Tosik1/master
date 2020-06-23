package orderAccount;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class DepositAccount extends BankAccount{

    LocalDate dateWithdraw = LocalDate.now();
    LocalDate dateValidDeposit = dateWithdraw.minusMonths(1);
    LocalDate dateDeposit;

    Period period = Period.between(dateValidDeposit, dateDeposit);
    int diff = period.getMonths();

    public double withdrawMoney(double withdraw){
        if (diff > 1){
            super.withdrawMoney(withdraw);
        }
        else {
            System.out.println("Прошло меньше месяца с последнего взноса");
        }
        return balance;
    }

    public double depositMoney ( double deposit){
        dateDeposit = LocalDate.now();
        balance = balance + deposit;
        return balance;
    }
}
