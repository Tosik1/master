package main.core.api.response;

import main.core.model.custom.CustomComment;
import main.core.model.custom.CustomUser;

import java.util.List;

public class PostResponse {

    private int id;

    private Long timestamp;

    private boolean active;

    private CustomUser user;

    private String title;

    private String text;

    private int likeCount;

    private int dislikeCount;

    private int viewCount;

    private List<CustomComment> comments;

    private List<String> tags;

    public PostResponse(int id, Long timestamp, int active, CustomUser user, String title, String text, int likeCount, int dislikeCount, int viewCount, List<CustomComment> comments, List<String> tags) {
        this.id = id;
        this.timestamp = timestamp / (int) Math.pow(10, 3);
        if (active == 1) {
            this.active = true;
        } else this.active = false;
        this.user = user;
        this.title = title;
        this.text = text;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.viewCount = viewCount;
        this.comments = comments;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public CustomUser getUser() {
        return user;
    }

    public void setUser(CustomUser user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(int dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public List<CustomComment> getComments() {
        return comments;
    }

    public void setComments(List<CustomComment> comments) {
        this.comments = comments;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
