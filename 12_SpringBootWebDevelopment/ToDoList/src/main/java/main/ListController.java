package main;

import main.model.Case1Dao;
import main.model.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import main.model.Case1;

import java.util.List;

@Controller
public class ListController {

    private final Case1Dao case1Dao;

    @Autowired
    public ListController(Case1Dao case1Dao) {
        this.case1Dao = case1Dao;
    }

    // Возвращает ссылку "localhost:8080/index" и передает в эту ссылку весь репозиторий с пометкой cases. В этой странице мы можем вывести по этому ключу весь репозиторий
    @RequestMapping("/cases")
    public String list(Model model){
        model.addAttribute("cases", case1Dao.getAll());
        return "index.html";
    }

    //ПОлучим 1 дело по его id из DAO и передадим это дело на отображение в представление
    @GetMapping("/cases/{id}")
    public String get(@PathVariable int id, Model model){
            model.addAttribute("case", case1Dao.get(id));
            return "case.html";
    }

    //При переходе на ссылку "localhost:808/cases/new" метод создает Model Case1 и направляет пользователя на форму с этой же ссылкой.
    @GetMapping("cases/new")
    public String newCase(@ModelAttribute("case") Case1 cas){
        return "new.html";
    }

    // При получении метода отправки POST он обращается к case1Dao.save, и перенаправляет пользователя на ссылку "localhost:808/cases" которая обращается к методу
    // @GetMapping("/cases/") который в свою очеред возвращает пользователю ссылку "index"
    @PostMapping("/cases/new")
    public String add(@ModelAttribute("case") Case1 cas){
        case1Dao.save(cas);
        return "redirect:/cases";
    }

    @GetMapping("/update/{id}")
    public String updateCaseForm(@PathVariable("id") int id, Model model){
        Case1 cas = case1Dao.get(id);
        model.addAttribute("case", cas);
        return "/update.html";
    }

    @PutMapping("/update")
    public String updateCase(@ModelAttribute("case") Case1 cas){
        case1Dao.update(cas);
        return "redirect:/cases";
    }



    @GetMapping("/case-delete/{id}")
    public String deleteCase(@PathVariable("id") int id){
        case1Dao.delete(id);
        return "redirect:/cases";
    }

    @GetMapping("case-deleteAll")
    public String deleteAllCases(){
        case1Dao.deleteAll();
        return "redirect:/cases";
    }
}
