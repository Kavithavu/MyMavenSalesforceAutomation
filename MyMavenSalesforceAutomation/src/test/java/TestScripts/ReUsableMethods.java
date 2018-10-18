package TestScripts;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ReUsableMethods{
	/* 
	 * Name of the Method: enterText
	 * Brief Description: Enters text to text field
	 * Arguments: obj --> Web object, textVal --> Text value to be entered in text box, objName --> Nam eof the object
	 * Created By: Automation team
	 * Creation Date: Oct 09 2018
	 * Last Modified: Oct 09 2018 
	 */
	static ExtentHtmlReporter htmlReporter;
	static ExtentReports extent=null;
	static ExtentTest logger=null;
	public static WebDriver driver=null;

	public static void enterText(WebElement obj, String textVal, String objName) {

		if(obj.isDisplayed()) {
			obj.clear();
			obj.sendKeys(textVal);
			logger.log(Status.PASS, MarkupHelper.createLabel(textVal + " is entered in "+ objName +" field", ExtentColor.GREEN));
		}else {
			logger.log(Status.FAIL,MarkupHelper.createLabel(objName +" field does not exist, please check your application", ExtentColor.RED));
		}

	}

	/* 
	 * Name of the Method: clickObj
	 * Brief Description: click on the object
	 * Arguments: obj --> Web object, objName--> Name of the object 
	 * Created By: Automation team
	 * Creation Date: Oct 09 2018
	 * Last Modified: Oct 09 2018 
	 */

	public static void clickObj(WebElement obj, String objName) {
		if(obj.isDisplayed()) {
			obj.click();
			logger.log(Status.PASS, MarkupHelper.createLabel(objName +" is clicked", ExtentColor.GREEN));
			//System.out.println("Pass: "+ objName +" is clicked");
		}else {
			logger.log(Status.FAIL,MarkupHelper.createLabel(objName +" field does not exist, please check your application", ExtentColor.RED));
			//System.out.println("Fail: "+ objName +" Object does not exist, please check the application");
		}
	}

	public static Properties configProperty(String path) throws IOException {
		Properties pro=new Properties();
		BufferedReader reader = new BufferedReader(new FileReader(path));
		pro.load(reader);
		return pro;
	}

	public static void GetError(WebElement obj,String ExpText)
	{
		if(obj.isDisplayed())
		{
			String acttxt=obj.getText();
			logger.log(Status.PASS, MarkupHelper.createLabel(ExpText +" is displayed", ExtentColor.GREEN));
			System.out.println("Pass: " + acttxt + " is displayed");
		}
		else
		{
			System.out.println("Fail: "+ obj +"  does not exist, please check the application");
		}
	}


	public static void verifyText(WebElement element,String elementName,String expectedText ) throws IOException
	{
		if (element.isDisplayed())
		{	
			if (element.getText().equals(expectedText))
				logger.log(Status.PASS, MarkupHelper.createLabel( elementName+" is displayed as expected", ExtentColor.GREEN));
			else{
				logger.log(Status.FAIL, MarkupHelper.createLabel( elementName+"is NOT as expected", ExtentColor.RED));
				String imagePath=takeScreenShot();
				logger.addScreenCaptureFromPath(imagePath);
			}
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( elementName+" not found", ExtentColor.RED));
		}
	}

	public static void dropDown(WebElement obj,String textvalue,String objName)
	{
		Select select=new Select(obj);
		select.selectByVisibleText(textvalue);
	}

	public static void VerifySalesForcePage(String Title)
	{
		if(Title.contains("Salesforce"))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel( "Salesforce application page is Verified..", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( "Salesforce page did not verified..", ExtentColor.RED));	
		}
	}

	public static String takeScreenShot() throws IOException{
		String reportPath=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String curDir=System.getProperty("user.dir");
		String destination=curDir+"/TestReports/screenshots/"+reportPath+"image.PNG";
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(destination),true);
		return destination;
	}

	public static void executionReport(String reportName) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String timeNow = dtf.format(now);
		timeNow = timeNow.replace(":", "_");
		timeNow = timeNow.replace(" ", "_");
		timeNow = timeNow.replace("/", "_");


		System.out.println(reportName+"_"+timeNow+".html");

		htmlReporter = new ExtentHtmlReporter("C:\\Kavitha\\Udemy_Workspace\\JavaQaTraining\\MyMavenSalesforceAutomation\\TestReports\\Extent_Report_Results\\" + reportName+"_"+timeNow+".html");

		extent = new ExtentReports ();
		extent.attachReporter(htmlReporter);
	}

	public static String[][] readSheet(String dt_Path, String sheetName) throws IOException{

		/*Step 1: Get the XL Path*/
		File xlFile = new File(dt_Path);

		/*Step2: Access the Xl File*/
		FileInputStream xlDoc = new FileInputStream(xlFile);


		/*Step3: Access the work book */
		HSSFWorkbook workbook=new HSSFWorkbook(xlDoc);

		/*Step4: Access the Sheet */
		HSSFSheet sheet=workbook.getSheet(sheetName);

		int rows=sheet.getLastRowNum()+1;
		int cols=sheet.getRow(0).getLastCellNum();
		String[][] xlData = new String[rows][cols];

		for(int i=1;i<=rows;i++){
			for(int j=0;j<cols;j++){
				if(sheet.getRow(i)==null || sheet.getRow(i).getCell(j) == null) {
					continue;
				}
				//				xlData[i][j]=sheet.getRow(i).getCell(j).getStringCellValue();
				HSSFCell cell=sheet.getRow(i).getCell(j);
				if(cell.getCellType()==CellType.STRING)
					xlData[i][j]=cell.getStringCellValue();
				else if(cell.getCellType()==CellType.NUMERIC)
					xlData[i][j]=String.valueOf(cell.getNumericCellValue());
			}
		}

		return xlData;
	}

	public static void createTestScriptReport(String testScriptName){
		logger = extent.createTest(testScriptName);
//		return logger;
	}
	public static void endExtentReport(){
		extent.flush();
	}

	public static WebDriver IntializeDriver(String name){
		//System.setProperty("webdriver.firefox.bin","C:/Program Files/Mozilla Firefox53/firefox.exe");
		if(name.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver","./src/test/resources/Utility/geckodriver.exe");
			driver=new FirefoxDriver();
		}
		else if(name.equalsIgnoreCase("chrome")){
			System.out.println("chrome entered");
			System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver.exe");
			driver=new ChromeDriver();
			//driver.manage().window().maximize();
		}
		else if(name.equalsIgnoreCase("ie")){
			System.out.println("chrome entered");
			System.setProperty("webdriver.ie.driver","./src/test/resources/IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		return driver;
	}
}
