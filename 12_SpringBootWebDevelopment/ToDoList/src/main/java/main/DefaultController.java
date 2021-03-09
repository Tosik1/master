package main;

import main.model.Case1;
import main.model.Case1Dao;
import main.model.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class DefaultController {

    @Autowired
    CaseRepository caseRepository;

    //Получим все дела из репозитория и передадим в представление
    @RequestMapping("/")
    public String index(Model model){
        Iterable<Case1> case1Iterable = caseRepository.findAll();
        ArrayList<Case1> cases = new ArrayList<>();
        for (Case1 cas : case1Iterable){
            cases.add(cas);
        }
        model.addAttribute("cases", cases);
        return "index";
    }
}
