package ExecutionEngine;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import Config.ActionKeywords;
import Config.Constants;
//import Config.SoftAssert;
import Utility.ExcelUtils;
import Utility.Log;



public class DriverScript {

 public static WebDriver driver;
 //  public static SoftAssert softAssert = new SoftAssert();
 public static Properties OR;
 public static ActionKeywords actionKeywords;
 public static String sActionKeyword;
 public static Method method[];
 public static int iTestStep;
 public static int iTestLastStep;
 public static String sTestCaseID;
 public static String sRunMode;
 public static String sData;
 public static boolean bResult;
 public static String sPageObject;

 public DriverScript() throws NoSuchMethodException, SecurityException {
  actionKeywords = new ActionKeywords();
  method = actionKeywords.getClass().getMethods();
 }

 public static void main(String[] args) throws Exception {

  String sPath = Constants.Path_TestData;
  ExcelUtils.setExcelFile(sPath);
  //Declaring String variable for storing Object Repository path
  String Path_OR = Constants.Path_OR;
  //Creating file system object for Object Repository text/property file
  FileInputStream fs = new FileInputStream(Path_OR);
  //Creating an Object of properties
  OR = new Properties(System.getProperties());
  //Loading all the properties from Object Repository property file in to OR object
  OR.load(fs);

  DOMConfigurator.configure("log4j.xml");

  DriverScript startEngine = new DriverScript();
  startEngine.execute_TestCase();
 }


 private void execute_TestCase() throws Exception {
  //This will return the total number of test cases mentioned in the Test cases sheet
  int iTotalTestCases = ExcelUtils.getRowCount(Constants.Sheet_TestCases);
  bResult = true;
  //This loop will execute number of times equal to Total number of test cases
  for (int iTestcase = 1; iTestcase <= iTotalTestCases; iTestcase++) {
   //This is to get the Test case name from the Test Cases sheet
   sTestCaseID = ExcelUtils.getCellData(iTestcase, Constants.Col_TestCaseID, Constants.Sheet_TestCases);
   //This is to get the value of the Run Mode column for the current test case
   sRunMode = ExcelUtils.getCellData(iTestcase, Constants.Col_RunMode, Constants.Sheet_TestCases);
   //This is the condition statement on RunMode value
   if (sRunMode.equals("Yes")) {
    //Only if the value of Run Mode is 'Yes', this part of code will execute
    iTestStep = ExcelUtils.getRowContains(sTestCaseID, Constants.Col_TestCaseID, Constants.Sheet_TestSteps);
    iTestLastStep = ExcelUtils.getTestStepsCount(Constants.Sheet_TestSteps, sTestCaseID, iTestStep);
    //This loop will execute number of times equal to Total number of test steps
    bResult = true;
    for (; iTestStep <= iTestLastStep; iTestStep++) {
     sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword, Constants.Sheet_TestSteps);
     sPageObject = ExcelUtils.getCellData(iTestStep, Constants.Col_PageObject, Constants.Sheet_TestSteps);
     sData = ExcelUtils.getCellData(iTestStep, Constants.Col_DataSet, Constants.Sheet_TestSteps);

     execute_Actions();

     if (bResult == false) {
      //If 'false' then store the test case result as Fail
      ExcelUtils.setCellData(Constants.KEYWORD_FAIL, iTestcase, Constants.Col_Result, Constants.Sheet_TestCases);
      //End the test case in the logs
      Log.endTestCase(sTestCaseID);
      //By this break statement, execution flow will not execute any more test step of the failed test case
      break;
     }

    }
    //This will only execute after the last step of the test case, if value is not 'false' at any step 
    if (bResult == true) {
     //Storing the result as Pass in the excel sheet
     ExcelUtils.setCellData(Constants.KEYWORD_PASS, iTestcase, Constants.Col_Result, Constants.Sheet_TestCases);
     Log.endTestCase(sTestCaseID);
    }
   }
  }
 }


 private static void execute_Actions() throws Exception {

  for (int i = 0; i < method.length; i++) {
   //This is now comparing the method name with the ActionKeyword value got from excel
   if (method[i].getName().equals(sActionKeyword)) {
    //In case of match found, it will execute the matched method
    method[i].invoke(actionKeywords, sData, sPageObject);


    if (bResult == true) {
     //If the executed test step value is true, Pass the test step in Excel sheet
     ExcelUtils.setCellData(Constants.KEYWORD_PASS, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
     break;
    } else {
     //If the executed test step value is false, Fail the test step in Excel sheet
     ExcelUtils.setCellData(Constants.KEYWORD_FAIL, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
     //In case of false, the test execution will not reach to last step of closing browser
     //So it make sense to close the browser before moving on to next test case
     ActionKeywords.closeBrowser("", "");
     break;
    }
   }
  }
 }
}