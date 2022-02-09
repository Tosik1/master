package main.repository;

import main.model.Tags;
import main.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagsRepository extends CrudRepository<Tags, Integer> {

    @Query(value = "SELECT *, " +
            "(SELECT COUNT(tag2post.tag_id) FROM tag2post where tag_id = tags.id) AS count_posts " +
            "FROM tags " +
            "order by count_posts DESC", nativeQuery = true)
    List<Tags> tags();

    @Query(value = "SELECT t.name as name FROM tags as t left join tag2post as t2p on t.id = t2p.tag_id where t2p.post_id = ?1", nativeQuery = true)
    List<String> findTagsById(int id);

    @Query(value = "SELECT count(t.name) as name FROM tags as t where t.name = ?1", nativeQuery = true)
    int findCountTagsByName(String name);

    @Query(value = "SELECT t.* from tags as t where t.name = ?1", nativeQuery = true)
    Optional<Tags> findTagByName(String name);
}
