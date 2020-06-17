package orderAccount;

public class BankAccount {
    protected double balance = 0.0;
    public double withdrawMoney(double widthdraw){
        if (widthdraw > balance) {
            System.out.println("Недостаточно денег на счету.");
        }
        else {
            balance = balance - widthdraw;
        }
        return balance;

    }

    public double depositMoney ( double deposit){
        balance = balance + deposit;
        return balance;
    }

    public double accountBalance () {
        return balance;
    }

    public boolean send(BankAccount receiver, double amount){
        if (this.accountBalance() < amount) {
            System.out.println("Недостаточно денег на счету");
            return false;
        }
        else{
            this.withdrawMoney(amount);
            receiver.depositMoney(amount);
            System.out.println("Операция прошла успешно");
            return true;
        }
    }
}
