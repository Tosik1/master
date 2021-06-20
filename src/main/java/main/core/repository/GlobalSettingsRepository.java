package main.core.repository;

import main.core.model.GlobalSettings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalSettingsRepository extends CrudRepository<GlobalSettings, Integer> {
}
