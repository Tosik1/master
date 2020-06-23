package client;

public abstract class Client
{
    protected double balance;

    public double depositMoney(double deposit)
    {
        balance += deposit;
        return balance;
    }

    public double withdrawMoney(double withdraw)
    {
        balance -= withdraw;
        return balance;
    }

    public double accountBalance()
    {
        return balance;
    }

}
