package main.core.service;

import main.core.api.response.ApiPostResponse;
import main.core.api.response.ApiSinglePostResponse;
import main.core.api.response.PostResponse;
import main.core.model.Post;
import main.core.model.PostComments;
import main.core.model.User;
import main.core.model.custom.CustomComment;
import main.core.model.custom.CustomUser;
import main.core.model.custom.CustomUserForPost;
import main.core.repository.PostCommentsRepository;
import main.core.repository.PostRepository;
import main.core.repository.TagsRepository;
import main.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostCommentsRepository postCommentsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagsRepository tagsRepository;

    public ResponseEntity<ApiPostResponse> getApiPostResponse(int offset, int limit, String mode) {
        Pageable first = PageRequest.of(offset / limit, limit);
        Page<Post> postsPage;

        switch (mode) {
            case "recent":
                postsPage = postRepository.findMostRecentPosts(first);
                break;
            case "popular":
                postsPage = postRepository.findMostCommentedPosts(first);
                break;
            case "best":
                postsPage = postRepository.findBestPost(first);
                break;
            case "early":
            default:
                postsPage = postRepository.findMostEarlyPosts(first);
                break;

        }
        List<ApiSinglePostResponse> singlePage = new ArrayList<>();
        for (Post p : postsPage) {
            CustomUser customUser = new CustomUser(p.getUser().getName(), p.getUser().getId());
            customUser.setId(p.getUser().getId());
            customUser.setName(p.getUser().getName());
            ApiSinglePostResponse apiSinglePostResponse = new ApiSinglePostResponse(p.getId(), p.getTime(), customUser, p.getTitle(), p.getText(), p.getLikeCount(), p.getDislikeCount(),
                    p.getCommentCount(), p.getViewCount());
            singlePage.add(apiSinglePostResponse);
        }
        ApiPostResponse apiPostResponse = new ApiPostResponse();
        apiPostResponse.setPosts(singlePage);
        apiPostResponse.setCount(postsPage.getTotalElements());

        return ResponseEntity.ok(apiPostResponse);
    }

    public ResponseEntity<ApiPostResponse> getApiPostSearchResponse(int offset, int limit, String query) {

        Pageable first = PageRequest.of(offset / limit, limit);
        Page<Post> postsPage;
        if (query.trim().equals("")) {
            postsPage = postRepository.findMostRecentPosts(first);
        } else {
            postsPage = postRepository.getPostsByQuery(query, first);
        }

        List<ApiSinglePostResponse> singlePage = new ArrayList<>();
        for (Post p : postsPage) {
            CustomUser customUser = new CustomUser(p.getUser().getName(), p.getUser().getId());
            ApiSinglePostResponse apiSinglePostResponse = new ApiSinglePostResponse(p.getId(), p.getTime(), customUser, p.getTitle(), p.getText(), p.getLikeCount(), p.getDislikeCount(),
                    p.getCommentCount(), p.getViewCount());
            singlePage.add(apiSinglePostResponse);
        }
        ApiPostResponse apiPostResponse = new ApiPostResponse();
        apiPostResponse.setPosts(singlePage);
        apiPostResponse.setCount(postsPage.getTotalElements());
        return ResponseEntity.ok(apiPostResponse);
    }

    public ResponseEntity<ApiPostResponse> getPostsByTag(int offset, int limit, String tag) {
        Pageable first = PageRequest.of(offset / limit, limit);
        Page<Post> postsPage;

        if (tag.equals("")) {
            postsPage = postRepository.findMostRecentPosts(first);
        } else {
            postsPage = postRepository.findByTag(tag, first);
        }

        List<ApiSinglePostResponse> singlePage = new ArrayList<>();
        for (Post p : postsPage) {
            CustomUser customUser = new CustomUser(p.getUser().getName(), p.getUser().getId());
            ApiSinglePostResponse apiSinglePostResponse = new ApiSinglePostResponse(p.getId(), p.getTime(), customUser, p.getTitle(), p.getText(), p.getLikeCount(), p.getDislikeCount(),
                    p.getCommentCount(), p.getViewCount());
            singlePage.add(apiSinglePostResponse);
        }
        ApiPostResponse apiPostResponse = new ApiPostResponse();
        apiPostResponse.setPosts(singlePage);
        apiPostResponse.setCount(postsPage.getTotalElements());
        return ResponseEntity.ok(apiPostResponse);
    }

    public ResponseEntity<ApiPostResponse> getPostsByDate(int offset, int limit, String date) {
        Pageable first = PageRequest.of(offset / limit, limit);
        Page<Post> postsPage;

        if (date.equals("")) {
            postsPage = postRepository.findMostRecentPosts(first);
        } else {
            postsPage = postRepository.findByDate(date, first);
        }

        List<ApiSinglePostResponse> singlePage = new ArrayList<>();
        for (Post p : postsPage) {
            CustomUser customUser = new CustomUser(p.getUser().getName(), p.getUser().getId());
            ApiSinglePostResponse apiSinglePostResponse = new ApiSinglePostResponse(p.getId(), p.getTime(), customUser, p.getTitle(), p.getText(), p.getLikeCount(), p.getDislikeCount(),
                    p.getCommentCount(), p.getViewCount());
            singlePage.add(apiSinglePostResponse);
        }
        ApiPostResponse apiPostResponse = new ApiPostResponse();
        apiPostResponse.setPosts(singlePage);
        apiPostResponse.setCount(postsPage.getTotalElements());
        return ResponseEntity.ok(apiPostResponse);
    }

    public ResponseEntity<PostResponse> getPostById(int id) {
        Post post = postRepository.findById(id);
        List<PostComments> postComments = postCommentsRepository.findByPostId(id);
        List<CustomComment> customComments = new ArrayList<>();

        for (PostComments pc : postComments) {
            User user = userRepository.findById(pc.getUserId()).get();
            CustomUserForPost customUserForPost = new CustomUserForPost(user.getId(), user.getName(), user.getPhoto());
            customComments.add(new CustomComment(pc.getId(), customUserForPost, pc.getTime(), pc.getText()));
        }

        List<String> namesTags = tagsRepository.findTagsById(id);


        CustomUser customUser = new CustomUser(post.getUser().getName(), post.getUser().getId());
        PostResponse postResponse = new PostResponse(post.getId(), post.getTime(), post.getIsActive(), customUser, post.getTitle(), post.getText(),
                post.getLikeCount(), post.getDislikeCount(), post.getViewCount(), customComments, namesTags);

        return ResponseEntity.ok(postResponse);
    }
}