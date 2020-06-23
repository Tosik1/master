package client;

public class IndividualEntrepreneur extends Client
{
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
