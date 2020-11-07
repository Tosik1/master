import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Subscriptions")
public class Subscription {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student subscriptionStudent;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course subscriptionCourse;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public Student getSubscriptionStudent() {
        return subscriptionStudent;
    }

    public void setSubscriptionStudent(Student subscriptionStudent) {
        this.subscriptionStudent = subscriptionStudent;
    }

    public Course getSubscriptionCourse() {
        return subscriptionCourse;
    }

    public void setSubscriptionCourse(Course subscriptionCourse) {
        this.subscriptionCourse = subscriptionCourse;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

}
