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
        CriteriaQuery<Subscription> query = builder.createQuery(Subscription.class);
        Root<Subscription> root = query.from(Subscription.class);
        query.select(root);
        List<Subscription> subList = session.createQuery(query).getResultList();

        Transaction transaction = session.beginTransaction();
        for (Subscription sub : subList){
            LinkedPurchaseList lpl = new LinkedPurchaseList();
            Key key = new Key();
            key.setCourseId(sub.getId().getCourseId());
            key.setStudentId(sub.getId().getStudentId());
            lpl.setId(key);
            lpl.setCourseId(sub.getId().getCourseId());
            lpl.setStudentId(sub.getId().getStudentId());
            session.save(lpl);
        }

        transaction.commit();
        sf.close();
    }
}
