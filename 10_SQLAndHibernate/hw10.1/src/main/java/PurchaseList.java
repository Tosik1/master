import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class PurchaseList {

    @EmbeddedId
    private PurchaseListKey key;

    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    PurchaseList(){}

    public PurchaseListKey getKey() {
        return key;
    }

    public void setKey(PurchaseListKey key) {
        this.key = key;
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

    @Embeddable
    public static class PurchaseListKey implements Serializable {
        @Column(name = "course_name")
        private String courseName;

        @Column(name = "student_name")
        private String studentName;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PurchaseListKey key = (PurchaseListKey) o;
            return courseName.equals(key.courseName) &&
                    studentName.equals(key.studentName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(courseName, studentName);
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }
    }
}
