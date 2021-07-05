package main.core.repository;

import main.core.model.Tags;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagsRepository extends CrudRepository<Tags, Integer> {

    @Query(value = "SELECT *, " +
            "(SELECT COUNT(tag2post.tag_id) FROM tag2post where tag_id = tags.id) AS count_posts " +
            "FROM tags " +
            "order by count_posts DESC", nativeQuery = true)
    List<Tags> tags();

    @Query(value = "SELECT t.name as name FROM tags as t left join tag2post as t2p on t.id = t2p.tag_id where t2p.post_id = ?1", nativeQuery = true)
    List<String> findTagsById(int id);
}
