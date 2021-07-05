package main.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private int isActive;

    @NotNull
    @Enumerated
    private ModeratorStatus modStatus;

    @OneToOne
    private User moderator;

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    private Date time;

    @NotNull
    private String title;

    @NotNull
    private String text;

    @NotNull
    private int viewCount;

    @OneToMany(mappedBy = "post")
    private List<PostComments> postComments;

    @OneToMany(mappedBy = "post")
    private List<PostVotes> postVotes;

    @OneToMany(mappedBy = "post")
    private List<Tag2Post> listTag2Post;

    public List<PostVotes> getPostVotes() {
        return postVotes;
    }

    public void setPostVotes(List<PostVotes> postVotes) {
        this.postVotes = postVotes;
    }

    public List<Tag2Post> getListTag2Post() {
        return listTag2Post;
    }

    public void setListTag2Post(List<Tag2Post> listTag2Post) {
        this.listTag2Post = listTag2Post;
    }


        public User getModerator() {
        return moderator;
    }

    public int getLikeCount(){
        int likeCount = 0;
        for (PostVotes pv : postVotes){
            if (pv.getValue() > 0){
                likeCount++;
            }
        }
        return likeCount;
    }

    public int getDislikeCount(){
        int dislikeCount = 0;
        for (PostVotes pv : postVotes){
            if (pv.getValue() < 0){
                dislikeCount++;
            }
        }
        return dislikeCount;
    }

    public User getUser() {
        return user;
    }

    public int getUserId(){ return user.getId();}

    public void setUser(User user) {
        this.user = user;
    }

    public List<PostComments> getPostComments() {
        return postComments;
    }

    public void setPostComments(List<PostComments> postComments) {
        this.postComments = postComments;
    }

    public ModeratorStatus getModStatus() {
        return modStatus;
    }

    public void setModStatus(ModeratorStatus modStatus) {
        this.modStatus = modStatus;
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

    public int getCommentCount(){
        return postComments.size();
    }

    public long getTime() {
        return time.getTime();
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAnnounce(){
        if (text.length() > 150){
            return text.substring(0, 150).concat("...");
        }
        else return text;
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

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
}
enum ModeratorStatus {
    NEW,
    ACCEPTED,
    DECLINED;

    ModeratorStatus(){
    }
}