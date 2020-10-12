import com.sun.source.tree.Tree;
import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class Loader {
    private static File file = new File("C:\\Users\\Kita\\Downloads\\movementList.csv");

    public static void main(String[] args) {

        double sumCom = 0.0;
        double sumCon = 0.0;

        ArrayList<Operation> op = new ArrayList<>();
        HashMap<String, Double> org = new HashMap<>();
        ArrayList<String> lines = new ArrayList<String>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

        try {
            lines = (ArrayList<String>) Files.readAllLines(Paths.get(file.toString()));
            lines.remove(0);

            for (String line : lines) {
                String[] fragments = line.split(",", 8);
                op.add(new Operation(fragments[1], fragments[2], formatter.parse(fragments[3]), fragments[4], fragments[5], fragments[6], fragments[7]));
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        for (Operation operation : op) {
            sumCom += operation.getComing();
            sumCon += operation.getConsumption();
            if (org.containsKey(operation.getOrganization())){
                double n = org.get(operation.getOrganization()) + operation.getConsumption();
                org.put(operation.getOrganization(), n);
            }else{
                org.put(operation.getOrganization(), operation.getConsumption());
            }
        }

        System.out.println("Сумма расходов: " + sumCon);
        System.out.println("Сумма доходов: " + sumCom);
        System.out.println("Сумма расходов по организациям: ");
        for (Map.Entry entry : org.entrySet()){
            System.out.println(entry);
        }
    }
}