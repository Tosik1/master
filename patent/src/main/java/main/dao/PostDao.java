//package main.dao;
//
//import main.model.Post;
//import main.repository.PostRepository;
//import main.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class PostDao implements DaoPosts {
//
//    @Autowired
//    private PostRepository postRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public Post get(int id) {
//        Optional<Post> optionalPost = postRepository.findById(id);
//        if (!optionalPost.isPresent()){
//            return null;
//        }else {
//            return optionalPost.get();
//        }
//    }
//
//    @Override
//    public List<Post> getAll() {
//        Iterable<Post> postIterable = postRepository.findAll();
//        ArrayList<Post> posts = new ArrayList<>();
//        for (Post post : postIterable){
//            posts.add(post);
//        }
//        return posts;
//    }
//
//    @Override
//    public void save(Post post) {
//        postRepository.save(post);
//    }
//
//    @Override
//    public void update(Post post) {
//        Optional<Post> newPost = postRepository.findById(post.getId() + 1);
//        Post item = newPost.get();
//
//        item.setAuthor(userRepository.findById(post.getUserId()).get());
//        item.setIsActive(post.getIsActive());
//        item.setModerator(userRepository.findById(post.getModeratorId()).get());
//        item.setText(post.getText());
//        item.setTime(post.getTime());
//        item.setTitle(post.getTitle());
//        item.setViewCount(post.getViewCount());
//        item.setModStatus(post.getModStatus());
//
//        postRepository.save(item);
//    }
//
//    @Override
//    public void delete(int id) {
//        postRepository.deleteById(id);
//    }
//
//    @Override
//    public void deleteAll() {
//        postRepository.deleteAll();
//    }
//}
