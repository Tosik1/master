package main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping(value = "/*/{path:[^\\.]*}")
    public String redirectToIndex() {
        return "forward:/";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
//
//    @RequestMapping(value = {
//            "/edit/*",
//            "/calendar/*",
//            "/my/*",
//            "/login",
//            "/login/**",
//            "/moderator/*",
//            "/moderation/*",
//            "/post/*",
//            "/posts/*",
//            "/profile",
//            "settings",
//            "/stat",
//            "/404",
//            "/comment"
//    })
//    public String all() {
//        return "forward:/";
//    }
}
