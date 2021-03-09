package main.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Case1Dao implements Dao{

    @Autowired
    private CaseRepository caseRepository;

    @Override
    public Case1 get(int id) {
            Optional<Case1> optionalCase1 = caseRepository.findById(id);
            if (!optionalCase1.isPresent()){
                return null;
            }else {
                return optionalCase1.get();
            }
    }

    @Override
    public List<Case1> getAll() {
        Iterable<Case1> caseIterable = caseRepository.findAll();
        ArrayList<Case1> cases = new ArrayList<>();
        for (Case1 cas : caseIterable){
            cases.add(cas);
        }
        return cases;
    }

    @Override
    public void save(Case1 cas) {
        caseRepository.save(cas);
    }

    @Override
    public void update(int id, Case1 cas) {
        Optional<Case1> newCase = caseRepository.findById(id);
        newCase.get().setName(cas.getName());
        newCase.get().setDate(cas.getDate());
        newCase.get().setId(cas.getId());
        caseRepository.save(newCase.get());
    }

    @Override
    public void delete(int id) {
        caseRepository.deleteById(id);
    }

    @Override
    public void deleteAll(){
        caseRepository.deleteAll();
    }
}
