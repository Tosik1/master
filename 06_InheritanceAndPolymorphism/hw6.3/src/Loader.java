import client.Client;
import client.Entity;
import client.Individual;
import client.IndividualEntrepreneur;

public class Loader
{
    public static void main(String argh[])
    {
        Individual ind = new Individual(100);
        ind.depositMoney(100);
        System.out.println("Количество денег на счете физического лица после внесения 100: " + ind.accountBalance());
        ind.withdrawMoney(10);
        System.out.println("Количество денег на счете физического лица после снятия 10: " + ind.accountBalance());

        Entity ent = new Entity(1000);
        ent.depositMoney(1000);
        System.out.println("Количество денег на счете юридического лица лица после внесения 1000: " + ent.accountBalance());
        ent.withdrawMoney(100);
        System.out.println("Количество денег на счете юридического лица после снятия 100: " + ent.accountBalance());

        IndividualEntrepreneur indEnt = new IndividualEntrepreneur(10000);
        indEnt.depositMoney(10000);
        System.out.println("Количество денег на счете индивидуального предпринимателя после внесения 10000: " + indEnt.accountBalance());
        indEnt.withdrawMoney(1000);
        System.out.println("Количество денег на счете индивидуального предпринимателя после снятия 1000: " + indEnt.accountBalance());
    }
}
