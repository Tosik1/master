package main.service;

import lombok.var;
import main.api.response.StatisticsResponse;
import main.model.User;
import main.repository.PostRepository;
import main.repository.PostVotesRepository;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StatisticsService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostVotesRepository postVotesRepository;

    @Autowired
    private UserRepository userRepository;

    public StatisticsResponse getMyStatistics() {

        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findUserByEmail(principal.getUsername()).get();
        int userId = user.getId();

        int countMyPosts = postRepository.getCountPostForUser(userId);
        int likesCount = postVotesRepository.getLikeCountsUser(userId);
        int dislikesCount = postVotesRepository.getDislikeCountsUser(userId);
        int viewsCount = postRepository.getCountViewsForUser(userId);
        Date firstPublication = postRepository.getFirstPublicationForUser(userId);

        firstPublication.getTime();

        return new StatisticsResponse(countMyPosts, likesCount, dislikesCount, viewsCount, firstPublication.getTime() / 1000);
    }

    public StatisticsResponse getAllStatistics() {

        int countMyPosts = postRepository.getCountPostForAll();
        int likesCount = postVotesRepository.getLikeCounts();
        int dislikesCount = postVotesRepository.getDislikeCounts();
        int viewsCount = postRepository.getCountViewsForAll();
        Date firstPublication = postRepository.getFirstPublicationForAll();

        firstPublication.getTime();

        return new StatisticsResponse(countMyPosts, likesCount, dislikesCount, viewsCount, firstPublication.getTime() / 1000);
    }
}
