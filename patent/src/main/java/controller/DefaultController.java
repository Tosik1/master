package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import repository.CaptchaCodesRepository;

@Controller
public class DefaultController {

    @RequestMapping("/")
    public String index(Model model){
        return "index.html";
    }
}
