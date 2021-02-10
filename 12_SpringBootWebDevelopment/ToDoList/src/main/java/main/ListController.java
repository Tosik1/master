package main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import response.Case;

import java.util.List;

@RestController
public class ListController {


    @GetMapping("/cases/")
    public List<Case> list(){
        return Storage.getAllCases();
    }

    @PostMapping("/cases/")
    public int add(Case cas){
        return Storage.addCase(cas);
    }

    @PutMapping("/cases/{id}")
    public Case put(@PathVariable int id, Case cas){
        return Storage.putCase(id, cas);
    }

    @GetMapping("/cases/{id}")
    public ResponseEntity get(@PathVariable int id){
        Case case1 = Storage.getCase(id);
        if (case1 == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(case1, HttpStatus.OK);
    }

    @DeleteMapping("/cases/{id}")
    public void deleteCase(@PathVariable int id){
        Storage.deleteCase(id);
    }

    @DeleteMapping("/cases/")
    public void deleteAllCases(){
        Storage.deleteAllCases();
    }
}
