package main.core.model;

import javax.persistence.*;

@Entity
public class Tag2Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Post post;

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
