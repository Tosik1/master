import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class PurchaseList {
    @OneToOne(cascade = CascadeType.ALL)
    private Student student;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Course> course;

    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Course> getCourse() {
        return course;
    }

    public void setCourse(List<Course> course) {
        this.course = course;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

}
