package main;

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

    @Autowired
    private CaseRepository caseRepository;

    @GetMapping("/cases/")
    public List<Case1> list(){
        Iterable<Case1> caseIterable = caseRepository.findAll();
        ArrayList<Case1> cases = new ArrayList<>();
        for (Case1 cas : caseIterable){
            cases.add(cas);
        }
        return cases;
    }

    @PostMapping("/cases/")
    public int add(Case1 cas){
        Case1 newCase = caseRepository.save(cas);
        return newCase.getId();
    }

    @PutMapping("/cases/{id}")
    public Case1 put(@PathVariable int id, Case1 cas){

        Optional<Case1> newCase = caseRepository.findById(id);
        newCase.get().setName(cas.getName());
        newCase.get().setDate(cas.getDate());
        return caseRepository.save(newCase.get());
    }

    @GetMapping("/cases/{id}")
    public ResponseEntity get(@PathVariable int id){
        Optional<Case1> optionalCase1 = caseRepository.findById(id);
        if (!optionalCase1.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalCase1.get(), HttpStatus.OK);
    }

    @DeleteMapping("/cases/{id}")
    public void deleteCase(@PathVariable int id){
        caseRepository.deleteById(id);
    }

    @DeleteMapping("/cases/")
    public void deleteAllCases(){
        caseRepository.deleteAll();
    }
}
