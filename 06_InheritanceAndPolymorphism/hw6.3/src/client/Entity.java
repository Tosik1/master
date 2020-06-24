package client;

public class Entity  extends Client
{
    public Entity(double balance)
    {
        super.balance = balance;
    }

    public Entity()
    {
        super.balance = 0;
    }

    public double withdrawMoney(double withdraw)
    {
        super.withdrawMoney(withdraw * 1.01);
        return balance;
    }
}
