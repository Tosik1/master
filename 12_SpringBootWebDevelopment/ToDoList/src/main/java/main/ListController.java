package main;

import main.model.Case1Dao;
import main.model.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Case1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ListController {

//    @GetMapping("/cases/")
    public List<Case1> list(){
        Case1Dao case1DaoList = new Case1Dao();
        return case1DaoList.getAll();
    }

//    @PostMapping("/cases/")
    public int add(Case1 cas){
        Case1Dao case1DaoAdd = new Case1Dao();
        case1DaoAdd.save(cas);
        return cas.getId();
    }

//    @PutMapping("/cases/{id}")
    public int put(@PathVariable int id, Case1 cas){
        Case1Dao case1DaoUpgrade = new Case1Dao();
        case1DaoUpgrade.update(id, cas);
        return id;
    }

//    @GetMapping("/cases/{id}")
    public ResponseEntity get(@PathVariable int id){
        Case1Dao daoGet = new Case1Dao();
        return daoGet.get(id);
    }

//    @DeleteMapping("/cases/{id}")
    public void deleteCase(@PathVariable int id){
        Case1Dao daoDelete = new Case1Dao();
        daoDelete.delete(id);
    }

//    @DeleteMapping("/cases/")
    public void deleteAllCases(){
        Case1Dao daoDelete = new Case1Dao();
        daoDelete.deleteAll();
    }
}
