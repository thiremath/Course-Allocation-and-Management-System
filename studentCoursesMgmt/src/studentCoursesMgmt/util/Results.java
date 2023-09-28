package studentCoursesMgmt.util;

import java.util.ArrayList;
import java.util.HashMap;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

    static ArrayList<String> regResultsList = new ArrayList<String>() ;
    static ArrayList<String> regConflictsList = new ArrayList<String>() ;
    static ArrayList<String> errorLogList = new ArrayList<String>() ;

    public static void updateResults(ArrayList<ArrayList<String>> allCoursesAllocated, HashMap<String, Double> satisfactionRatingHashMap, Double AverageSatisfactionRating, ArrayList<String> regConflictsListIn, ArrayList<String> errorLogListIn){
        for(ArrayList<String> temp: allCoursesAllocated){
                String regResultsString = "" ;

                String key = temp.get(0) ;
                regResultsString+= temp.get(0) + ":" ;
                for(int j = 1; j< temp.size();j++){

                    regResultsString+= temp.get(j) ;
                    if(j != temp.size()-1){
                        regResultsString+="," ;
                    }
                }
                String s = String.format("%.2f", satisfactionRatingHashMap.get(key)) ;
                regResultsString+= "::SatisfactionRating="+ s ;
                regResultsList.add(regResultsString) ;
            }
            String s = String.format("%.2f", AverageSatisfactionRating) ;
            regResultsList.add("AverageSatisfactionRating="+s) ;
            regConflictsList = regConflictsListIn ;
            errorLogList = errorLogListIn ;
    }

    @Override
    public void WriteStringToFile() {
        String regResultsPath = FileProcessor.regResults ;
        FileProcessor.Write(regResultsList, regResultsPath);
        String regConflictsPath = FileProcessor.regConflicts ;
        FileProcessor.Write(regConflictsList, regConflictsPath);
    }

    @Override
    public void WriteStreamToFile() {
        String errorLogPath = FileProcessor.errorLog ;
        FileProcessor.Write(errorLogList, errorLogPath);
    }

}

         
            
            
            