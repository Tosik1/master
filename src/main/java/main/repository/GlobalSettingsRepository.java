package main.repository;

import main.model.GlobalSettings;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface GlobalSettingsRepository extends CrudRepository<GlobalSettings, Integer> {

    @Query(value = "SELECT * FROM global_settings", nativeQuery = true)
    List<GlobalSettings> getGlobalSettings();

    @Query(value = "SELECT s.value FROM global_settings as s where s.code = 'STATISTICS_IS_PUBLIC'", nativeQuery = true)
    String getPublicSetting();

    @Query(value = "SELECT s.value FROM global_settings as s where s.code = 'MULTIUSER_MODE'", nativeQuery = true)
    String getMultiuserSetting();

    @Query(value = "SELECT s.value FROM global_settings as s where s.code = 'POST_PREMODERATION'", nativeQuery = true)
    String getPremoderationSetting();
}
