package main.controller;

import main.api.request.CommentRequest;
import main.api.request.EditProfileRequest;
import main.api.request.ModerationRequest;
import main.api.request.SettingsRequest;
import main.api.response.*;
import main.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ApiGeneralController {

    private final SettingsService settingsService;
    private final TagService tagService;
    private final CalendarService calendarService;
    private final InitResponse initResponse;
    private final CommentService commentService;
    private final StatisticsService statisticsService;
    private final UserService userService;
    private final PostService postService;

    public ApiGeneralController(SettingsService settingsService, TagService tagService, CalendarService calendarService, InitResponse initResponse, CommentService commentService, StatisticsService statisticsService, UserService userService, PostService postService) {
        this.settingsService = settingsService;
        this.tagService = tagService;
        this.calendarService = calendarService;
        this.initResponse = initResponse;
        this.commentService = commentService;
        this.statisticsService = statisticsService;
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/settings")
    public SettingsResponse settings() {
        return settingsService.getGlobalSettings();
    }

    @GetMapping("/init")
    public InitResponse init() {
        return initResponse;
    }

    @GetMapping("/tag")
    public TagResponse tag(@RequestParam(name = "query", required = false, defaultValue = "all") String query) {
        return tagService.getTagResponse(query);
    }

    @GetMapping("/calendar")
    public CalendarResponse calendar(@RequestParam(name = "year", required = false) String year) {
        return calendarService.getCalendarResponse(year);
    }

    @PostMapping("/comment")
    @ResponseBody
    public ResponseEntity comment(@Validated @RequestBody CommentRequest commentRequest) {
        return commentService.getCommentResponse(commentRequest.getParentId(), commentRequest.getPostId(), commentRequest.getText());
    }

    @GetMapping("/statistics/my")
    @ResponseBody
    public StatisticsResponse statisticsMy(){
        return statisticsService.getMyStatistics();
    }

    @GetMapping("/statistics/all")
    @ResponseBody
    public ResponseEntity statisticsAll(){
        return statisticsService.getAllStatistics();
    }

    @PostMapping(value = "/profile/my", consumes = "multipart/form-data")
    public ResponseEntity<EditProfileResponse> editProfile(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "removePhoto") int removePhoto,
            @RequestParam(value = "photo") MultipartFile file,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "password", required = false) String password
    ){
        return userService.editProfileWithPhoto(email, removePhoto, file, name, password);
    }

    @PostMapping("/profile/my")
    public ResponseEntity<EditProfileResponse> editProfile(@RequestBody EditProfileRequest editProfileRequest) {
        return userService.editProfileWithoutPhoto(editProfileRequest);
    }

    @PostMapping("/image")
    public ResponseEntity image(@RequestParam("image") MultipartFile file){
        return userService.addImage(file);
    }

    @PostMapping("/moderation")
    public ResponseEntity moderation(@RequestBody ModerationRequest request){
        return postService.moderation(request);
    }

    @PutMapping("/settings")
    public void setSettings(@RequestBody SettingsRequest request){
        settingsService.setGlobalSettings(request);
    }

}
