package main.core.api.response;

import main.core.model.Tags;
import main.core.model.custom.CustomTags;

import java.util.List;

public class TagResponse {

    private List<CustomTags> tags;

    public List<CustomTags> getTags() {
        return tags;
    }

    public void setTags(List<CustomTags> tags) {
        this.tags = tags;
    }
}
