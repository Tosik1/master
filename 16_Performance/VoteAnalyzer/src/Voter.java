import java.text.SimpleDateFormat;
import java.util.Date;

public class Voter {

    private String name;
    private long birthDay;

    public Voter(String name, long birthDay) {
        this.name = name;
        this.birthDay = birthDay;
    }

    @Override
    public boolean equals(Object obj) {
        Voter voter = (Voter) obj;
        return name.equals(voter.name) && (birthDay == (voter.birthDay));
    }

    @Override
    public int hashCode() {
        long code = name.hashCode() + Long.hashCode(birthDay);
        while (code > Integer.MAX_VALUE) {
            code = code / 10;
        }
        return (int) code;
    }

    public String toString() {
//        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy.MM.dd");
        return name + " (" + new SimpleDateFormat("yyyy.MM.dd").format(new Date(birthDay)) + ")";
    }

    public String getName() {
        return name;
    }

    public long getBirthDay() {
        return birthDay;
    }
}
