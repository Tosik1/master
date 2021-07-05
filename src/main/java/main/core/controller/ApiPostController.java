package main.core.controller;

import main.core.api.response.ApiPostResponse;
import main.core.api.response.PostResponse;
import main.core.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam(name = "mode", required = false, defaultValue = "recent") String mode) {
        return postService.getApiPostResponse(offset, limit, mode);
    }

    @GetMapping("/search")
    @ResponseBody
    private ResponseEntity<ApiPostResponse> getSearch(
            @RequestParam(name = "offset", required = false, defaultValue = "0") int offset,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit,
            @RequestParam(name = "query", required = false, defaultValue = " ") String query){
        return postService.getApiPostSearchResponse(offset, limit, query);
    }

    @GetMapping("/byTag")
    @ResponseBody
    private ResponseEntity<ApiPostResponse> getPostsByTag(
            @RequestParam(name = "offset", required = false, defaultValue = "0") int offset,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit,
            @RequestParam(name = "tag", required = false, defaultValue = "") String query){
        return postService.getPostsByTag(offset, limit, query);
    }

    @GetMapping("/byDate")
    @ResponseBody
    private ResponseEntity<ApiPostResponse> getPostsByDate(
            @RequestParam(name = "offset", required = false, defaultValue = "0") int offset,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit,
            @RequestParam(name = "date", required = false, defaultValue = "") String query){
        return postService.getPostsByDate(offset, limit, query);
    }

    @GetMapping("/{id}")
    private ResponseEntity<PostResponse> getById(@PathVariable int id){
        return postService.getPostById(id);
    }
}
