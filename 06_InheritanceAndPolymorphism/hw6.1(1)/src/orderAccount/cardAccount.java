package orderAccount;

public class cardAccount extends bankAccount {
    double balance = 0;
    public double withdrawMoney(double widthdraw){
        if (widthdraw > balance)
            System.out.println("Недостаточно денег на счету.");
        else
            balance = balance - widthdraw;
        return balance;
    }

    public double depositMoney ( double deposit){
        balance = balance + (deposit * 0.99);
        return balance;
    }

    public double accountBalance () {
        return balance;
    }
}
