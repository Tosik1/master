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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import main.model.Case1;

import java.util.List;

@RestController
public class ListController {

    private final Case1Dao case1Dao;

    @Autowired
    public ListController(Case1Dao case1Dao) {
        this.case1Dao = case1Dao;
    }

    @GetMapping("/cases/")
    public String list(Model model){
        model.addAttribute("cases", case1Dao.getAll());
        return "index";
    }

    //ПОлучим 1 дело по его id из DAO и передадим это дело на отображение в представление
    @GetMapping("/cases/{id}")
    public ResponseEntity get(@PathVariable int id, Model model){

        if (case1Dao.get(id) != null){
            model.addAttribute("/cases/case", case1Dao.get(id));
            return new ResponseEntity("cases/case", HttpStatus.OK);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/cases/new")
    public String newCase(@ModelAttribute("case") Case1 cas){
        return "cases/new";
    }

    @PostMapping("/cases/")
    public String add(@ModelAttribute("case") Case1 cas){
        case1Dao.save(cas);
        return "redirect:/cases";
    }

    @PutMapping("/cases/{id}")
    public int put(@PathVariable int id, Case1 cas){
        case1Dao.update(id, cas);
        return id;
    }



    @DeleteMapping("/cases/{id}")
    public void deleteCase(@PathVariable int id){
        case1Dao.delete(id);
    }

    @DeleteMapping("/cases/")
    public void deleteAllCases(){
        case1Dao.deleteAll();
    }
}
