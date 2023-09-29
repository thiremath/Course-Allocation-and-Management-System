package studentCoursesMgmt.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FileProcessor{

    HashMap<String,ArrayList<String>> courses = new HashMap<String,ArrayList<String>>() ;
    String courseInfo;
    String coursePref;
    static String regResults;
    static String regConflicts;
    static String errorLog;
    
    public FileProcessor(String coursePrefFilePathIn, String courseInfoFilePathIn, String regResultsFilePathIn,
            String regConflictsFilePathIn, String errorLogFilePathIn) {
                courseInfo = courseInfoFilePathIn;
                coursePref = coursePrefFilePathIn;
                regResults = regResultsFilePathIn;
                regConflicts = regConflictsFilePathIn;
                errorLog = errorLogFilePathIn;

                File fileregResults = new File(regResults); 
                File fileregConflicts = new File(regConflicts) ;
                File fileerrorLog = new File(errorLog) ;
                // deleting the output files.
                if (fileregResults.delete()) { 
                    System.out.println("Deleted the file: " + fileregResults.getName());
                }
                if (fileregConflicts.delete()) { 
                    System.out.println("Deleted the file: " + fileregConflicts.getName());
                }
                if (fileerrorLog.delete()) { 
                    System.out.println("Deleted the file: " + fileerrorLog.getName());
                }
    }

    public void ReadFile(){
        String[] arraStrings ;
        try {
            File myObj = new File(courseInfo);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              ArrayList<String> AL = new ArrayList<String>();
              String data = myReader.nextLine();
              arraStrings = data.split(":",3) ;
              if(arraStrings.length != 3){
                continue ;
              }
              AL.add(arraStrings[1]) ;
              AL.add(arraStrings[2]) ;
              courses.put(arraStrings[0], AL) ;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to read the file.");
            e.printStackTrace();
            System.exit(0);
          }
    }


    public static void Write(ArrayList<String> line, String file){
        try {
            FileWriter myWriter1 = new FileWriter(file);
            for(int i=0; i<line.size(); i++){
                myWriter1.write(line.get(i)) ;
                if(i != line.size()-1){
                    myWriter1.write("\n") ;
                }
            }
            myWriter1.close();
            System.out.println("Successfully wrote to the file "+file+".");
            } catch (IOException e) {
                System.out.println("Unable to Write to the file.");
                e.printStackTrace();
                System.exit(0);
            }
    }
}
