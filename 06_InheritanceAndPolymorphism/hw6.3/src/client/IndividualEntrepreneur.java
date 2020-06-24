package client;

public class IndividualEntrepreneur extends Client
{
    public IndividualEntrepreneur(double balance)
    {
        super.balance = balance;
    }

    public IndividualEntrepreneur()
    {
        super.balance = 0;
    }

    public double depositMoney(double deposit)
    {

        if (deposit < 1000)
        {
            super.depositMoney(deposit * 0.99);
            return balance;
        }
        else
        {
            super.depositMoney(deposit * 0.995);
            return balance;
        }
    }
}
