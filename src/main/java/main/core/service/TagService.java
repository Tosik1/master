package main.core.service;

import main.core.api.response.TagResponse;
import main.core.model.Tags;
import main.core.repository.PostRepository;
import main.core.repository.TagsRepository;
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
            for (Tags tags : allTags){
                tags.setWeight(tags.getTag2Posts().size() * k);
            }
            tagResponse.setTags(allTags);
        }
        else {
            List<Tags> oneTag = new ArrayList();
            for (Tags tags : allTags){
                if (query.equals(tags.getName())){
                    oneTag.add(tags);
                }
            }
            tagResponse.setTags(oneTag);
        }
        return tagResponse;
    }
}
