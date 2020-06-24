package orderAccount;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class DepositAccount extends BankAccount{

    LocalDate dateWithdraw;
    LocalDate dateValidDeposit;
    LocalDate dateDeposit;

    public double withdrawMoney(double withdraw){
        dateWithdraw = LocalDate.now();
        dateValidDeposit = dateWithdraw.minusDays(30);

        Period period = Period.between(dateValidDeposit, dateDeposit);
        int diff = period.getDays();

        if (diff > 30){
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
