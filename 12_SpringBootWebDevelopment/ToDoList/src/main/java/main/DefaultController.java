package main;

import main.model.Case1;
import main.model.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class DefaultController {

    @Autowired
    CaseRepository caseRepository;

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
