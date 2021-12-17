package main.model.custom;

import lombok.Data;

@Data
public class CustomUserForLogin {

    private int id;

    private String name;

    private String photo;

    private String email;

    private boolean moderation;

    private int moderationCount = 0;

    private boolean settings = false;

}
