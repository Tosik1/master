package main.core.repository;

import main.core.api.response.ApiSinglePostResponse;
import main.core.model.Post;
import main.core.model.custom.CountPostPerYear;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {

    @Query(value = "SELECT COUNT(*) FROM post where sysdate() - time > 0 and mod_status = 1 and is_active = 1", nativeQuery = true)
    int countPosts();

    @Query("FROM Post p where sysdate() - p.time > 0 and mod_status = 1 and is_active = 1 ORDER BY p.postComments.size DESC")
    Page<Post> findMostCommentedPosts(Pageable pageable);

    @Query("FROM Post p where sysdate() - p.time > 0 and mod_status = 1 and is_active = 1 ORDER BY p.time DESC")
    Page<Post> findMostRecentPosts(Pageable pageable);

    @Query(value = "SELECT *, " +
            "(SELECT SUM(value) FROM post_votes where sysdate() - time > 0 and value < 0 and post.id = post_id) as likeCount " +
            "FROM post where mod_status = 1 and is_active = 1 " +
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
}
