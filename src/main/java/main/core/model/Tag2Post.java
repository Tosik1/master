package main.core.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Tag2Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne
    private Post post;

    @NotNull
    @ManyToOne
    private Tags tag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return post.getId();
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public int getTagId() {
        return tag.getId();
    }

    public void setTag(Tags tag) {
        this.tag = tag;
    }
}
