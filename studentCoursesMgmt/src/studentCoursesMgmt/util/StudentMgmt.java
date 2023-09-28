package studentCoursesMgmt.util;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class StudentMgmt {

    ArrayList<String> regConflictsList = new ArrayList<String>() ;
    ArrayList<String> errorLogList = new ArrayList<String>() ;
    HashMap<String, Double> satisfactionRatingHashMap = new HashMap<String, Double>() ;
    ArrayList<String> CoursesFull = new ArrayList<String>();
    double AverageSatisfactionRating ;
    String[] pref ;
    ArrayList<ArrayList<String>> allCoursesAllocated = new ArrayList<ArrayList<String>>() ;
    ArrayList<String> CourseAlloc = new ArrayList<String>();
    String coursePrefPath ;
    HashMap<String,ArrayList<String>> courses = new HashMap<String,ArrayList<String>>() ;


    public StudentMgmt(FileProcessor FPIn){
        coursePrefPath = FPIn.coursePref ;
        courses = FPIn.courses ;
    }

    public void processPreference(){
        AverageSatisfactionRating = 0.0 ;
    
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
        AverageSatisfactionRating /= (double)allCoursesAllocated.size();
		myReader1.close();
        Results.updateResults(allCoursesAllocated, satisfactionRatingHashMap, AverageSatisfactionRating, regConflictsList, errorLogList) ;
	  } 

        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } 

    }

    public ArrayList<String> Compute(String[] preference){

        ArrayList<String> CourseAlloc1 = new ArrayList<String>();
        HashMap<String, String> timings = new HashMap<String, String>() ;

        double currentSatisfaction = 0.0 ;
        String studentId = preference[0] ;
        CourseAlloc1.add(studentId) ;
        for(int i=1; i<preference.length;i++){
            if(CourseAlloc1.size() >= 4){
                currentSatisfaction = currentSatisfaction / 3.0 ;
                satisfactionRatingHashMap.put(studentId, currentSatisfaction) ;
                AverageSatisfactionRating += currentSatisfaction ;
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
                                currentSatisfaction += ((9-i)+1) ;
                                int seat = Integer.valueOf(temp1.get(0)) ;
                                seat-- ;
                                ArrayList<String> q = new ArrayList<String>(2) ;
                                q.add(Integer.toString(seat)) ;
                                q.add(temp1.get(1)) ;
                                courses.put(preference[i],q) ;
                            }
                            else{
                                String line1 = "For Student-"+studentId+", Course "+timings.get(temp1.get(1))+" is already present in the same time slot as Course "+temp+"." ;
                                regConflictsList.add(line1);
                                // Other course present in the same time slot.
                            }
                        }
                        else{
                            String line2 = "Course "+temp+" has been filled up. Any further registration requests for this course will be rejected." ;
                            errorLogList.add(line2);
                            CoursesFull.add(temp) ;                            
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

                currentSatisfaction = currentSatisfaction / 3 ;
                satisfactionRatingHashMap.put(studentId, currentSatisfaction) ;
                AverageSatisfactionRating += currentSatisfaction ;
                return CourseAlloc1 ;

            }
            
        }
        return CourseAlloc1 ;


    }

}

