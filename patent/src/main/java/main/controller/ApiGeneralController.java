package main.controller;

import main.api.response.InitResponse;
import main.api.response.SettingsResponse;
import main.model.Post;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import main.service.SettingsService;

import java.sql.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ApiGeneralController {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    private static Connection connection;

    private final InitResponse initResponse;
    private final SettingsService settingsService;
//    private final PostDao postDao;

    public ApiGeneralController(InitResponse initResponse, SettingsService settingsService) {
        this.initResponse = initResponse;
        this.settingsService = settingsService;
//        this.postDao = postDao;
    }

    @GetMapping("/settings")
    private SettingsResponse settings() {
        return settingsService.getGlobalSettings();
    }

    @GetMapping("/init")
    private InitResponse init() {
        return initResponse;
    }

//    @GetMapping("/post")
//    private List<Post> post(@RequestParam("offset") int offset, @RequestParam("limit") int limit, @RequestParam("mode") String mode) throws SQLException {
//
//        switch (mode) {
//            case "recent":
//                connection = DriverManager.getConnection(url, username, password);
//                Statement statement = connection.createStatement();
//                ResultSet resultSet = statement.executeQuery("SELECT * FROM post WHERE is_active = 1 AND moderator_status = ACCEPTED ORDER BY time DESC LIMIT " + limit);
//
//                try (PreparedStatement stm = connection.prepareStatement("SELECT * FROM post WHERE is_active = 1 AND moderator_status = ACCEPTED ORDER BY time DESC LIMIT " + limit))
//                {
//
//                }
//
//                break;
//            case "popular":
//                "SELECT post.id, COUNT(*) as count_comments FROM post_comments JOIN post ON post.id = post_comments.post_id GROUP BY post_id ORDER BY count_comments"
//                break;
//            case "best":
//
//                break;
//            case "early":
//
//                break;
//        }
//    }
}
