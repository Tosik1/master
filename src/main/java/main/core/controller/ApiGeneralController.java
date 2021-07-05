package main.core.controller;

import main.core.api.response.*;
import main.core.service.CalendarService;
import main.core.service.TagService;
import org.springframework.web.bind.annotation.*;
import main.core.service.SettingsService;

@RestController
@RequestMapping("/api")
public class ApiGeneralController {

    private final SettingsService settingsService;
    private final TagService tagService;
    private final CalendarService calendarService;
    private final InitResponse initResponse;

    public ApiGeneralController(SettingsService settingsService, TagService tagService, CalendarService calendarService, InitResponse initResponse) {
        this.settingsService = settingsService;
        this.tagService = tagService;
        this.calendarService = calendarService;
        this.initResponse = initResponse;
    }

    @GetMapping("/settings")
    private SettingsResponse settings() {
        return settingsService.getGlobalSettings();
    }

    @GetMapping("/init")
    private InitResponse init() {
        return initResponse;
    }

    @GetMapping("/tag")
    private TagResponse tag(@RequestParam(name = "query", required = false, defaultValue = "all") String query){
        return tagService.getTagResponse(query);
    }

    @GetMapping("/calendar")
    private CalendarResponse calendar(@RequestParam(name = "year", required = false) String year){
        return calendarService.getCalendarResponse(year);
    }

}
