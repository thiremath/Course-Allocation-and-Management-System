package studentCoursesMgmt.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
//public class FileProcessor implements FileDisplayInterface
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
              AL.add(arraStrings[1]) ;
              AL.add(arraStrings[2]) ;
              courses.put(arraStrings[0], AL) ;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
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
            System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
    }
}
