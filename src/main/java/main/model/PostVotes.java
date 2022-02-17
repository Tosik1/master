package main.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PostVotes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @OneToOne
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @NotNull
    private Date time;

    @NotNull
    private int value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return user.getId();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPostId() {
        return post.getId();
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
