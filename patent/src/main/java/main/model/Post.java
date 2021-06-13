package main.model;

import org.hibernate.type.TextType;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int isActive;

    private ModeratorStatus modStatus;

    @OneToOne
    private User moderator;

    @ManyToOne
    private User author;

    private long time;

    private String title;

    private TextType text;

    private int viewCount;

    public ModeratorStatus getModStatus() {
        return modStatus;
    }

    public void setModStatus(ModeratorStatus modStatus) {
        this.modStatus = modStatus;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getModeratorId() {
        return moderator.getId();
    }

    public void setModerator(User moderator) {
        this.moderator = moderator;
    }

    public int getUserId() {
        return author.getId();
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TextType getText() {
        return text;
    }

    public void setText(TextType text) {
        this.text = text;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
}
