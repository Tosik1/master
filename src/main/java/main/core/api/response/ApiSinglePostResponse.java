package main.core.api.response;

import main.core.model.custom.CustomUser;

public class ApiSinglePostResponse {

    private int id;

    private Long timestamp;

    private CustomUser user;

    private String title;

    private String text;

    private int likeCount;

    private int dislikeCount;

    private int commentCount;

    private int viewCount;

    public ApiSinglePostResponse(int id, Long timestamp, CustomUser user, String title, String text, int likeCount, int dislikeCount, int commentCount, int viewCount) {
        this.id = id;
        this.timestamp = timestamp / (int) Math.pow(10, 3);
        this.user = user;
        this.title = title;
        if (text.length() > 150){
            this.text = text.substring(0, 150);
        }else {
            this.text = text;
        }
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.commentCount = commentCount;
        this.viewCount = viewCount;
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
        this.timestamp = timestamp / (int) Math.pow(10, 3);
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

    public String getAnnounce() {
        return text;
    }

    public void setText(String text) {
        if (text.length() > 150){
            this.text = text.substring(0, 150);
        }else {
            this.text = text;
        }
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

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
}