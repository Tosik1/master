package main.service;

import main.api.response.TagResponse;
import main.repository.PostRepository;
import main.repository.TagsRepository;
import main.model.Tags;
import main.model.custom.CustomTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagsRepository tagsRepository;

    @Autowired
    private PostRepository postRepository;

    public TagResponse getTagResponse(String query){
        TagResponse tagResponse = new TagResponse();

        int countPosts = postRepository.countPosts();
        List<Tags> allTags = tagsRepository.tags();
        Tags mostPopularTag = allTags.get(0);
        double dWeightMax = mostPopularTag.getTag2Posts().size() / countPosts;
        double k = 1 / dWeightMax;

        if (query.equals("all")){
            List<CustomTags> allCustomTags = new ArrayList<>();
            for (Tags tags : allTags){
                tags.setWeight(tags.getTag2Posts().size() * k / countPosts);
                CustomTags customTags = new CustomTags(tags.getName(), tags.getWeight());
                allCustomTags.add(customTags);
            }
            tagResponse.setTags(allCustomTags);
        }
        else {
            List<CustomTags> oneTag = new ArrayList();
            for (Tags tags : allTags){
                if (query.equals(tags.getName())){
                    oneTag.add(new CustomTags(tags.getName(), tags.getWeight()));
                }
            }
            tagResponse.setTags(oneTag);
        }
        return tagResponse;
    }
}
