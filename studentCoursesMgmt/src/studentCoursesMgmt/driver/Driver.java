package studentCoursesMgmt.driver;

import studentCoursesMgmt.util.FileProcessor;
import studentCoursesMgmt.util.Results;
// import studentCoursesMgmt.projectRunner.ProjectRunner;
/**
 * @author placeholder
 *
 */
public class Driver {
	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as argX, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */

	    if (args.length != 5 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")
				|| args[3].equals("${arg3}") || args[4].equals("${arg4}")) {

			System.err.println("Error: Incorrect number of arguments. Program accepts 5 argumnets.");
			System.exit(0);
		}
		String coursePrefFilePath = args[0];
		String courseInfoFilePath = args[1];
		String regResultsFilePath = args[2];
		String regConflictsFilePath = args[3];
		String errorLogFilePath = args[4];

		FileProcessor fileProcessor = new FileProcessor(coursePrefFilePath, courseInfoFilePath, regResultsFilePath, regConflictsFilePath, errorLogFilePath);
		fileProcessor.ReadFile();
		Results results = new Results(fileProcessor) ;
		results.processPreference() ;
		// fileProcessor.WriteFile(results);
		// System.out.println(results.allCoursesAllocated) ;
		// System.out.println(results.totalSatisfaction) ;
	}
}
