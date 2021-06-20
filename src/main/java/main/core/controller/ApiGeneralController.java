package main.core.controller;

import main.core.api.response.InitResponse;
import main.core.api.response.SettingsResponse;
import main.core.api.response.TagResponse;
import main.core.service.TagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import main.core.service.SettingsService;

@RestController
@RequestMapping("/api")
public class ApiGeneralController {

    private final SettingsService settingsService;
    private final TagService tagService;

    public ApiGeneralController(SettingsService settingsService, TagService tagService) {
        this.settingsService = settingsService;
        this.tagService = tagService;
    }

    @GetMapping("/settings")
    private SettingsResponse settings() {
        return settingsService.getGlobalSettings();
    }

    @GetMapping("/init")
    private InitResponse init() {
        return new InitResponse();
    }

    @GetMapping("/tag")
    private TagResponse tag(@RequestParam(name = "query", required = false, defaultValue = "all") String query){
        return tagService.getTagResponse(query);
    }
}
