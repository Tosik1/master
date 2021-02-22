package main.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Case1Dao implements Dao{

    @Autowired
    private CaseRepository caseRepository;

    @Override
    @GetMapping("/cases/{id}")
    public ResponseEntity get(int id) {
            Optional<Case1> optionalCase1 = caseRepository.findById(id);
            if (!optionalCase1.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return new ResponseEntity(optionalCase1.get(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/cases/")
    public List<Case1> getAll() {
        Iterable<Case1> caseIterable = caseRepository.findAll();
        ArrayList<Case1> cases = new ArrayList<>();
        for (Case1 cas : caseIterable){
            cases.add(cas);
        }
        return cases;
    }

    @Override
    @PostMapping("/cases/")
    public void save(Case1 cas) {
        Case1 newCase = new Case1();
        newCase.setDate(cas.getDate());
        newCase.setName(cas.getName());
        caseRepository.save(newCase);
    }

    @Override
    @PutMapping("/cases/{id}")
    public void update(int id, Case1 cas) {
        Optional<Case1> newCase = caseRepository.findById(id);
        newCase.get().setName(cas.getName());
        newCase.get().setDate(cas.getDate());
    }

    @Override
    @DeleteMapping("/cases/{id}")
    public void delete(int id) {
        caseRepository.deleteById(id);
    }

    @Override
    @DeleteMapping("/cases/")
    public void deleteAll(){
        caseRepository.deleteAll();
    }
}
