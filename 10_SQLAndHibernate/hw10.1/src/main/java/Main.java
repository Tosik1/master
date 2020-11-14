import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static org.hibernate.id.PersistentIdentifierGenerator.PK;

public class Main {
    public static void main(String[] args) {
        SessionFactory sf = Initial.initialization();
        Session session = Initial.openSession(sf);

        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<PurchaseList> query = builder.createQuery(PurchaseList.class);
        Root<PurchaseList> root = query.from(PurchaseList.class);
        query.select(root);
        List<PurchaseList> purList = session.createQuery(query).getResultList();

        CriteriaQuery<Student> queryStudent = builder.createQuery(Student.class);
        Root<Student> rootStudent = queryStudent.from(Student.class);
        queryStudent.select(rootStudent);
        List<Student> studList = session.createQuery(queryStudent).getResultList();

        CriteriaQuery<Course> queryCourse = builder.createQuery(Course.class);
        Root<Course> rootCourse = queryCourse.from(Course.class);
        queryCourse.select(rootCourse);
        List<Course> courseList = session.createQuery(queryCourse).getResultList();

        Transaction transaction = session.beginTransaction();
        for (PurchaseList pur : purList){
            for (Student stud : studList) {
                if (pur.getKey().getStudentName().equals(stud.getName())) {
                    for (Course course : courseList) {
                        if (pur.getKey().getCourseName().equals(course.getName())) {
                            LinkedPurchaseList lpl = new LinkedPurchaseList();
                            LinkedPurchaseList.Key key = new LinkedPurchaseList.Key();
                            key.setStudent(stud);
                            key.setCourse(course);
                            lpl.setPrice(pur.getPrice());
                            lpl.setSubscriptionDate(pur.getSubscriptionDate());
                            lpl.setId(key);
                            session.save(lpl);
                        }
                    }
                }
            }
        }

        transaction.commit();
        sf.close();
    }
}
