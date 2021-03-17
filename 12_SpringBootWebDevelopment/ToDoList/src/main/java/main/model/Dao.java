package main.model;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface Dao {

    Case1 get(int id);

    List<Case1> getAll();

    void save(Case1 cas);

    void update(Case1 cas);

    void delete(int id);

    void deleteAll();
}