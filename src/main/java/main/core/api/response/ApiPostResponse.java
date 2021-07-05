package main.core.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import main.core.model.Post;

import java.util.List;

public class ApiPostResponse {

    @JsonProperty("count")
    private Long count;
    @JsonProperty("posts")
    private List<ApiSinglePostResponse> posts;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<ApiSinglePostResponse> getPosts() {
        return posts;
    }

    public void setPosts(List<ApiSinglePostResponse> posts) {
        this.posts = posts;
    }
}
