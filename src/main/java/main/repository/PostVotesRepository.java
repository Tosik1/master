package main.repository;

import main.model.PostComments;
import main.model.PostVotes;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PostVotesRepository extends CrudRepository<PostVotes, Integer> {

    @Query(value = "SELECT * FROM post_votes as pv where pv.post_id = ?1", nativeQuery = true)
    List<PostVotes> findByPostId(int postId);

    @Modifying
    @Transactional
    @Query(value = "update post_votes as pv set pv.value = ?1 where pv.id = ?2", nativeQuery = true)
    void changeValue(int value, int id);

    @Query(value = "SELECT COUNT(*) FROM post as p left JOIN post_votes as pv ON p.id = pv.post_id WHERE p.user_id = ?1 and pv.value = 1", nativeQuery = true)
    int getLikeCountsUser(int userId);

    @Query(value = "SELECT COUNT(*) FROM post as p left JOIN post_votes as pv ON p.id = pv.post_id WHERE pv.value = 1", nativeQuery = true)
    int getLikeCounts();

    @Query(value = "SELECT COUNT(*) FROM post as p left JOIN post_votes as pv ON p.id = pv.post_id WHERE p.user_id = ?1 and pv.value = -1", nativeQuery = true)
    int getDislikeCountsUser(int userId);

    @Query(value = "SELECT COUNT(*) FROM post as p left JOIN post_votes as pv ON p.id = pv.post_id WHERE pv.value = -1", nativeQuery = true)
    int getDislikeCounts();
}
