package main.repository;

import main.model.PostComments;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostCommentsRepository extends CrudRepository<PostComments, Integer> {

    @Query(value = "SELECT * FROM post_comments as pc where pc.parent_id = ?1", nativeQuery = true)
    PostComments findByParentId(int parentId);

    @Query(value = "SELECT * FROM post_comments as pc where pc.post_id = ?1", nativeQuery = true)
    List<PostComments> findByPostId(int postId);
}
