package main.core.model.custom;

import java.util.Date;

public class CustomComment {

    private int id;

    private CustomUserForPost user;

    private Date time;

    private String text;

    public CustomComment(int id, CustomUserForPost user, Date time, String text) {
        this.id = id;
        this.user = user;
        this.time = time;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CustomUserForPost getUser() {
        return user;
    }

    public void setUser(CustomUserForPost user) {
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
