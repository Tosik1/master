package main.service;

import main.api.request.PostRequest;
import main.api.response.*;
import main.model.*;
import main.model.custom.CustomComment;
import main.model.custom.CustomUser;
import main.model.custom.CustomUserForPost;
import main.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostCommentsRepository postCommentsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Tag2PostRepository tag2PostRepository;

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

    public ResponseEntity<PostResponse> getPostById(int id) {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User checkingUser = userRepository.findUserByEmail(principal.getUsername()).get();
        Post post;
        if (checkingUser.isModerator() || checkingUser.checkingPosts(id)) {
            post = postRepository.findByIdForEdit(id);
        } else {
            post = postRepository.findById(id);
        }
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

        NewPostResponse newPost = new NewPostResponse();
        PostErrorsResponse postErrors = new PostErrorsResponse();
        int i = 0;

        if (title.length() < 3) {
            postErrors.setTitle("Название публикации слишком короткое");
            i++;
        }
        if (text.length() < 50) {
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

            List<Tags> listTags = new ArrayList<>();

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

            post.setTitle(title);
            post.setModStatusNew();
            post.setViewCount(0);

            org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            post.setUser(userRepository.findUserByEmail(principal.getUsername()).get());

            postRepository.save(post);

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
        if (userRepository.findUserByEmail(principal.getUsername()).get().isModerator() || post.getUser().getId() == userRepository.findUserByEmail(principal.getUsername()).get().getId()) {
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
                post.setText(request.getText());
                post.setIsActive(request.getActive());

                List<Tags> listTags = new ArrayList<>();


                List<Tag2Post> tag2PostList = post.getListTag2Post();
                for (Tag2Post tag2Post : tag2PostList){
                    tag2PostRepository.delete(tag2Post);
                    System.out.println();
                }
                post.setListTag2Post(new ArrayList<>());

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

                post.setTitle(request.getTitle());
                if (!userRepository.findUserByEmail(principal.getUsername()).get().isModerator()) {
                    post.setModStatusNew();
                }
                post.setViewCount(0);
                post.setUser(userRepository.findUserByEmail(principal.getUsername()).get());

                postRepository.save(post);

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
}