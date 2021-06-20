package main.core.controller;

import main.core.api.response.ApiPostResponse;
import main.core.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/post")
public class ApiPostController {

    private final PostService postService;

    public ApiPostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("")
    @ResponseBody
    private ResponseEntity<ApiPostResponse> getPost(
            @RequestParam(name = "offset", required = false, defaultValue = "0") int offset,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit,
            @RequestParam(name = "mode", required = false, defaultValue = "recent") String mode) throws SQLException {
        return postService.getApiPostResponse(offset, limit, mode);
    }
}
