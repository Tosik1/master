package main.api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SettingsRequest {

    @JsonProperty("MULTIUSER_MODE")
    private boolean multiuser;

    @JsonProperty("POST_PREMODERATION")
    private boolean premoderation;

    @JsonProperty("STATISTICS_IS_PUBLIC")
    private boolean statistics;

    public boolean isMultiuser() {
        return multiuser;
    }

    public void setMultiuser(boolean multiuser) {
        this.multiuser = multiuser;
    }

    public boolean isPremoderation() {
        return premoderation;
    }

    public void setPremoderation(boolean premoderation) {
        this.premoderation = premoderation;
    }

    public boolean isStatistics() {
        return statistics;
    }

    public void setStatistics(boolean statistics) {
        this.statistics = statistics;
    }
}
