package main.service;

import main.api.request.SettingsRequest;
import main.api.response.SettingsResponse;
import main.model.GlobalSettings;
import main.repository.GlobalSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingsService {

    @Autowired
    private GlobalSettingsRepository settingsRepository;

    public SettingsResponse getGlobalSettings(){
        List<GlobalSettings> listSettings = settingsRepository.getGlobalSettings();
        SettingsResponse settingsResponse = new SettingsResponse();
        for (GlobalSettings setting: listSettings){
            if (setting.getCode().equals("MULTIUSER_MODE")){
                settingsResponse.setMultiuserMode(setting.getValue().equals("YES"));
            }
            if (setting.getCode().equals("POST_PREMODERATION")){
                settingsResponse.setPostPremoderation(setting.getValue().equals("YES"));
            }
            if (setting.getCode().equals("STATISTICS_IS_PUBLIC")){
                settingsResponse.setStatisticsIsPublic(setting.getValue().equals("YES"));
            }
        }
        return settingsResponse;
    }

    public void setGlobalSettings(SettingsRequest request){
        String multiuser = request.isMultiuser() ? "YES" : "NO";
        String premod = request.isPremoderation() ? "YES" : "NO";
        String stat = request.isStatistics() ? "YES" : "NO";

        settingsRepository.setGlobalSetting(multiuser, "MULTIUSER_MODE");
        settingsRepository.setGlobalSetting(premod, "POST_PREMODERATION");
        settingsRepository.setGlobalSetting(stat, "STATISTICS_IS_PUBLIC");
    }
}
