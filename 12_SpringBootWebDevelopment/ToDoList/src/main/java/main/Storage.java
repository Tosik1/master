package main;

import response.Case;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Storage {
    private static int currentId = 1;
    private static HashMap<Integer, Case> cases = new HashMap<Integer, Case>();

    public static List<Case> getAllCases(){
        ArrayList<Case> casesList = new ArrayList<Case>();
        casesList.addAll(cases.values());
        return casesList;
    }

    public static int addCase(Case cas){
        int id = currentId++;
        cas.setId(id);
        cases.put(id, cas);
        return id;
    }

    public static Case putCase(int caseId, Case cas){
        if (cases.containsKey(caseId)) {
            cases.get(caseId).setName(cas.getName());
            cases.get(caseId).setDate(cas.getDate());
            return cases.get(caseId);
        }else {
            return null;
        }
    }

    public static Case getCase(int caseId){
        if (cases.containsKey(caseId)){
            return cases.get(caseId);
        }
        return null;
    }

    public static void deleteCase(int caseId){
        cases.remove(caseId);
    }

    public static void deleteAllCases(){
        cases.clear();
    }
}
