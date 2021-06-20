package main.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class PostComments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;



//    @OneToMany(mappedBy = "parent")
//    @JsonIgnore
//    private List<PostComments> childComments;
//
//    @ManyToOne
//    @JsonIgnore
//    private PostComments parent;

    @ManyToOne
    @JsonIgnore
    private Post post;

    @OneToOne
    private User user;

    private Date time;

    private String text;



//    public PostComments getParent() {
//        return parent;
//    }
//
//    public void setParent(PostComments parent) {
//        this.parent = parent;
//    }

    public Post getPost() {
        return post;
    }

    public User getUser() {
        return user;
    }
//
//    public List<PostComments> getChildComments() {
//        return childComments;
//    }
//
//    public void setChildComments(List<PostComments> childComments) {
//        this.childComments = childComments;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public int getParentId() {
//        return parent.getId();
//    }
//
//    public void setParentId(PostComments parent) {
//        this.parent = parent;
//    }

    public int getPostId() {
        return post.getId();
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public int getUserId() {
        return user.getId();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
