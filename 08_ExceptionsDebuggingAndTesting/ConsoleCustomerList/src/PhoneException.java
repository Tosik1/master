public class PhoneException extends Exception
{
    String number;

    public PhoneException(String massage, String number)
    {
        super(massage);
        this.number = number;
    }

    public String getNumber()
    {
        return number;
    }
}
