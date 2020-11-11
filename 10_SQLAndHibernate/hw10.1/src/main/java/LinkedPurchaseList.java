import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "linked_purchase_list")
public class LinkedPurchaseList {
    @EmbeddedId
    private Key id;

    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public LinkedPurchaseList(){}

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

    public Key getId() {
        return id;
    }

    public void setId(Key id) {
        this.id = id;
    }

    @Embeddable
    public static class Key implements Serializable {
        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "student_id", insertable = false, updatable = false)
        private Student student;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "course_id", insertable = false, updatable = false)
        private Course course;

        public Key(){}

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return student.equals(key.student) &&
                    course.equals(key.course);
        }

        @Override
        public int hashCode() {
            return Objects.hash(student, course);
        }

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
    }
}
