package studentCoursesMgmt.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FileProcessor implements FileDisplayInterface{

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

                try {
                    FileWriter myWriter1 = new FileWriter(regConflicts);
                    myWriter1.write("") ;
                    FileWriter myWriter2 = new FileWriter(errorLog);
                    myWriter2.write("") ;
                    // FileWriter myWriter3 = new FileWriter(regResults);
                    // myWriter3.write("") ;
                    myWriter1.close();
                    myWriter2.close();
                    // myWriter3.close();
                    System.out.println("Successfully wrote to the file.");
                } 
                catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
        }

    

    public void ReadFile(){
        String[] arraStrings ;
        try {
            File myObj = new File(courseInfo);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              ArrayList<String> AL = new ArrayList<String> (2);
              String data = myReader.nextLine();
              arraStrings = data.split(":",3) ;
              
              // x[k] = arraStrings ;
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

//    public static void Writereg_Results(ArrayList<ArrayList<String>> allCoursesAllocated, HashMap<String, Double> satHashMap, Double totalSatisfaction){
//         try {
//             FileWriter myWriter = new FileWriter(regResults);
//             for(ArrayList<String> temp: allCoursesAllocated){

//                 String key = temp.get(0) ;
//                 myWriter.write(temp.get(0) + ":") ;
//                 for(int j = 1; j< temp.size();j++){

//                     myWriter.write(temp.get(j)) ;
//                     if(j != temp.size()-1){
//                         myWriter.write(",") ;
//                     }
//                 }
//                 String s = String.format("%.6f", satHashMap.get(key)) ;
//                 myWriter.write("::SatisfactionRating="+ s + "\n") ;

//             }
//             String s = String.format("%.2f", totalSatisfaction) ;
//             myWriter.write("AverageSatisfactionRating="+s) ;
//             myWriter.close();
//             System.out.println("Successfully wrote to the file.");
//         } 
//             catch (IOException e) {
//                 System.out.println("An error occurred.");
//                 e.printStackTrace();
//             }
//     }

//     public static void WriteRegConflicts(String Course1, String Course2){
//         try {
//             String line = "Course "+Course1+" present in the same time slot as Course "+Course2+"." ;
//             FileWriter myWriter1 = new FileWriter(regConflicts, true);
//             myWriter1.write(line+"\n") ;
//             myWriter1.close();
//             System.out.println("Successfully wrote to the file.");
//         } catch (IOException e) {
//             System.out.println("An error occurred.");
//             e.printStackTrace();
//           }
//         }

//     public static void WriteErrorLog(String Course){
//         try {
//             String line = "Any further requests for Course "+Course+" will be rejected because the seats are full." ;
//             FileWriter myWriter2 = new FileWriter(errorLog, true);
//             myWriter2.write(line+"\n") ;
//             myWriter2.close();
//             System.out.println("Successfully wrote to the file.");
//         } catch (IOException e) {
//             System.out.println("An error occurred.");
//             e.printStackTrace();
//           }
//     }

    public static void Write(ArrayList<String> line, String file){
        try {
            FileWriter myWriter1 = new FileWriter(file);
            for(int i=0; i<line.size(); i++){
                myWriter1.write(line.get(i)+"\n") ;
            }
            myWriter1.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        }
    }



    // public void WriteFile(Results results){
    //     try {
    //         FileWriter myWriter = new FileWriter(regResults);
    //         for(ArrayList<String> temp:results.allCoursesAllocated){

    //             String key = temp.get(0) ;
    //             myWriter.write(temp.get(0) + ":") ;
    //             for(int j = 1; j< temp.size();j++){

    //                 myWriter.write(temp.get(j)) ;
    //                 if(j != temp.size()-1){
    //                     myWriter.write(",") ;
    //                 }
    //             }
    //             myWriter.write("::SatisfactionRating="+results.satHashMap.get(key) + "\n") ;

    //         }
    //         myWriter.write("AverageSatisfactionRating="+String.valueOf(results.totalSatisfaction)) ;
    //         myWriter.close();
    //         System.out.println("Successfully wrote to the file.");
    //     } 
    //         catch (IOException e) {
    //             System.out.println("An error occurred.");
    //             e.printStackTrace();
    //         }
    // }





    



	// void FileProcessor(){
    //     // store courseInfo.txt file into a data structure
// }
    


