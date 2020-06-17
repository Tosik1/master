package orderAccount;

public class depositAccount extends bankAccount{
    public void withdrawMoney(){
        System.out.println("Вы не можете вывести средства, так как не прошел месяц с последнего внесения");
        return;
    }

    public double depositMoney ( double deposit){
        balance = balance + deposit;
        return balance;
    }

    public double accountBalance () {
        return balance;
    }
}
