package mscMetro;

import java.util.HashMap;
import java.util.Map;

public class Station {
    private String nameStation;

    private Map<String, String> mapStation = new HashMap<>();

    public Station(String name){
        nameStation = name;
    }


    public void addCon(String numb, String name){
        mapStation.put(numb, name);
    }

    public Map<String, String> getCon(){
        return mapStation;
    }

    public String getNameStation() {
        return nameStation;
    }

    public void setNameStation(String nameStation) {
        this.nameStation = nameStation;
    }
}
