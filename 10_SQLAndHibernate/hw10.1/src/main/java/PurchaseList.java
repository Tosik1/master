import javax.persistence.*;
import java.util.Date;

@Entity
public class PurchaseList {
    @OneToOne(cascade = CascadeType.ALL)
    private Student student;

    @OneToMany(cascade = CascadeType.ALL)
    private Course course;

    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
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
