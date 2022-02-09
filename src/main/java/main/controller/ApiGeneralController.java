package main.controller;

import main.api.response.*;
import main.service.TagService;
import main.service.CalendarService;
import org.springframework.web.bind.annotation.*;
import main.service.SettingsService;

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
    public SettingsResponse settings() {
        return settingsService.getGlobalSettings();
    }

    @GetMapping("/init")
    public InitResponse init() {
        return initResponse;
    }

    @GetMapping("/tag")
    public TagResponse tag(@RequestParam(name = "query", required = false, defaultValue = "all") String query){
        return tagService.getTagResponse(query);
    }

    @GetMapping("/calendar")
    public CalendarResponse calendar(@RequestParam(name = "year", required = false) String year){
        return calendarService.getCalendarResponse(year);
    }

//    @PostMapping("/image")
//    public ImageResponse image(){
//        return
//    }

}
