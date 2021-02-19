package main;

import main.model.Case1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Storage {
    private static int currentId = 1;
    private static HashMap<Integer, Case1> cases = new HashMap<Integer, Case1>();

    public static List<Case1> getAllCases(){
        ArrayList<Case1> casesList = new ArrayList<Case1>();
        casesList.addAll(cases.values());
        return casesList;
    }

    public static int addCase(Case1 cas){
        int id = currentId++;
        cas.setId(id);
        cases.put(id, cas);
        return id;
    }

    public static Case1 putCase(int caseId, Case1 cas){
        if (cases.containsKey(caseId)) {
            cases.get(caseId).setName(cas.getName());
            cases.get(caseId).setDate(cas.getDate());
            return cases.get(caseId);
        }else {
            return null;
        }
    }

    public static Case1 getCase(int caseId){
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
