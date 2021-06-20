package main.core.repository;

import main.core.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {

    @Query(value = "SELECT COUNT(*) FROM website.post where mod_status = 1 and is_active = 1", nativeQuery = true)
    int countPosts();

    @Query("FROM Post p where mod_status = 1 and is_active = 1 ORDER BY p.postComments.size")
    Page<Post> findMostCommentedPosts(Pageable pageable);

    @Query("FROM Post p where mod_status = 1 and is_active = 1 ORDER BY p.time DESC")
    Page<Post> findMostRecentPosts(Pageable pageable);

    @Query(value = "SELECT *, " +
            "(SELECT SUM(value) FROM website.post_votes where value < 0 and post.id = post_id) as likeCount " +
            "FROM post where mod_status = 1 and is_active = 1 " +
            "ORDER BY likeCount DESC", nativeQuery = true)
    Page<Post> findBestPost(Pageable pageable);

    @Query("FROM Post p where mod_status = 1 and is_active = 1 ORDER BY p.time")
    Page<Post> findMostEarlyPosts(Pageable pageable);
}
