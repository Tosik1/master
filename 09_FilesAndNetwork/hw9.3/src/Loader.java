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
        HashSet<String> org = new HashSet<>();
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
            org.add(operation.getOrganization());
        }

        ArrayList<String> org1 = new ArrayList<>(org);
        double[] masCon = new double[org1.size()];
        for (int i = 0; i < org1.size(); i++) {
            double sum = 0.0;
            for (int a = 0; a < op.size(); a++){
                if (org1.get(i).equals(op.get(a).getOrganization())){
                    sum += op.get(a).getConsumption();
                }
            }
            masCon[i] = sum;
        }


        System.out.println("Сумма расходов: " + sumCon);
        System.out.println("Сумма доходов: " + sumCom);
        System.out.println("Сумма расходов по организациям: ");
        for (int i = 0; i < org1.size(); i++) {
            System.out.println("\t" + masCon[i] + "\t\t\t" + org1.get(i));
        }

    }
}
