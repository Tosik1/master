package main.repository;

import main.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Modifying
    @Transactional
    @Query(value = "Insert users(email, password, name, is_moderator, reg_time) values( ?1, ?2, ?3, 0, sysdate())", nativeQuery = true)
    void insertUser(String eMail, String password, String name, int isModerator);

    @Query(value = "SELECT u.* from users as u where u.email = ?1", nativeQuery = true)
    Optional<User> findUserByEmail(String email);
}
