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
          if(pref.length < 4){
            continue ;
          }
		  CourseAlloc = Compute(pref) ;
          if(CourseAlloc.size() < 1){
            continue ;
          }
          allCoursesAllocated.add(CourseAlloc) ;
		}
        AverageSatisfactionRating /= (double)allCoursesAllocated.size();
		myReader1.close();
        Results.updateResults(allCoursesAllocated, satisfactionRatingHashMap, AverageSatisfactionRating, regConflictsList, errorLogList) ;
	  } 

        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(0);
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
            String currentCourse = preference[i] ; // current course
            ArrayList<String> temp1 = new ArrayList<String>() ;
            if(courses.containsKey(currentCourse))
            {
                temp1 = courses.get(currentCourse) ; // temp1 ArrayList<String> - stores seats and timings.
                String currentTiming = temp1.get(1) ;
                int seats = 0 ;
                try{
                    seats = Integer.valueOf(temp1.get(0)) ;
                }
                catch(NumberFormatException e){
                    System.out.println("Cannot convert String to Integer: " + e.getMessage()) ;
                    System.exit(0);
                }
                if(!CourseAlloc1.contains(currentCourse)){
                    if(!CoursesFull.contains(currentCourse)){
                        if(seats > 0){ // if seats > 0
                            if(!timings.containsKey(currentTiming)){ // comparing time
                                timings.put(currentTiming,currentCourse) ; // put time,course
                                CourseAlloc1.add(preference[i]) ;
                                currentSatisfaction += ((9-i)+1) ;
                                seats-- ;
                                ArrayList<String> q = new ArrayList<String>() ;
                                q.add(Integer.toString(seats)) ;
                                q.add(currentTiming) ;
                                courses.put(preference[i],q) ;
                            }
                            else{
                                String line1 = "For Student-"+studentId+", Course "+timings.get(currentTiming)+" is already present in the same time slot as Course "+currentCourse+"." ;
                                regConflictsList.add(line1);
                                // Other course present in the same time slot.
                            }
                        }
                        else{
                            String line2 = "Course "+currentCourse+" has been filled up. Any further registration requests for this course will be rejected." ;
                            errorLogList.add(line2);
                            CoursesFull.add(currentCourse) ;                            
                        }
                    }
                    else{
                        // Every request is rejected because the course is full.
                    }
                }
                else{
                    String line3 = "The Course "+currentCourse+", is already allocated for Student "+studentId+"." ;
                    errorLogList.add(line3) ;
                    // course already allocated.
                }
            }
            else{
                    String line4 = "Course "+currentCourse+", does not exist. An invalid preference given by the Student "+studentId+"." ;
                    errorLogList.add(line4) ;
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

