package mscMetro;

import java.util.ArrayList;

public class Line {


    private String nameLine;
    private String numberLine;
    private ArrayList<Station> stations;

    public Line(String nameLine, String numberLine){
        this.nameLine = nameLine;
        this.numberLine = numberLine;
    }

    public String getNameLine() {
        return nameLine;
    }

    public void setNameLine(String nameLine) {
        this.nameLine = nameLine;
    }

    public String getNumberLine() {
        return numberLine;
    }

    public void setNumberLine(String numberLine) {
        this.numberLine = numberLine;
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public void setStations(ArrayList<Station> stations) {
        this.stations = stations;
    }
}
