package main.core.service;

import main.core.api.response.ApiPostResponse;
import main.core.model.Post;
import main.core.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class PostService {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Autowired
    private PostRepository postRepository;

    public ResponseEntity<ApiPostResponse> getApiPostResponse(int offset, int limit, String mode) throws SQLException {
        ApiPostResponse apiPostResponse = new ApiPostResponse();

        int countPosts = postRepository.countPosts();
        apiPostResponse.setCount(countPosts);

        Pageable first = PageRequest.of(offset, limit);

        switch (mode) {
            case "recent":
                Page<Post> recentPost = postRepository.findMostRecentPosts(first);
                apiPostResponse.setPosts(recentPost.getContent());
                break;
            case "popular":
                Page<Post> popularPost = postRepository.findMostCommentedPosts(first);
                apiPostResponse.setPosts(popularPost.getContent());
                break;
            case "best":
                Page<Post> bestPost = postRepository.findBestPost(first);
                apiPostResponse.setPosts(bestPost.getContent());
                break;
            case "early":
                Page<Post> earlyPost = postRepository.findMostEarlyPosts(first);
                apiPostResponse.setPosts(earlyPost.getContent());
                break;
        }

        return ResponseEntity.ok(apiPostResponse);
    }
}

//        connection = DriverManager.getConnection(url, username, password);
//                Statement statement = connection.createStatement();
//                ResultSet resultSet;
//                JSONObject jobj = new JSONObject();
//                List<Post> postList = new ArrayList();
