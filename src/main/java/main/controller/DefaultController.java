package main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DefaultController {

//    @RequestMapping(method = {RequestMethod.OPTIONS,
//            RequestMethod.GET}, value = "/{path:[^\\\\.]*}")
//    public String redirectToIndex() {
//        return "forward:/";
//    }
//
//    @RequestMapping(method = {RequestMethod.OPTIONS,
//            RequestMethod.GET}, value = "/*/{path:[^\\\\.]*}")
//    public String redirectToIndex1() {
//        return "forward:/";
//    }

    @GetMapping(value = "/**/{path:[^\\.]*}")
    public String redirectToIndex() {
        return "forward:/"; //делаем перенаправление
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
