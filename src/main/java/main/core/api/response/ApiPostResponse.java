package main.core.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import main.core.model.Post;

import java.util.List;

public class ApiPostResponse {

    @JsonProperty("count")
    private int count;
    @JsonProperty("posts")
    private List<Post> posts;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
