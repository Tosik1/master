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
}
