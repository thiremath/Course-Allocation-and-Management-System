all:
	ant -buildfile studentCoursesMgmt/src/build.xml clean
	ant -buildfile studentCoursesMgmt/src/build.xml all
	ant -buildfile studentCoursesMgmt/src/build.xml run -Darg0=coursePrefs.txt -Darg1=courseInfo.txt -Darg2=reg_Results.txt -Darg3=regConflicts.txt -Darg4=errorLog.txt