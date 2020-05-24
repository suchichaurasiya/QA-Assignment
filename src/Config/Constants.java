package Config;


public class Constants {

	//This is the list of System Variables
    //Declared as 'public', so that it can be used in other classes of this project
    //Declared as 'static', so that we do not need to instantiate a class object
    //Declared as 'final', so that the value of this variable can be changed
    // 'String' & 'int' are the data type for storing a type of value	
	public static final String URL = "http://automationpractice.com/index.php";
	public static final String Path_TestData = "C:\\Users\\Acro\\Documents\\suchi\\selenium\\QA_Assignment\\src\\DataEngine\\DataEngine.xlsx";
	public static final String File_TestData = "DataEngine.xlsx";
	 public static final String Path_OR = "C:\\Users\\Acro\\Documents\\suchi\\selenium\\QA_Assignment\\src\\Config\\OR.txt";

	//List of Data Sheet Column Numbers
	public static final int Col_TestCaseID = 0;	
	public static final int Col_TestScenarioID =1 ;
	public static final int Col_ActionKeyword =3 ;
	 public static final int Col_RunMode =2 ;
	 public static final int Col_Result =3 ;
	 public static final int Col_TestStepResult =5 ;
	 public static final int Col_DataSet =4;
	 public static final int Col_PageObject =6 ;

	//List of Data Engine Excel sheets
	public static final String Sheet_TestSteps = "Test Steps";
	public static final String Sheet_TestCases = "Test Cases";
	// List of Test Data
	//public static final String UserName = "suchi654chaurasiya@deh.com";
	//public static final String Password = "Test@123";
	public static final String KEYWORD_FAIL = "FAIL";
	public static final String KEYWORD_PASS = "PASS";

}