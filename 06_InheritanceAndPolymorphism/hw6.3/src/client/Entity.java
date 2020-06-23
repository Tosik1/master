package client;

public class Entity  extends Client
{
    public double withdrawMoney(double withdraw)
    {
        super.withdrawMoney(withdraw * 1.01);
        return balance;
    }
}
