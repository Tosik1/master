package main.dao;

import main.model.Post;

import java.util.List;

public interface DaoPosts {

    Post get(int id);

    List<Post> getAll();

    void save(Post post);

    void update(Post post);

    void delete(int id);

    void deleteAll();
}
