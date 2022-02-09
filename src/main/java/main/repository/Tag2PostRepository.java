package main.repository;

import main.model.Tag2Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Tag2PostRepository extends CrudRepository<Tag2Post, Integer> {

    @Query(value = "SELECT COUNT(*) FROM tag2post where post_id = ?1 and tag_id = ?2", nativeQuery = true)
    int findCountRelations(int postId, int tagId);

}
