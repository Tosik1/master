package main.core.api.response;

import main.core.model.Tags;

import java.util.List;

public class TagResponse {

    private List<Tags> tags;

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }
}
