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
import org.springframework.web.bind.annotation.*;
import main.model.Case1;

import java.util.List;

@RestController
public class ListController {

    @Autowired
    private final Case1Dao case1Dao;

    @Autowired
    public ListController(Case1Dao case1Dao) {
        this.case1Dao = case1Dao;
    }

    @GetMapping("/cases/")
    public List<Case1> list(){
        Case1Dao case1DaoList = new Case1Dao();
        return case1DaoList.getAll();
    }

    @PostMapping("/cases/")
    public int add(Case1 cas){
        Case1Dao case1DaoAdd = new Case1Dao();
        case1DaoAdd.save(cas);
        return cas.getId();
    }

    @PutMapping("/cases/{id}")
    public int put(@PathVariable int id, Case1 cas){
        Case1Dao case1DaoUpgrade = new Case1Dao();
        case1DaoUpgrade.update(id, cas);
        return id;
    }

    @GetMapping("/cases/{id}")
    public ResponseEntity get(@PathVariable int id){
        Case1Dao daoGet = new Case1Dao();
        if (daoGet.get(id) != null){
            return new ResponseEntity(daoGet.get(id), HttpStatus.OK);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/cases/{id}")
    public void deleteCase(@PathVariable int id){
        Case1Dao daoDelete = new Case1Dao();
        daoDelete.delete(id);
    }

    @DeleteMapping("/cases/")
    public void deleteAllCases(){
        Case1Dao daoDelete = new Case1Dao();
        daoDelete.deleteAll();
    }
}
