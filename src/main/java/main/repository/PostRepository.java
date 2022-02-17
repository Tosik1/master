package main.repository;

import liquibase.pro.packaged.Q;
import lombok.var;
import main.model.Post;
import main.model.custom.CountPostPerYear;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {

    @Query(value = "SELECT COUNT(*) FROM post where sysdate() - time > 0 and mod_status = 1 and is_active = 1", nativeQuery = true)
    int countPosts();

    @Query(value = "SELECT COUNT(*) FROM post where sysdate() - time > 0 and mod_status = 0", nativeQuery = true)
    int countPostsOnModeration();

    @Query("FROM Post p where sysdate() - p.time > 0 and mod_status = 1 and is_active = 1 ORDER BY p.postComments.size DESC")
    Page<Post> findMostCommentedPosts(Pageable pageable);

    @Query("FROM Post p where sysdate() - p.time > 0 and mod_status = 1 and is_active = 1 ORDER BY p.time DESC")
    Page<Post> findMostRecentPosts(Pageable pageable);

    @Query(value = "SELECT *,\n" +
            "(SELECT SUM(value) FROM website.post_votes where sysdate() - time > 0 and value > 0 and p.id = post_id) as likeCount\n" +
            "FROM post p where sysdate() - p.time > 0 and mod_status = 1 and is_active = 1 \n" +
            "ORDER BY likeCount DESC", nativeQuery = true)
    Page<Post> findBestPost(Pageable pageable);

    @Query("FROM Post p where sysdate() - p.time > 0 and mod_status = 1 and is_active = 1 ORDER BY p.time")
    Page<Post> findMostEarlyPosts(Pageable pageable);

    @Query(value = "SELECT * FROM post p where sysdate() - p.time > 0 and p.is_active = 1 and p.mod_status = 1 and (p.title like %?1% OR p.text like %?1%)", nativeQuery = true)
    Page<Post> getPostsByQuery(String query, Pageable pageable);

    @Query(value = "SELECT YEAR(time) as year FROM post where mod_status = 1 and is_active = 1 group by time order by year", nativeQuery = true)
    List<Integer> findAllYears();

    @Query(value = "SELECT DATE(p.time) as date , COUNT(*) as count FROM Post as p where p.mod_status = 1 and p.is_active = 1 and sysdate() - p.time > 0 and YEAR(p.time) = ?1 group by p.time order by date", nativeQuery = true)
    List<CountPostPerYear> findCountForDate(String year);

    @Query(value = "select p.* " +
            "FROM post as p " +
            "left join tag2post as t2p on p.id = t2p.post_id " +
            "left join tags as t on t.id = t2p.tag_id " +
            "where p.id = t2p.post_id and t2p.tag_id = t.id and t.name = ?1 and p.mod_status = 1 and p.is_active = 1 and sysdate() - p.time > 0", nativeQuery = true)
    Page<Post> findByTag(String query, Pageable pageable);

    @Query(value = "select p.* FROM post as p where date(p.time) = ?1", nativeQuery = true)
    Page<Post> findByDate(String date, Pageable pageable);

    @Query(value = "select p.* from post as p where p.id = ?1 and p.mod_status = 1 and p.is_active = 1 and sysdate() - p.time > 0", nativeQuery = true)
    Post findById(int id);

    @Query(value = "select p.* from post as p where p.id = ?1 and p.is_active = 1", nativeQuery = true)
    Post findByIdForEdit(int id);

    @Query("FROM Post p where sysdate() - p.time > 0 and is_active = 0 and user_id = ?1 ORDER BY p.time DESC")
    Page<Post> findMyInactivePosts(Pageable pageable, int id);

    @Query("FROM Post p where sysdate() - p.time > 0 and mod_status = 0 and is_active = 1 and user_id = ?1 ORDER BY p.time DESC")
    Page<Post> findMyPendingPosts(Pageable pageable, int id);

    @Query("FROM Post p where sysdate() - p.time > 0 and mod_status = 2 and is_active = 1 and user_id = ?1 ORDER BY p.time DESC")
    Page<Post> findMyDeclinedPosts(Pageable pageable, int id);

    @Query("FROM Post p where sysdate() - p.time > 0 and mod_status = 1 and is_active = 1 and user_id = ?1 ORDER BY p.time DESC")
    Page<Post> findMyPublishedPosts(Pageable pageable, int id);

    @Query(value = "select p.* FROM Post as p where sysdate() - p.time > 0 and p.mod_status = 2 and p.is_active = 1 and p.moderator_id = ?1 ORDER BY p.time DESC", nativeQuery = true)
    Page<Post> getMyDeclinedPosts(int id, Pageable pageable);

    @Query(value = "select p.* FROM Post as p where sysdate() - p.time > 0 and p.mod_status = 1 and p.is_active = 1 and p.moderator_id = ?1 ORDER BY p.time DESC", nativeQuery = true)
    Page<Post> getMyAcceptedPosts(int id, Pageable pageable);

    @Query("FROM Post p where sysdate() - p.time > 0 and mod_status = 0 and is_active = 1 ORDER BY p.time DESC")
    Page<Post> getNewPosts(Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM post as p where p.user_id = ?1", nativeQuery = true)
    int getCountPostForUser(int userId);

    @Query(value = "SELECT COUNT(*) FROM post", nativeQuery = true)
    int getCountPostForAll();

    @Query(value = "SELECT sum(view_count) FROM post where user_id = ?1", nativeQuery = true)
    int getCountViewsForUser(int userId);

    @Query(value = "SELECT sum(view_count) FROM post", nativeQuery = true)
    int getCountViewsForAll();

    @Query(value = "SELECT time FROM post where user_id = ?1 order by time limit 1", nativeQuery = true)
    Date getFirstPublicationForUser(int userId);

    @Query(value = "SELECT time FROM post order by time limit 1", nativeQuery = true)
    Date getFirstPublicationForAll();
}
