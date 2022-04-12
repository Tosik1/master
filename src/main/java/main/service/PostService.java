package main.service;

import main.api.request.ModerationRequest;
import main.api.request.PostRequest;
import main.api.response.*;
import main.model.*;
import main.model.custom.CustomComment;
import main.model.custom.CustomUser;
import main.model.custom.CustomUserForPost;
import main.repository.*;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.HTML;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostVotesRepository postVotesRepository;

    @Autowired
    private PostCommentsRepository postCommentsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Tag2PostRepository tag2PostRepository;

    @Autowired
    private TagsRepository tagsRepository;

    @Autowired
    private GlobalSettingsRepository globalSettingsRepository;

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
        ApiPostResponse apiPostResponse = getApiPostResponse(postsPage);

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

        ApiPostResponse apiPostResponse = getApiPostResponse(postsPage);
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

        ApiPostResponse apiPostResponse = getApiPostResponse(postsPage);
        return ResponseEntity.ok(apiPostResponse);
    }

    public ResponseEntity getPostById(int id) {
        if (postRepository.findByIdForEdit(id) == null) {
            return ResponseEntity.notFound().build();
        }
        Post post;
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.equals("anonymousUser")) {
            org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) user;
            User checkingUser = userRepository.findUserByEmail(principal.getUsername()).get();


            if (checkingUser.isModerator() || checkingUser.checkingPosts(id)) {
                post = postRepository.findByIdForEdit(id);
            } else {
                post = postRepository.findById(id);
                post.addView();
            }
        } else {
            post = postRepository.findById(id);
            post.addView();
        }
        postRepository.save(post);

        List<PostComments> postComments = postCommentsRepository.findByPostId(id);
        List<CustomComment> customComments = new ArrayList<>();

        for (PostComments pc : postComments) {
            User user1 = userRepository.findById(pc.getUserId()).get();
            CustomUserForPost customUserForPost = new CustomUserForPost(user1.getId(), user1.getName(), user1.getPhoto());
            customComments.add(new CustomComment(pc.getId(), customUserForPost, pc.getTime(), pc.getText()));
        }

        List<String> namesTags = tagsRepository.findTagsById(id);

        CustomUser customUser = new CustomUser(post.getUser().getName(), post.getUser().getId());
        PostResponse postResponse = new PostResponse(post.getId(), post.getTime(), post.getIsActive(), customUser, post.getTitle(), post.getText(),
                post.getLikeCount(), post.getDislikeCount(), post.getViewCount(), customComments, namesTags);
        return ResponseEntity.ok(postResponse);
    }

    public ResponseEntity<ApiPostResponse> getMyPostResponse(int offset, int limit, String status) {
        Pageable first = PageRequest.of(offset / limit, limit);
        Page<Post> postsPage;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int myId = userRepository.findUserByEmail(authentication.getName()).get().getId();

        switch (status) {
            case "inactive":
                postsPage = postRepository.findMyInactivePosts(first, myId);
                break;
            case "pending":
                postsPage = postRepository.findMyPendingPosts(first, myId);
                break;
            case "declined":
                postsPage = postRepository.findMyDeclinedPosts(first, myId);
                break;
            case "published":
            default:
                postsPage = postRepository.findMyPublishedPosts(first, myId);
                break;

        }
        ApiPostResponse apiPostResponse = getApiPostResponse(postsPage);
        return ResponseEntity.ok(apiPostResponse);
    }

    public ResponseEntity<ApiPostResponse> getPostsOnModeration(int offset, int limit, String status) {
        Pageable first = PageRequest.of(offset / limit, limit);
        Page<Post> postsPage;
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User currentUser = userRepository.findUserByEmail(principal.getUsername()).get();

        switch (status) {
            case "accepted":
                postsPage = postRepository.getMyAcceptedPosts(currentUser.getId(), first);
                break;
            case "declined":
                postsPage = postRepository.getMyDeclinedPosts(currentUser.getId(), first);
                break;
            case "new":
            default:
                postsPage = postRepository.getNewPosts(first);
                break;

        }
        ApiPostResponse apiPostResponse = getApiPostResponse(postsPage);

        return ResponseEntity.ok(apiPostResponse);
    }

    public ResponseEntity<NewPostResponse> postNewPost(long timestamp, int active, String title, String[] tags, String text) {

        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findUserByEmail(principal.getUsername()).get();
        NewPostResponse newPost = new NewPostResponse();
        PostErrorsResponse postErrors = new PostErrorsResponse();
        int i = 0;

        if (title.length() < 3 || title == null) {
            postErrors.setTitle("Название публикации слишком короткое");
            i++;
        }
        if (text.length() < 50 || text == null) {
            postErrors.setText("Для публикации не хватает символов в тексте");
            i++;
        }
        if (i > 0) {
            newPost.setResult(false);
            newPost.setErrors(postErrors);
        } else {
            Post post = new Post();
            long currentTime = System.currentTimeMillis();
            if (currentTime > timestamp) {
                timestamp = currentTime;
            }
            post.setTime(new Date(timestamp));
            post.setText(text);
            post.setIsActive(active);
            post.setTitle(title);
            if (user.isModerator() || globalSettingsRepository.getPremoderationSetting().equals("NO")) {
                post.setModStatusAccepted();
            } else {
                post.setModStatusNew();
            }
            post.setViewCount(0);
            post.setUser(user);

            postRepository.save(post);

            List<Tags> listTags = new ArrayList<>();

            if (active == 1) {
                for (String tag : tags) {
                    if (tagsRepository.findCountTagsByName(tag) == 0) {
                        Tags newTag = new Tags();
                        newTag.setName(tag);
                        newTag.setWeight(1);
                        tagsRepository.save(newTag);
                        listTags.add(newTag);
                    } else {
                        listTags.add(tagsRepository.findTagByName(tag).get());
                    }
                }
            }

            for (Tags tag : listTags) {
                Tag2Post tag2Post = new Tag2Post();
                tag2Post.setPost(post);
                tag2Post.setTag(tag);
                post.addTagOnTag2Post(tag2Post);
                tag.addTag2Post(tag2Post);
                tag2PostRepository.save(tag2Post);
            }
        }
        return ResponseEntity.ok(newPost);
    }

    public ResponseEntity editPost(int id, PostRequest request) {
        Post post = postRepository.findByIdForEdit(id);
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userRepository.findUserByEmail(principal.getUsername()).get().isModerator() ||
                post.getUser().getId() == userRepository.findUserByEmail(principal.getUsername()).get().getId()) {
            NewPostResponse newPost = new NewPostResponse();
            PostErrorsResponse postErrors = new PostErrorsResponse();
            int i = 0;

            if (request.getTitle().length() < 3) {
                postErrors.setTitle("Название публикации слишком короткое");
                i++;
            }
            if (request.getText().length() < 50) {
                postErrors.setText("Для публикации не хватает символов в тексте");
                i++;
            }
            if (i > 0) {
                newPost.setResult(false);
                newPost.setErrors(postErrors);
            } else {
                long currentTime = System.currentTimeMillis();
                long timestamp = request.getTimestamp();
                if (currentTime > timestamp) {
                    timestamp = currentTime;
                }
                post.setTime(new Date(timestamp));
                post.setText(Jsoup.parse(request.getText()).text());
                post.setIsActive(request.getActive());

                List<Tag2Post> tag2PostList = post.getListTag2Post();
                for (Tag2Post tag2Post : tag2PostList) {
                    tag2PostRepository.delete(tag2Post);
                }
                post.setListTag2Post(new ArrayList<>());
                post.setTitle(request.getTitle());
                if (!userRepository.findUserByEmail(principal.getUsername()).get().isModerator()) {
                    post.setModStatusNew();
                }
                post.setUser(userRepository.findUserByEmail(principal.getUsername()).get());
                postRepository.save(post);

                if (request.getActive() == 1) {
                    List<Tags> listTags = new ArrayList<>();
                    for (String tag : request.getTags()) {
                        if (tagsRepository.findCountTagsByName(tag) == 0) {
                            Tags newTag = new Tags();
                            newTag.setName(tag);
                            newTag.setWeight(1);
                            tagsRepository.save(newTag);

                            listTags.add(newTag);
                        } else {
                            listTags.add(tagsRepository.findTagByName(tag).get());
                        }
                    }

                    for (Tags tag : listTags) {
                        Tag2Post tag2Post = new Tag2Post();
                        tag2Post.setPost(post);
                        tag2Post.setTag(tag);
                        post.addTagOnTag2Post(tag2Post);
                        tag.addTag2Post(tag2Post);
                        tag2PostRepository.save(tag2Post);
                    }
                }
            }
            return ResponseEntity.ok(newPost);

        }
        return (ResponseEntity) ResponseEntity.badRequest();
    }

    private ApiPostResponse getApiPostResponse(Page<Post> postsPage) {
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
        return apiPostResponse;
    }

    public ResponseEntity<LikeResponse> getLikeResponse(int postId) {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findUserByEmail(principal.getUsername()).get();
        LikeResponse likeResponse = new LikeResponse();
        Post post = postRepository.findById(postId);
        List<PostVotes> postVotes = postVotesRepository.findByPostId(postId);
        if (postVotes != null) {
            for (PostVotes votes : postVotes) {
                if (votes.getUserId() == currentUser.getId()) {
                    if (votes.getValue() == 1) {
                        likeResponse.setResult(false);
                        return ResponseEntity.ok(likeResponse);
                    } else {
                        postVotesRepository.changeValue(1, votes.getId());
                        likeResponse.setResult(true);
                        return ResponseEntity.ok(likeResponse);
                    }
                }
            }
        }
        PostVotes pv = new PostVotes();
        pv.setPost(post);
        pv.setTime(new Date(System.currentTimeMillis()));
        pv.setUser(currentUser);
        pv.setValue(1);
        postVotesRepository.save(pv);
        likeResponse.setResult(true);
        return ResponseEntity.ok(likeResponse);
    }

    public ResponseEntity<LikeResponse> getDislikeResponse(int postId) {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findUserByEmail(principal.getUsername()).get();
        LikeResponse likeResponse = new LikeResponse();
        Post post = postRepository.findById(postId);
        List<PostVotes> postVotes = postVotesRepository.findByPostId(postId);
        if (postVotes != null) {
            for (PostVotes votes : postVotes) {
                if (votes.getUserId() == currentUser.getId()) {
                    if (votes.getValue() == -1) {
                        likeResponse.setResult(false);
                        return ResponseEntity.ok(likeResponse);
                    } else {
                        postVotesRepository.changeValue(-1, votes.getId());
                        likeResponse.setResult(true);
                        return ResponseEntity.ok(likeResponse);
                    }
                }
            }
        }
        PostVotes pv = new PostVotes();
        pv.setPost(post);
        pv.setTime(new Date(System.currentTimeMillis()));
        pv.setUser(currentUser);
        pv.setValue(-1);
        postVotesRepository.save(pv);
        likeResponse.setResult(true);
        return ResponseEntity.ok(likeResponse);
    }

    public ResponseEntity moderation(ModerationRequest request) {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findUserByEmail(principal.getUsername()).get();
        Post post = postRepository.findByIdForEdit(request.getPostId());

        if (request.getDecision().equals("accept")) {
            post.setModStatusAccepted();
            post.setModerator(currentUser);
        } else if (request.getDecision().equals("decline")) {
            post.setModStatusDeclined();
            post.setModerator(currentUser);
        } else {
            return ResponseEntity.ok(new ModerationResponse(false));
        }
        postRepository.save(post);

        return ResponseEntity.ok(new ModerationResponse(true));
    }

    public int countPostsOnModeration() {
        return postRepository.getCountPostOnModeration();
    }
}