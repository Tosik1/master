package main.service;

import main.api.response.CommentErrorsResponse;
import main.api.response.CommentResponse;
import main.model.Post;
import main.model.PostComments;
import main.repository.PostCommentsRepository;
import main.repository.PostRepository;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostCommentsRepository postCommentsRepository;

    public ResponseEntity getCommentResponse(int parentId, int postId, String text){
        CommentResponse commentResponse = new CommentResponse();
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (text.length() < 6 || text == null) {
            CommentErrorsResponse errors = new CommentErrorsResponse();
            errors.setText("Текст комментария не задан или слишком короткий");
            commentResponse.setErrors(errors);
            commentResponse.setResult(false);
            return ResponseEntity.badRequest().body(commentResponse);
        }else {
            PostComments postComments = new PostComments();
            Post post = postRepository.findById(postId);
            postComments.setPost(post);
            postComments.setText(text);
            postComments.setTime(new Date(System.currentTimeMillis()));
            postComments.setUser(userRepository.findUserByEmail(principal.getUsername()).get());
            if (!String.valueOf(parentId).equals("")){
                postComments.setParent(postCommentsRepository.findByParentId(parentId));
            }
            postCommentsRepository.save(postComments);
            commentResponse.setResult(null);
            commentResponse.setId(postComments.getId());
            return ResponseEntity.ok(commentResponse);
        }

    }
}
