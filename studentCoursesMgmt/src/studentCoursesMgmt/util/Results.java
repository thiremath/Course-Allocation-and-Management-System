package studentCoursesMgmt.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

    HashMap<String, Double> satHashMap = new HashMap<String, Double>() ;
    ArrayList<String> CoursesFull = new ArrayList<String>();
    public double totalSatisfaction ;
    String[] pref ;
    int x = 0 ;
    public ArrayList<ArrayList<String>> allCoursesAllocated = new ArrayList<ArrayList<String>>() ;
    ArrayList<String> CourseAlloc = new ArrayList<String>();
    String coursePrefPath ;
    HashMap<String,ArrayList<String>> courses = new HashMap<String,ArrayList<String>>() ;


    public Results(FileProcessor FPIn){
        coursePrefPath = FPIn.coursePref ;
        courses = FPIn.courses ;
    }

    public void processPreference(){
        totalSatisfaction = 0.0 ;
    
        try {

		File myObj1 = new File(coursePrefPath);
		Scanner myReader1 = new Scanner(myObj1);  
		while (myReader1.hasNextLine()) {
		  String data1 = myReader1.nextLine();
		  data1 = data1.replace(";","") ;
		  pref = data1.split(" ", 10) ;
		  CourseAlloc = Compute(pref) ;
          allCoursesAllocated.add(CourseAlloc) ;
		}
        totalSatisfaction /= (double)allCoursesAllocated.size();
		myReader1.close();
        FileProcessor.Writereg_Results(allCoursesAllocated, satHashMap, totalSatisfaction) ;
	  } 

        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } 

    }

    public ArrayList<String> Compute(String[] preference){

        ArrayList<String> CourseAlloc1 = new ArrayList<String>();
        HashMap<String, String> timings = new HashMap<String, String>() ;

        double sat = 0.0 ;
        CourseAlloc1.add(preference[0]) ;
        for(int i=1; i<preference.length;i++){
            if(CourseAlloc1.size() >= 4){
                sat = sat / 3.0 ;
                satHashMap.put(preference[0], sat) ;
                totalSatisfaction += sat ;
                return CourseAlloc1 ;
            }
            String temp = preference[i] ; // current course
            ArrayList<String> temp1 = new ArrayList<String>(1) ;
            if(courses.containsKey(temp))
            {
                temp1 = courses.get(temp) ; // temp1 ArrayList<String> - stores seats and timings.
                if(!CourseAlloc1.contains(temp)){
                    if(!CoursesFull.contains(temp)){
                        if(Integer.valueOf(temp1.get(0)) > 0){ // if seats > 0
                            if(!timings.containsKey(temp1.get(1))){ // comparing time
                                timings.put(temp1.get(1),temp) ; // put time,course
                                CourseAlloc1.add(preference[i]) ;
                                sat += ((9-i)+1) ;
                                int seat = Integer.valueOf(temp1.get(0)) ;
                                seat-- ;
                                ArrayList<String> q = new ArrayList<String>(2) ;
                                q.add(Integer.toString(seat)) ;
                                q.add(temp1.get(1)) ;
                                courses.put(preference[i],q) ;
                            }

                            else{
                                // Write to regConflicts.txt
                                //System.out.println("Course "+timings.get(temp1.get(1))+"present in the same time slot as Course "+temp+".");
                                FileProcessor.WriteRegConflicts(timings.get(temp1.get(1)), temp) ;
                                // Other course present in the same time slot.
                            }
                        }

                        else{
                            // Write to errorLog.txt
                            //System.out.println("Any further requests for Course "+temp+" will be rejected because the seats are full.");
                            CoursesFull.add(temp) ;
                            FileProcessor.WriteErrorLog(temp) ;
                            
                        }
                    }

                    else{
                        
                        // course is full
                    }
                }

                else{
                    
                    // course already allocated.
                }


            }

            else{
                 // course not present
            }
        
            if(i == preference.length-1 ){

                sat = sat / 3 ;
                satHashMap.put(preference[0], sat) ;
                totalSatisfaction += sat ;
                return CourseAlloc1 ;

            }
            
        }
        return CourseAlloc1 ;


    }

}
