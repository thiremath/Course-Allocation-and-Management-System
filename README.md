# CSX42: Assignment 1
## Name: Tejas Ravindra Hiremath

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on the project.
#### Note: build.xml is present in studentCoursesMgmt/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile studentCoursesMgmt/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile studentCoursesMgmt/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile studentCoursesMgmt/src/build.xml run -Darg0=<input_file.txt> -Darg1=<delete_file.txt> -Darg2=<output1_file.txt> -Darg3=<output2_file.txt> -Darg4=<output3_file.txt>


Replace <fileName.txt> with real file names. For example, if the files are available in the path,
you can run it in the following manner:

### Command: 
ant -buildfile studentCoursesMgmt/src/build.xml run -Darg0=coursePrefs.txt -Darg1=courseInfo.txt -Darg2=reg_Results.txt -Darg3=regConflicts.txt -Darg4=errorLog.txt


Note: Arguments accept the absolute path of the files.

-----------------------------------------------------------------------
## Description:
The Program assigns courses to students based on their preferences. It accepts 5 text file inputs- coursePrefs.txt, courseInfo.txt, reg_Results.txt, regConflicts.txt and errorLog.txt, where the coursePrefs.txt file represents the student ID corresponding to preferences of students for 9 different courses namely from A to I. The courseInfo.txt file represents information about 9 different courses, which include the number of seats in a course and timing for a course. The reg_Results.txt file is an output file from this program which contains allocated courses for each student, their Individual satisfaction rating- from 0 to 9 and the overall Average Satisfaction Rating which is the Average of all individual satisfaction ratings. The regConflicts.txt file is also an output file, which logs any error if a student is already allocated a course which is in the same time slot as the requested course. The Error is logged because the request is rejected for a particular course in the same time slot. The errorLog.txt file is also an output file, which logs that a course is filled up and any further requests for that particular course will be rejected.

## Input:
Example of coursePrefs.txt file:
111 D C A B G I H F G;
222 F E D C B A H I G;
333 D A F E I C H B G;

Example of courseInfo.txt file:
A:30:11
B:40:22
C:50:33
D:60:11
E:70:77
F:35:44
G:45:33
H:55:77
I:25:66

## Output:
The registration_results.txt will have the following format:

<student1_id>:<course_1>,<course_2>,<course_3>::SatisfactionRating=<value>
<student2_id>:<course_1>,<course_2>,<course_3>::SatisfactionRating=<value>
<student3_id>:<course_1>,<course_2>,<course_3>::SatisfactionRating=<value>
...
AverageSatisfactionRating=<value>


The registration_results.txt will have the following format if students are allocated three courses, two courses, one course and zero course allocated:

<student1_id>:<course_1>,<course_2>,<course_3>::SatisfactionRating=<value>
<student2_id>:<course_1>,<course_2>::SatisfactionRating=<value>
<student3_id>:<course_1>::SatisfactionRating=<value>
<student4_id>:::SatisfactionRating=<value>
...
AverageSatisfactionRating=<value>

-----------------------------------------------------------------------
### Quality of the Solution: 
Algorithm Used: This program uses the brute force algorithm, which is a direct and straightforward technique for solving a particular problem. The StudentMgmt.java file has the algorithm- the Compute function which takes preference for every student line by line as input and assigns courses to students on the basis of FCFS(First Come First Serve). Students who gave the preferences first, get higher priority than others. As the courses get full and the student preferences are exausted, a particular student can be assigned with three, two, One or Zero Courses.

Although it is a guranteed way to solve the problem, this algorithm remains inefficient. For limited number of courses and large number of students, the Average Satisfaction Rating always remains low. 

Time Complexity: Iterating over every student's preferences is a constant amount of time as the inner loop will always run 10 times. The outer loop depends on the number of students-N. As this algorithm store the Course information in a hashMap, the searching time complexity always remains O(1). Therefore, the Overall Time Complexity of this algorithm is O(N), where N is the number of students.

### Data Structures used: 

This Algorithm uses ArrayList and HashMap Data Structures. 

The Compute function uses Array(String[]) N number of times, it is reused to store and iterate over the preferences of students one by one. We used Array here, because we just need to iterate over the preferences one by one and we don't want to use any other methods. We know the length of Array which is atmost 10 i.e constant time- O(1) operation.

The Compute function ArrayList(ArrayList<String>), which is found in java.util.ArrayList package to store the results i.e courses allocated for every student. The ArrayLists have many useful methods such as add(), get() which is used to add and item and get a paricular item at/from any index. These methods are constant time operation i.e O(1) time complexity. It provides flexibility over Arrays to both add and remove elements.

The Algorithm also uses HashMap(HashMap<Integer,ArrayList<String>>), which is found in java.util.HashMap package to store the Course Information which contains- (key)-Course Names,(Value)- Number of seats and timings of different courses. The HashMap is useful to find details about any particular course. It offers constant-time performance i.e O(1) time complexity for many basic operations such as get() and put(),the find and insert operations, which are frequently used in throughout the Algorithm. It also provides flexibility in storing any type of data structure as Key-Value Pair. 
-----------------------------------------------------------------------

### Purpose of all classes

### Driver:
    It contains the main method and accepts 5 String arguments through command line. It validates the number of arguments passed, creates the Instance of fileProcessor.java class and calls the ReadFile method. It then creates an instance of StudentMgmt.java class, which contains the algorithm and calls the processPreference function of it. Finally it creates an instance of results.java class and calls two methods- WriteStringToFile and WriteStreamToFile of it.

### FileProcessor:
    The FileProcessor class reads and writes from/to the input/output files.

### StudentMgmt:
    This class has two methods- processPreference, which iterates over every student preference line by line and calls the Compute method. The Compute method takes the preference input and uses the brute force algorithm to allocate courses.

### Results:
    This class has method- updateResults, which stores the result in a member variable of this class. The class also overrides two methods- WriteStringToFile and WriteStreamToFile which calls the FileProcessor Write method.

### FileDisplayInterface:
    The FileDispayInterface class is an interface which has the WriteStringToFile method signature in it.

### StdoutDisplayInterface:
    The StdoutDisplayInterface class is an interface which has the WriteStreamToFile method signature in it.

-----------------------------------------------------------------------
### No Slack Days used for the Assignment.
-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense.""

Date: -- 09/28/2023


