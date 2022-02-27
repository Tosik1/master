package main.service;

import main.api.response.StatisticsResponse;
import main.model.User;
import main.repository.GlobalSettingsRepository;
import main.repository.PostRepository;
import main.repository.PostVotesRepository;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private GlobalSettingsRepository settingsRepository;

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

    public ResponseEntity getAllStatistics() {

        int countMyPosts = postRepository.getCountPostForAll();
        int likesCount = postVotesRepository.getLikeCounts();
        int dislikesCount = postVotesRepository.getDislikeCounts();
        int viewsCount = postRepository.getCountViewsForAll();
        Date firstPublication = postRepository.getFirstPublicationForAll();

        firstPublication.getTime();

        if (settingsRepository.getPublicSetting().equals("YES")) {
            return ResponseEntity.ok(new StatisticsResponse(countMyPosts, likesCount, dislikesCount, viewsCount, firstPublication.getTime() / 1000));
        } else {
            if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
                org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                User user = userRepository.findUserByEmail(principal.getUsername()).get();
                if (user.isModerator()) {
                    return ResponseEntity.ok(new StatisticsResponse(countMyPosts, likesCount, dislikesCount, viewsCount, firstPublication.getTime() / 1000));
                }
            }
            return (ResponseEntity) ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }
    }
}
