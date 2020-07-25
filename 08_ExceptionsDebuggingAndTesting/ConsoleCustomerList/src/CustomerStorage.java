import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerStorage
{
    private Pattern patternE = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    private Pattern patternTel = Pattern.compile("\\+[7]\\d{10}");

    private HashMap<String, Customer> storage;

    public CustomerStorage()
    {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) throws PhoneException
    {
        String[] components = data.split("\\s+");
        if (components.length == 4) {
            String name = components[0] + " " + components[1];

            Matcher matcher = patternE.matcher(components[2]);
            Matcher matcher1 = patternTel.matcher(components[3]);

            if (matcher.matches()) {
                if (matcher1.matches()) {
                    storage.put(name, new Customer(name, components[3], components[2]));
                } else {
                    throw new PhoneException("Wrong format tel number. Correct format: +79215637722", components[3]);
                }
            } else {
                throw new IllegalArgumentException("Wrong format email. Correct format: vasily.petrov@gmail.com");
            }
        }
        else {
            throw new IllegalArgumentException("Wrong command! Available command examples: \n add Василий Петров vasily.petrov@gmail.com +79215637722");
        }
    }

    public void listCustomers()
    {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name)
    {
        if (name.split("\\s+").length == 2) {
            storage.remove(name);
        } else {
            throw new IllegalArgumentException("Wrong name. Correct name: Василий Петров");
        }
    }

    public int getCount()
    {
        return storage.size();
    }
}