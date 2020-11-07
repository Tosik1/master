import javax.persistence.*;
import java.util.Date;

@Entity
public class PurchaseList {

    @OneToOne(cascade = CascadeType.ALL)
    private Student student;

    @OneToOne(cascade = CascadeType.ALL)
    private Course course;

    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;
}
