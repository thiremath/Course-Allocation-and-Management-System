package studentCoursesMgmt.util;

import java.util.ArrayList;
import java.util.HashMap;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

    static ArrayList<String> ALS3 = new ArrayList<String>() ;
    static ArrayList<String> ALS1 = new ArrayList<String>() ;
    static ArrayList<String> ALS2 = new ArrayList<String>() ;

    public static void updateResults(ArrayList<ArrayList<String>> allCoursesAllocated, HashMap<String, Double> satHashMap, Double totalSatisfaction, ArrayList<String> ALS11, ArrayList<String> ALS22){
        for(ArrayList<String> temp: allCoursesAllocated){
                String k = "" ;

                String key = temp.get(0) ;
                k+= temp.get(0) + ":" ;
                for(int j = 1; j< temp.size();j++){

                    k+= temp.get(j) ;
                    if(j != temp.size()-1){
                        k+="," ;
                    }
                }
                String s = String.format("%.2f", satHashMap.get(key)) ;
                k+= "::SatisfactionRating="+ s ;
                ALS3.add(k) ;
            }
            String s = String.format("%.2f", totalSatisfaction) ;
            ALS3.add("AverageSatisfactionRating="+s) ;
            ALS1 = ALS11 ;
            ALS2 = ALS22 ;
    }

    public static void Writeres(){
        String x = "reg_Results.txt" ;
        FileProcessor.Write(ALS3, x);
        String y = "regConflicts.txt" ;
        FileProcessor.Write(ALS1, y);
        String z = "errorLog.txt" ;
        FileProcessor.Write(ALS2, z);
    }

}

         
            
            
            