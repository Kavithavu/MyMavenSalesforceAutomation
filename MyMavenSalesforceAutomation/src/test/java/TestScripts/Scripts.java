package TestScripts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.gargoylesoftware.htmlunit.javascript.host.media.webkitMediaStream;

public class Scripts extends ReUsableMethods{

	public static void Login_Error_Message_1() throws IOException
	{
		Properties pro=configProperty("./src/test/resources/Utility/Configuration.properties");
		driver=IntializeDriver(pro.getProperty("browser"));
		createTestScriptReport("Login_Error_Message_1");


		System.out.println("Pass: Firefox driver is launched");
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(pro.getProperty("salesforceUrl"));

		String path="C:\\Kavitha\\Udemy_Workspace\\JavaQaTraining\\SeleniumAutomation\\TestData\\Login_Error_Message_1.xls";
		String uname=null;
		String pawd=null;
		String expData=null;
		String[][] data=readSheet(path, "Sheet1");
		for(int i=1;i<data.length;i++)
		{
			uname=data[i][0];
			pawd=data[i][1];
			expData=data[i][2];
		}

		String Title=driver.getTitle();
		VerifySalesForcePage(Title);

		/*Enter username to username field*/
		WebElement un = driver.findElement(By.xpath("//input[@id='username']"));
		enterText(un, uname, "userName");

		/*Enter password to password field*/
		WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
		pwd.clear();

		/*Click login*/
		WebElement login = driver.findElement(By.xpath("//input[@id='Login']")) ;
		clickObj(login, "Login Button");

		/*Get Error msg*/
		WebElement actuaError=driver.findElement(By.xpath("//div[@id='error']"));
		verifyText(actuaError,"error message",expData);

		driver.close();

	}


	public static void Login_To_SalesForce_2() throws IOException, InterruptedException {

		Properties pro=configProperty("./src/test/resources/Utility/Configuration.properties");
		driver=IntializeDriver(pro.getProperty("browser"));
		
		createTestScriptReport("Login_To_SalesForce_2");

		System.out.println("Pass: Firefox driver is launched");
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(pro.getProperty("salesforceUrl"));

		String path="C:\\Kavitha\\Udemy_Workspace\\JavaQaTraining\\SeleniumAutomation\\TestData\\Login_To_SalesForce_2.xls";
		String uname=null;
		String pawd=null;
		String ExpData=null;

		String[][] data=readSheet(path, "Sheet1");
		for(int i=1;i<data.length;i++)
		{
			uname=data[i][0];
			pawd=data[i][1];
			ExpData=data[i][2];
		}

		String Title=driver.getTitle();
		VerifySalesForcePage(Title);

		driver.manage().window().maximize();


		/*Enter username to username field*/
		WebElement un = driver.findElement(By.xpath("//input[@id='username']"));
		enterText(un, uname, "userName");

		/*Enter password to password field*/
		WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
		enterText(pwd, pawd, "Password");

		/*Click login*/
		WebElement login = driver.findElement(By.xpath("//input[@id='Login']")) ;
		clickObj(login, "Login Button");

		Thread.sleep(5000);
		String ActTxt=driver.getTitle();

		if(ActTxt.contains(ExpData))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel( "Salesforce Home page is Verified..", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( "Salesforce Home page verfication failed..", ExtentColor.RED));	
		}

		driver.close();
	}

	public static void Check_RemeberMe_3() throws InterruptedException, IOException
	{

		Properties pro=configProperty("./src/test/resources/Utility/Configuration.properties");
		driver=IntializeDriver(pro.getProperty("browser"));
		
		createTestScriptReport("Check_RemeberMe_3");

		System.out.println("Pass: Firefox driver is launched");
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(pro.getProperty("salesforceUrl"));

		String path="C:\\Kavitha\\Udemy_Workspace\\JavaQaTraining\\SeleniumAutomation\\TestData\\Check_RemeberMe_3.xls";


		String uname=null;
		String pawd=null;

		String[][] data=readSheet(path, "Sheet1");
		for(int i=1;i<data.length;i++)
		{
			uname=data[i][0];
			pawd=data[i][1];
		}

		String Title=driver.getTitle();
		VerifySalesForcePage(Title);
		driver.manage().window().maximize();

		/*Enter username to username field*/
		WebElement un = driver.findElement(By.xpath("//input[@id='username']"));
		enterText(un, uname, "userName");

		/*Enter password to password field*/
		WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
		enterText(pwd, pawd, "Password");

		/*Check Remember me*/
		WebElement checkbox=driver.findElement(By.xpath("//input[@id='rememberUn']"));
		clickObj(checkbox, "RememberMe");

		/*Click login*/
		WebElement login = driver.findElement(By.xpath("//input[@id='Login']")) ;
		clickObj(login, "Login Button");

		/*logout from usermenu dropdown*/

		WebElement userMenu=driver.findElement((By.xpath("//div[@id='userNav-arrow']")));
		clickObj(userMenu, "userdropdown");

		WebElement logout=driver.findElement(By.xpath("//a[text()='Logout']"));
		clickObj(logout, "Logout");

		Thread.sleep(5000);
		String Expdata=uname;

		WebElement username=driver.findElement((By.xpath("//*[@id='idcard-identity']")));
		verifyText(username, "UserName", Expdata);

		WebElement chkbox=driver.findElement(By.xpath("//input[@id='rememberUn']"));
		if(chkbox.isSelected())
		{
			logger.log(Status.PASS, MarkupHelper.createLabel( "Remember me checkbox is checked..", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( "Remember me checkbox is not checked...", ExtentColor.RED));	
		}

		driver.close();
	}

	public static void Forgot_Password_4_A() throws InterruptedException, IOException
	{
		Properties pro=configProperty("./src/test/resources/Utility/Configuration.properties");
		driver=IntializeDriver(pro.getProperty("browser"));
		
		createTestScriptReport("Forgot_Password_4_A");

		System.out.println("Pass: Firefox driver is launched");
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.get(pro.getProperty("salesforceUrl"));

		String path="C:\\Kavitha\\Udemy_Workspace\\JavaQaTraining\\SeleniumAutomation\\TestData\\Forgot_Password_4_A.xls";

		String Title=driver.getTitle();
		VerifySalesForcePage(Title);
		driver.manage().window().maximize();

		WebElement forgotpwd=driver.findElement(By.xpath("//a[@id='forgot_password_link']"));
		clickObj(forgotpwd, "ForgotPassword");

		String pwd=driver.getTitle();
		if(pwd.contains("Forgot Your Password"))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel( "Forgot Password page is Verified..", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( "Forgot Password page did not verified..", ExtentColor.RED));	
		}

		String uname1=null;

		String[][] data=readSheet(path, "Sheet1");
		for(int i=1;i<data.length;i++)
		{
			uname1=data[i][0];
			//			pawd=data[i][1];
		}

		WebElement uname=driver.findElement(By.xpath("//input[@id='un']"));
		enterText(uname, uname1, "UserEmail");

		WebElement continuebtn=driver.findElement(By.xpath("//input[@id='continue']"));
		clickObj(continuebtn, "ContinueButton");

		driver.close();
	}


	public static void ValidateLoginErrorMessage_5() throws IOException
	{

		Properties pro=configProperty("./src/test/resources/Utility/Configuration.properties");
		driver=IntializeDriver(pro.getProperty("browser"));

		createTestScriptReport("ValidateLoginErrorMessage_5");

		System.out.println("Pass: Firefox driver is launched");
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(pro.getProperty("salesforceUrl"));

		String path="C:\\Kavitha\\Udemy_Workspace\\JavaQaTraining\\SeleniumAutomation\\TestData\\ValidateLoginErrorMessage_5.xls";


		String uname=null;
		String pawd=null;

		String[][] data=readSheet(path, "Sheet1");
		for(int i=1;i<data.length;i++)
		{
			uname=data[i][0];
			pawd=data[i][1];
		}

		String Title=driver.getTitle();
		VerifySalesForcePage(Title);
		driver.manage().window().maximize();

		/*Enter username to username field*/
		WebElement un = driver.findElement(By.xpath("//input[@id='username']"));
		enterText(un, uname, "userName");

		/*Enter password to password field*/
		WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
		enterText(pwd, pawd,  "Password");

		/*Click login*/
		WebElement login = driver.findElement(By.xpath("//input[@id='Login']")) ;
		clickObj(login, "Login Button");

		WebElement ErrorMsg=driver.findElement(By.xpath("//div[@id='error']"));
		String exptext="Please check your username and password. If you still can't log in, contact your Salesforce administrator.";

		verifyText(ErrorMsg, "Error Msg", exptext);

		driver.close();
	}

	public static void TC05() throws IOException
	{

		Properties pro=configProperty("./src/test/resources/Utility/Configuration.properties");
		driver=IntializeDriver(pro.getProperty("browser"));
		
		createTestScriptReport("TC05");

		System.out.println("Pass: Firefox driver is launched");
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(pro.getProperty("salesforceUrl"));

		String path="C:\\Kavitha\\Udemy_Workspace\\JavaQaTraining\\SeleniumAutomation\\TestData\\TC05.xls";


		String uname=null;
		String pawd=null;

		String[][] data=readSheet(path, "Sheet1");
		for(int i=1;i<data.length;i++)
		{
			uname=data[i][0];
			pawd=data[i][1];
		}

		String[] options=new String[]{"My Profile","My Settings","Developer Console","Logout"};
		List<String> expList=new ArrayList<String>
		(Arrays.asList(options));

		System.out.println("Pass: Firefox driver is launched");

		String Title=driver.getTitle();
		VerifySalesForcePage(Title);

		driver.manage().window().maximize();

		/*Enter username to username field*/
		WebElement un = driver.findElement(By.xpath("//input[@id='username']"));
		enterText(un, uname, "userName");

		/*Enter password to password field*/
		WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
		enterText(pwd, pawd, "Password");

		/*Click login*/
		WebElement login = driver.findElement(By.xpath("//input[@id='Login']")) ;
		clickObj(login, "Login Button");

		/*User Menu Displayed*/
		WebElement usermenu=driver.findElement(By.xpath("//*[@id='userNav-arrow']"));
		clickObj(usermenu, "UserMenuTab");

		List<WebElement> list=driver.findElements(By.xpath("//*[@id='userNav-menuItems']/a"));
		//		System.out.println(list);
		ArrayList<String> actData=new ArrayList<String>();
		for(WebElement ele:list){
			actData.add(ele.getText().trim());
		}
		//		System.out.println(actData);
		if(actData.containsAll(expList))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel( "USer DropDown list items is Verified..", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( "User DropDown list items did not verified..", ExtentColor.RED));	
		}

		driver.close();
	}

	public static void TC06() throws InterruptedException, IOException
	{
		Properties pro=configProperty("./src/test/resources/Utility/Configuration.properties");
		driver=IntializeDriver(pro.getProperty("browser"));

		createTestScriptReport("TC06");

		System.out.println("Pass: Firefox driver is launched");
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(pro.getProperty("salesforceUrl"));

		String path="C:\\Kavitha\\Udemy_Workspace\\JavaQaTraining\\SeleniumAutomation\\TestData\\TC06.xls";


		String uname=null;
		String pawd=null;

		String[][] data=readSheet(path, "Sheet1");
		for(int i=1;i<data.length;i++)
		{
			uname=data[i][0];
			pawd=data[i][1];
		}

		String[] options=new String[]{"My Profile","My Settings","Developer Console","Logout"};
		List<String> expList=new ArrayList<String>(Arrays.asList(options));

		System.out.println("Pass: Firefox driver is launched");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://login.salesforce.com/?locale=in");
		//		System.out.println("Pass: SFDC login page is launched..");

		String Title=driver.getTitle();
		VerifySalesForcePage(Title);
		driver.manage().window().maximize();

		/*Enter username to username field*/
		WebElement un = driver.findElement(By.xpath("//input[@id='username']"));
		enterText(un, uname, "userName");

		/*Enter password to password field*/
		WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
		enterText(pwd, pawd, "Password");

		/*Click login*/
		WebElement login = driver.findElement(By.xpath("//input[@id='Login']")) ;
		clickObj(login, "Login Button");

		/*User Menu Displayed*/
		WebElement usermenu=driver.findElement(By.xpath("//*[@id='userNav-arrow']"));
		clickObj(usermenu, "UserMenuTab");

		List<WebElement> list=driver.findElements(By.xpath("//*[@id='userNav-menuItems']/a"));
		//		System.out.println(list);
		ArrayList<String> actData=new ArrayList<String>();
		for(WebElement ele:list){
			actData.add(ele.getText().trim());
		}
		//		System.out.println(actData);
		if(actData.containsAll(expList))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel( "USer DropDown list items is Verified..", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( "User DropDown list items did not verified..", ExtentColor.RED));	
		}

		WebElement myProfile=driver.findElement(By.linkText("My Profile"));
		clickObj(myProfile, "MyProfile Tab");

		Thread.sleep(5000);

		//		WebElement feedtab=driver.findElement(By.xpath("profileTab_sfdc.ProfilePlatformFeed"));
		//		if(feedtab.isDisplayed())
		//		{
		//			logger.log(Status.PASS, MarkupHelper.createLabel( "USer Profile Page is Verified..", ExtentColor.GREEN));
		//		}
		//		else
		//		{
		//			logger.log(Status.FAIL, MarkupHelper.createLabel( "USer Profile Page did not verified..", ExtentColor.RED));	
		//		}

		Thread.sleep(5000);
		WebElement edit=driver.findElement(By.xpath("//a[@class='contactInfoLaunch editLink']//img[@title='Edit Profile']"));
		clickObj(edit, "Edit");

		driver.switchTo().frame("contactInfoContentId");
		Thread.sleep(6000);
		WebElement about=driver.findElement((By.linkText("About")));
		//		about.click();
		clickObj(about, "About Tab");
		Thread.sleep(6000);
		WebElement lastname=driver.findElement(By.xpath("//input[@id='lastName']"));
		enterText(lastname, "abc", "LastName");
		//		Thread.sleep(6000);
		WebElement saveall=driver.findElement((By.xpath("//input[@class='zen-btn zen-primaryBtn zen-pas']")));
		clickObj(saveall, "Save All");
		Thread.sleep(3000);

		//POST 
		driver.switchTo().defaultContent();
		WebElement postbtn=driver.findElement(By.xpath("//span[text()='Post']"));
		Thread.sleep(3000);
		postbtn.click();
		clickObj(postbtn, "Post");
		Thread.sleep(6000);
		WebElement frame=driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']"));
		Thread.sleep(10000);
		driver.switchTo().frame(frame);
		Thread.sleep(10000);
		WebElement postMessage=driver.findElement(By.xpath("/html/body"));
		enterText(postMessage, "This is qa", "Post Message");
		//		postMessage.sendKeys("This is using Automation");
		driver.switchTo().defaultContent();
		WebElement sharebtn=driver.findElement(By.xpath("//input[@title='Share']"));
		//		sharebtn.click();
		clickObj(sharebtn, "Share Button");

		//FILE
		Thread.sleep(3000);
		WebElement filelink=driver.findElement((By.xpath("//span[text()='File']")));
		//		filelink.click();
		clickObj(filelink, "File Link");
		Thread.sleep(4000);
		WebElement upload=driver.findElement((By.xpath("//a[text()='Upload a file from your computer']")));
		//		upload.click();
		clickObj(upload, "Upload");
		Thread.sleep(6000);
		WebElement browse=driver.findElement(By.xpath((".//*[@id='chatterFile']")));
		//		browse.sendKeys("C:\\Kavitha\\data1.txt");
		enterText(browse, "C:\\Kavitha\\data1.txt", "Path ");
		WebElement share=driver.findElement(By.xpath(".//*[@id='publishersharebutton']"));
		//		share.click();
		clickObj(share, "Share");
		Thread.sleep(5000);

		//ADD PHOTO
		Actions mousehover=new Actions(driver);
		mousehover.moveToElement(driver.findElement(By.xpath("//*[text()='Moderator']"))).perform();
		WebElement uploadphoto=driver.findElement((By.xpath("//a[@id='uploadLink']")));
		//		uploadphoto.click();
		clickObj(uploadphoto, "Upload Photo");
		Thread.sleep(5000);
		driver.switchTo().frame("uploadPhotoContentId");
		WebElement browsephoto=driver.findElement(By.xpath("//input[@id='j_id0:uploadFileForm:uploadInputFile']"));
		//		browsephoto.click();
		//		browsephoto.sendKeys("C:\\Users\\kavit\\OneDrive\\Pictures\\Saved Pictures\\newflower.jpg");
		enterText(browsephoto, "C:\\Users\\kavit\\OneDrive\\Pictures\\Saved Pictures\\newflower.jpg", "Browse Photo");
		Thread.sleep(5000);
		WebElement savePhoto=driver.findElement(By.xpath(".//*[@id='j_id0:uploadFileForm:uploadBtn']"));
		//		savePhoto.click();
		clickObj(savePhoto, "Save Photo");

		Actions action=new Actions(driver); 
		action.dragAndDropBy(driver.findElement(By.xpath(".//*[@id='j_id0:outer']/div[1]/div/div/div/div[6]")), 100, 20);
		driver.findElement(By.xpath(".//*[@id='j_id0:j_id7:save']")).click(); 

		driver.switchTo().defaultContent();
		driver.close();
	}

	public static void TC07() throws IOException, InterruptedException, AWTException
	{

		Properties pro=configProperty("./src/test/resources/Utility/Configuration.properties");
		driver=IntializeDriver(pro.getProperty("browser"));

		createTestScriptReport("TC07");

		System.out.println("Pass: Firefox driver is launched");
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(pro.getProperty("salesforceUrl"));

		String path="C:\\Kavitha\\Udemy_Workspace\\JavaQaTraining\\SeleniumAutomation\\TestData\\TC07.xls";


		String uname=null;
		String pawd=null;

		String[][] data=readSheet(path, "Sheet1");
		for(int i=1;i<data.length;i++)
		{
			uname=data[i][0];
			pawd=data[i][1];
		}
		String[] options=new String[]{"My Profile","My Settings","Developer Console","Logout"};
		List<String> expList=new ArrayList<String>(Arrays.asList(options));

		System.out.println("Pass: Firefox driver is launched");

		String Title=driver.getTitle();
		VerifySalesForcePage(Title);

		driver.manage().window().maximize();

		/*Enter username to username field*/
		WebElement un = driver.findElement(By.xpath("//input[@id='username']"));
		enterText(un, uname, "userName");

		/*Enter password to password field*/
		WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
		enterText(pwd, pawd, "Password");

		/*Click login*/
		WebElement login = driver.findElement(By.xpath("//input[@id='Login']")) ;
		clickObj(login, "Login Button");

		/*User Menu Displayed*/
		WebElement usermenu=driver.findElement(By.xpath("//*[@id='userNav-arrow']"));
		clickObj(usermenu, "UserMenuTab");

		List<WebElement> list=driver.findElements(By.xpath("//*[@id='userNav-menuItems']/a"));
		//		System.out.println(list);
		ArrayList<String> actData=new ArrayList<String>();
		for(WebElement ele:list){
			actData.add(ele.getText().trim());
		}
		//		System.out.println(actData);
		if(actData.containsAll(expList))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel( "USer DropDown list items is Verified..", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( "User DropDown list items did not verified..", ExtentColor.RED));	
		}

		WebElement mySettings=driver.findElement(By.linkText("My Settings"));
		clickObj(mySettings, "MySettings");

		WebElement personalLink=driver.findElement(By.xpath("	//span[@id='PersonalInfo_font']"));
		clickObj(personalLink, "Personal Link");

		WebElement  loginHistory=driver.findElement(By.xpath("//span[@id='LoginHistory_font']"));
		clickObj(loginHistory, "Login History");

		Thread.sleep(5000);
		WebElement download=driver.findElement(By.xpath("//a[contains(text(),'Download login history for last six months, includ')]"));
		clickObj(download, "Download");

		Thread.sleep(3000);


		String oldwindow=driver.getWindowHandle();
		for(String handles:driver.getWindowHandles())
		{
			if(!handles.equals(oldwindow))
			{
				driver.switchTo().window(handles);
			}
		}

		Robot rb=new Robot();
		rb.keyPress(KeyEvent.VK_DOWN);
		rb.keyRelease(KeyEvent.VK_DOWN);
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);

		driver.switchTo().window(oldwindow);
		Thread.sleep(5000);

		WebElement displayAndLayout=driver.findElement(By.xpath("//span[@id='DisplayAndLayout_font']"));
		clickObj(displayAndLayout, "DisplayAndLayOut");

		WebElement customizeMyTabs=driver.findElement(By.xpath("//span[@id='CustomizeTabs_font']"));
		clickObj(customizeMyTabs, "CustomizeMytabs");

		WebElement customApp=driver.findElement(By.xpath("//select[@id='p4']"));
		clickObj(customApp, "CustomApp");
		Select select=new Select(customApp);
		select.selectByVisibleText("Salesforce Chatter");


		WebElement listtab=driver.findElement(By.xpath(".//*[@id='duel_select_0']"));
		Select newselect=new Select(listtab);
		newselect.selectByVisibleText("Reports");

		WebElement addObj=driver.findElement(By.xpath(".//*[@id='duel_select_0_right']/img"));
		addObj.click();

		WebElement emailObj=driver.findElement(By.xpath(".//*[@id='EmailSetup_font']"));
		emailObj.click();

		WebElement emailsettings=driver.findElement(By.xpath(".//*[@id='EmailSettings_font']"));
		emailsettings.click();

		WebElement userName=driver.findElement(By.xpath(".//*[@id='sender_name']"));
		userName.sendKeys("kavitha");

		WebElement emailid=driver.findElement(By.xpath(".//*[@id='sender_email']"));
		emailid.sendKeys("kavithavuppin@gmail.com");

		WebElement rdBtn=driver.findElement(By.xpath(".//*[@id='auto_bcc1']"));

		WebElement saveBtn=driver.findElement(By.xpath(".//*[@id='bottomButtonRow']/input[1]"));

		if(rdBtn.isSelected())
			saveBtn.click();
		else
		{
			rdBtn.click();
			saveBtn.click();
		}

		WebElement calandRemind=driver.findElement(By.xpath(".//*[@id='CalendarAndReminders_font']"));
		calandRemind.click();

		WebElement remind=driver.findElement(By.xpath(".//*[@id='Reminders_font']"));
		remind.click();

		WebElement testReminder=driver.findElement(By.xpath(".//*[@id='testbtn']"));
		testReminder.click();

		Robot robo=new Robot();
		robo.keyPress(KeyEvent.VK_ESCAPE);
		robo.keyRelease(KeyEvent.VK_ESCAPE);

		Thread.sleep(4000);

		driver.close();
		logger.log(Status.PASS,MarkupHelper.createLabel("passed",ExtentColor.GREEN));
	}

	public static void TC08() throws IOException
	{
		Properties pro=configProperty("./src/test/resources/Utility/Configuration.properties");
		driver=IntializeDriver(pro.getProperty("browser"));

		createTestScriptReport("TC08");

		System.out.println("Pass: Firefox driver is launched");
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(pro.getProperty("salesforceUrl"));

		String path="C:\\Kavitha\\Udemy_Workspace\\JavaQaTraining\\SeleniumAutomation\\TestData\\TC08.xls";


		String uname=null;
		String pawd=null;

		String[][] data=readSheet(path, "Sheet1");
		for(int i=1;i<data.length;i++)
		{
			uname=data[i][0];
			pawd=data[i][1];
		}
		String[] options=new String[]{"My Profile","My Settings","Developer Console","Logout"};
		List<String> expList=new ArrayList<String>
		(Arrays.asList(options));

		System.out.println("Pass: Firefox driver is launched");
		//		System.out.println("Pass: SFDC login page is launched..");
		String Title=driver.getTitle();
		if(Title.contains("Salesforce"))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel( "Salesforce application page is Verified..", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( "Salesforce page did not verified..", ExtentColor.RED));	
		}
		driver.manage().window().maximize();

		/*Enter username to username field*/
		WebElement un = driver.findElement(By.xpath("//input[@id='username']"));
		enterText(un, uname, "userName");

		/*Enter password to password field*/
		WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
		enterText(pwd, pawd, "Password");

		/*Click login*/
		WebElement login = driver.findElement(By.xpath("//input[@id='Login']")) ;
		clickObj(login, "Login Button");

		/*User Menu Displayed*/
		WebElement usermenu=driver.findElement(By.xpath("//*[@id='userNav-arrow']"));
		clickObj(usermenu, "UserMenuTab");

		List<WebElement> list=driver.findElements(By.xpath("//*[@id='userNav-menuItems']/a"));
		//		System.out.println(list);
		ArrayList<String> actData=new ArrayList<String>();
		for(WebElement ele:list){
			actData.add(ele.getText().trim());
		}
		//		System.out.println(actData);
		if(actData.containsAll(expList))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel( "USer DropDown list items is Verified..", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( "User DropDown list items did not verified..", ExtentColor.RED));	
		}

		WebElement developerConsole=driver.findElement((By.linkText("Developer Console")));
		//		developerConsole.click();
		clickObj(developerConsole, "Developer Console");
		String oldWindow=driver.getWindowHandle();
		Set<String> getAllWindows=driver.getWindowHandles();
		String[] getWindow=getAllWindows.toArray(new String[getAllWindows.size()]);
		driver.switchTo().window(getWindow[1]);
		driver.close();
		driver.switchTo().window(oldWindow);
		driver.close();
	}

	public static void TC09() throws IOException
	{
		Properties pro=configProperty("./src/test/resources/Utility/Configuration.properties");
		driver=IntializeDriver(pro.getProperty("browser"));

		createTestScriptReport("TC09");

		System.out.println("Pass: Firefox driver is launched");
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(pro.getProperty("salesforceUrl"));

		String path="C:\\Kavitha\\Udemy_Workspace\\JavaQaTraining\\SeleniumAutomation\\TestData\\TC09.xls";


		String uname=null;
		String pawd=null;

		String[][] data=readSheet(path, "Sheet1");
		for(int i=1;i<data.length;i++)
		{
			uname=data[i][0];
			pawd=data[i][1];
		}
		String[] options=new String[]{"My Profile","My Settings","Developer Console","Logout"};
		List<String> expList=new ArrayList<String>
		(Arrays.asList(options));

		System.out.println("Pass: Firefox driver is launched");

		String Title=driver.getTitle();
		if(Title.contains("Salesforce"))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel( "Salesforce application page is Verified..", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( "Salesforce page did not verified..", ExtentColor.RED));	
		}
		driver.manage().window().maximize();

		/*Enter username to username field*/
		WebElement un = driver.findElement(By.xpath("//input[@id='username']"));
		enterText(un, uname, "userName");

		/*Enter password to password field*/
		WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
		enterText(pwd, pawd, "Password");

		/*Click login*/
		WebElement login = driver.findElement(By.xpath("//input[@id='Login']")) ;
		clickObj(login, "Login Button");

		/*User Menu Displayed*/
		WebElement usermenu=driver.findElement(By.xpath("//*[@id='userNav-arrow']"));
		clickObj(usermenu, "UserMenuTab");

		List<WebElement> list=driver.findElements(By.xpath("//*[@id='userNav-menuItems']/a"));
		//		System.out.println(list);
		ArrayList<String> actData=new ArrayList<String>();
		for(WebElement ele:list){
			actData.add(ele.getText().trim());
		}
		//		System.out.println(actData);
		if(actData.containsAll(expList))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel( "USer DropDown list items is Verified..", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( "User DropDown list items did not verified..", ExtentColor.RED));	
		}

		WebElement logout=driver.findElement(By.xpath("//a[text()='Logout']"));
		clickObj(logout, "Logout");

		driver.close();
	}


	public static void TC10_CreateAccount() throws InterruptedException, IOException
	{
		Properties pro=configProperty("./src/test/resources/Utility/Configuration.properties");
		driver=IntializeDriver(pro.getProperty("browser"));

		createTestScriptReport("TC10_CreateAccount");

		System.out.println("Pass: Firefox driver is launched");
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(pro.getProperty("salesforceUrl"));

		String path="C:\\Kavitha\\Udemy_Workspace\\JavaQaTraining\\SeleniumAutomation\\TestData\\TC10_CreateAccount.xls";


		String uname=null;
		String pawd=null;
		String AccntName=null;

		String[][] data=readSheet(path, "Sheet1");
		for(int i=1;i<data.length;i++)
		{
			uname=data[i][0];
			pawd=data[i][1];
			AccntName=data[i][2];
		}
		String[] options=new String[]{"My Profile","My Settings","Developer Console","Logout"};
		List<String> expList=new ArrayList<String>
		(Arrays.asList(options));

		System.out.println("Pass: Firefox driver is launched");
		//		System.out.println("Pass: SFDC login page is launched..");
		String Title=driver.getTitle();
		if(Title.contains("Salesforce"))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel( "Salesforce application page is Verified..", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( "Salesforce page did not verified..", ExtentColor.RED));	
		}
		driver.manage().window().maximize();

		/*Enter username to username field*/
		WebElement un = driver.findElement(By.xpath("//input[@id='username']"));
		enterText(un, uname, "userName");

		/*Enter password to password field*/
		WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
		enterText(pwd, pawd, "Password");

		/*Click login*/
		WebElement login = driver.findElement(By.xpath("//input[@id='Login']")) ;
		clickObj(login, "Login Button");

		WebElement accounts=driver.findElement((By.linkText("Accounts")));
		//		accounts.click();
		clickObj(accounts, "Accounts Tab");
		Thread.sleep(5000);

		WebElement alertclose=driver.findElement((By.xpath("//a[@title='Close']")));
//		alertclose.click();
		clickObj(alertclose, "Close Alert");

		Thread.sleep(5000);
		WebElement accntbtn=driver.findElement((By.xpath("//input[@title='New']")));
		//		accntbtn.click();
		clickObj(accntbtn, "Account Button");

		Thread.sleep(4000);
		WebElement accntName=driver.findElement((By.xpath("//input[@id='acc2']")));
		//		accntName.sendKeys("WellsFargo");
		enterText(accntName, AccntName, "Account Name");

		Thread.sleep(4000);
		WebElement savebtn=driver.findElement((By.xpath("//input[@type='submit']")));
		//		savebtn.click();
		clickObj(savebtn, "SAVE");
		driver.close();

	}

	public static void TC11_Createnewview() throws IOException, InterruptedException
	{
		try {
			Properties pro=configProperty("./src/test/resources/Utility/Configuration.properties");
			driver=IntializeDriver(pro.getProperty("browser"));

			String path="C:\\Kavitha\\Udemy_Workspace\\JavaQaTraining\\SeleniumAutomation\\TestData\\TC11_Createnewview.xls";
			String uname=null;
			String pawd=null;
			String view=null;
			String Uview=null;
			String[][] data=readSheet(path, "Sheet1");
			for(int i=1;i<data.length;i++)
			{
				uname=data[i][0];
				pawd=data[i][1];
				view=data[i][2];
				Uview=data[i][3];
			}
			createTestScriptReport("TC11_Createnewview");
			logger.log(Status.INFO,"Pass: Firefox driver is launched");

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(pro.getProperty("salesforceUrl"));
			String Title=driver.getTitle();
			if(Title.contains("Salesforce"))
			{
				logger.log(Status.PASS, MarkupHelper.createLabel( "Salesforce application page is Verified..", ExtentColor.GREEN));
			}
			else
			{
				logger.log(Status.FAIL, MarkupHelper.createLabel( "Salesforce page did not verified..", ExtentColor.RED));	
			}
			driver.manage().window().maximize();

			/*Enter username to username field*/
			WebElement un = driver.findElement(By.xpath("//input[@id='username']"));
			enterText(un, uname, "userName");

			/*Enter password to password field*/
			WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
			enterText(pwd, pawd, "Password");

			/*Click login*/
			WebElement login = driver.findElement(By.xpath("//input[@id='Login']")) ;
			clickObj(login, "Login Button");

			WebElement accntTab=driver.findElement(By.xpath("//a[@title='Accounts Tab']"));
			clickObj(accntTab, "Accounttab");

			WebElement alertclose=driver.findElement((By.xpath("//a[@title='Close']")));
			clickObj(alertclose, "AlertWindow");

			WebElement createnewview=driver.findElement(By.xpath("//a[contains(text(),'Create New View')]"));
			clickObj(createnewview, "CreateNewView");

			WebElement name=driver.findElement((By.xpath("//input[@name='fname']")));
			//		name.sendKeys("New View");
			enterText(name, view, "ViewByName");

			WebElement uniqview=driver.findElement(By.xpath("//input[@id='devname']"));
			//		uniqview.sendKeys("UniqueView");
			enterText(uniqview, Uview, "Bynameuniqview");

			Thread.sleep(4000);
			WebElement savebtn=driver.findElement((By.xpath("//input[@type='submit']")));
			//		savebtn.click();
			clickObj(savebtn, "Save");

			driver.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void TC12_Editview() throws IOException, InterruptedException
	{
		try{
			Properties pro=configProperty("./src/test/resources/Utility/Configuration.properties");
			driver=IntializeDriver(pro.getProperty("browser"));

			String path="C:\\Kavitha\\Udemy_Workspace\\JavaQaTraining\\SeleniumAutomation\\TestData\\TC12_Editview.xls";
			String uname=null;
			String pawd=null;

			String[][] data=readSheet(path, "Sheet1");
			for(int i=1;i<data.length;i++)
			{
				uname=data[i][0];
				pawd=data[i][1];
				//			view=data[i][2];
				//			Uview=data[i][3];
			}

			createTestScriptReport("TC12_Editview");
			logger.log(Status.INFO,"Pass: Firefox driver is launched");

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(pro.getProperty("salesforceUrl"));
			String Title=driver.getTitle();
			if(Title.contains("Salesforce"))
			{
				logger.log(Status.PASS, MarkupHelper.createLabel( "Salesforce application page is Verified..", ExtentColor.GREEN));
			}
			else
			{
				logger.log(Status.FAIL, MarkupHelper.createLabel( "Salesforce page did not verified..", ExtentColor.RED));	
			}

			driver.manage().window().maximize();

			/*Enter username to username field*/
			WebElement un = driver.findElement(By.xpath("//input[@id='username']"));
			enterText(un, uname, "userName");

			/*Enter password to password field*/
			WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
			enterText(pwd, pawd, "Password");

			/*Click login*/
			WebElement login = driver.findElement(By.xpath("//input[@id='Login']")) ;
			clickObj(login, "Login Button");

			Thread.sleep(5000);
			WebElement accntTab=driver.findElement(By.xpath(" //a[@title='Accounts Tab']"));
			clickObj(accntTab, "Accounttab");

			WebElement alertclose=driver.findElement((By.xpath("//a[@title='Close']")));
			clickObj(alertclose, "AlertWindow");

			Thread.sleep(5000);
			WebElement viewdrpdwn=driver.findElement((By.xpath("//select[@id='fcf']")));
			dropDown(viewdrpdwn,"newlyview", "ViewDropDown");

			WebElement editbtn=driver.findElement(By.linkText("Edit"));
			clickObj(editbtn, "Edit");

			WebElement viewname=driver.findElement(By.xpath("//input[@id='fname']"));
			viewname.sendKeys("Wells1View");
			enterText(viewname, "Wells1View", "View Name");

			Thread.sleep(6000);
			WebElement filedName=driver.findElement(By.xpath(".//*[@id='fcol1']"));
			//		Select select1=new Select(filedName);
			//		select1.selectByVisibleText("Account Name");
			dropDown(filedName,"Account Name", "Field");
			//
			Thread.sleep(4000);
			WebElement fieldOper=driver.findElement(By.xpath(".//*[@id='fop1']"));
			dropDown(fieldOper, "contains", "Operator");

			Thread.sleep(3000);
			WebElement fval=driver.findElement(By.xpath(".//*[@id='fval1']"));
			enterText(fval, "<a>", "Value");


			Thread.sleep(3000);

			WebElement availablefields=driver.findElement((By.xpath("//select[@id='colselector_select_0']")));
			dropDown(availablefields, "Last Activity", "Available Fields");

			WebElement addbtn=driver.findElement((By.xpath("//img[@title='Add']")));
			clickObj(addbtn, "AddBtn");

			WebElement savebtn=driver.findElement((By.xpath("//div[@class='pbHeader']//input[@title='Save']")));
			clickObj(savebtn, "SaveBtn");

			driver.close();
		}catch(Exception e)
		{
			System.out.println("bhbj");
		}
	}	
	public static void TC13_MergeAccounts() throws IOException
	{
		try {
			Properties pro=configProperty("./src/test/resources/Utility/Configuration.properties");
			driver=IntializeDriver(pro.getProperty("browser"));
			
		String path="C:\\Kavitha\\Udemy_Workspace\\JavaQaTraining\\SeleniumAutomation\\TestData\\TC13_MergeAccounts.xls";
		String uname=null;
		String pawd=null;

		String[][] data=readSheet(path, "Sheet1");
		for(int i=1;i<data.length;i++)
		{
			uname=data[i][0];
			pawd=data[i][1];
			//			view=data[i][2];
			//			Uview=data[i][3];
		}

		createTestScriptReport("TC13_MergeAccounts");
		//		DriverScript.IntializeDriver("browser");
		logger.log(Status.INFO,"Pass: Firefox driver is launched");
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(pro.getProperty("salesforceUrl"));
		String Title=driver.getTitle();
		if(Title.contains("Salesforce"))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel( "Salesforce application page is Verified..", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( "Salesforce page did not verified..", ExtentColor.RED));	
		}

		driver.manage().window().maximize();

		/*Enter username to username field*/
		WebElement un = driver.findElement(By.xpath("//input[@id='username']"));
		enterText(un, uname, "userName");

		/*Enter password to password field*/
		WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
		enterText(pwd, pawd, "Password");

		/*Click login*/
		WebElement login = driver.findElement(By.xpath("//input[@id='Login']")) ;
		clickObj(login, "Login Button");

		WebElement accntTab=driver.findElement(By.xpath(" //a[@title='Accounts Tab']"));
		clickObj(accntTab, "Accounttab");

		WebElement alertclose=driver.findElement((By.xpath("//a[@title='Close']")));
		clickObj(alertclose, "AlertWindow");

		WebElement mergeaccnts=driver.findElement((By.linkText("Merge Accounts")));
		//		mergeaccnts.click();
		clickObj(mergeaccnts, "Merge Accounts");

		WebElement textboxaccnt=driver.findElement(By.xpath("//input[@id='srch']"));
		//		textboxaccnt.sendKeys("wells");
		//		clickObj(textboxaccnt, "Find Accounts");
		enterText(textboxaccnt, "wells", "Find Accounts");

		WebElement findaccnts=driver.findElement((By.xpath("//div[contains(@class,'pbWizardBody')]//input[2]")));
		//		findaccnts.click();
		clickObj(findaccnts, "Find Accounts");

		WebElement chkbox=driver.findElement(By.xpath("//input[@id='cid0']"));
		//		chkbox.click();
		clickObj(chkbox, "Checkbox");

		WebElement chkbox1=driver.findElement(By.xpath("//input[@id='cid1']"));
		//		chkbox1.click();
		clickObj(chkbox1, "Checkbox");

		WebElement savebtn=driver.findElement(By.xpath("//div[@class='pbBottomButtons']//input[@title='Next']"));
		//		savebtn.click();
		clickObj(savebtn, "Savebtn");

		WebElement mergebtn=driver.findElement(By.xpath("//div[@class='pbBottomButtons']//input[@title='Merge']"));
		//		mergebtn.click();
		clickObj(mergebtn, "Merge Button");

		Alert alert_box = driver.switchTo().alert();
		alert_box.accept();

		driver.close();

	}catch(Exception e) {
		System.out.println("vvh");}
		
	}

	public static void TC14() throws IOException, InterruptedException
	{
		Properties pro=configProperty("./src/test/resources/Utility/Configuration.properties");
		driver=IntializeDriver(pro.getProperty("browser"));
		
		String path="C:\\Kavitha\\Udemy_Workspace\\JavaQaTraining\\SeleniumAutomation\\TestData\\TC14.xls";
		String uname=null;
		String pawd=null;

		String[][] data=readSheet(path, "Sheet1");
		for(int i=1;i<data.length;i++)
		{
			uname=data[i][0];
			pawd=data[i][1];
		}

		createTestScriptReport("TC14");
		//		DriverScript.IntializeDriver("browser");
		logger.log(Status.INFO,"Pass: Firefox driver is launched");
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.get(pro.getProperty("salesforceUrl"));
		String Title=driver.getTitle();
		if(Title.contains("Salesforce"))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel( "Salesforce application page is Verified..", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( "Salesforce page did not verified..", ExtentColor.RED));	
		}

		driver.manage().window().maximize();

		/*Enter username to username field*/
		WebElement un = driver.findElement(By.xpath("//input[@id='username']"));
		enterText(un, uname, "userName");

		/*Enter password to password field*/
		WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
		enterText(pwd, pawd, "Password");

		/*Click login*/
		WebElement login = driver.findElement(By.xpath("//input[@id='Login']")) ;
		clickObj(login, "Login Button");

		WebElement accntTab=driver.findElement(By.xpath(" //a[@title='Accounts Tab']"));
		clickObj(accntTab, "Accounttab");

		WebElement alertclose=driver.findElement((By.xpath("//a[@title='Close']")));
		clickObj(alertclose, "AlertWindow");

		WebElement accntlink=driver.findElement(By.xpath("//a[contains(text(),'Accounts with last activity > 30 days')]"));
		//		accntlink.click();
		clickObj(accntlink, "AccntsLastActivity");

		WebElement selectMenu=driver.findElement(By.id("ext-gen20"));
		clickObj(selectMenu, "DateField");

		WebElement cdate=driver.findElement(By.xpath("//div[contains(text(),'Created Date')]"));
		cdate.click();

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
		//get current date time with Date()
		Date date = new Date();

		// Now format the date
		String date1= dateFormat.format(date);

		Thread.sleep(2000);
		WebElement fromDate=driver.findElement(By.id("ext-comp-1042"));
		fromDate.clear();
		fromDate.sendKeys(date1);

		Thread.sleep(3000);

		WebElement toDate=driver.findElement(By.id("ext-comp-1045"));
		toDate.clear();
		toDate.sendKeys(date1);

		Thread.sleep(2000);

		WebElement saveObj=driver.findElement(By.id("ext-gen49"));
		//		saveObj.click();
		clickObj(saveObj, "SaveBtn");

		Thread.sleep(2000);
		String oldwindow=driver.getWindowHandle();
		for(String handles:driver.getWindowHandles())
		{
			if(!handles.equals(oldwindow))
			{
				driver.switchTo().window(handles);
			}
		}

		WebElement reportName=driver.findElement(By.id("saveReportDlg_reportNameField"));
		enterText(reportName, "SeptRep1", "ReportName");

		WebElement repUniqueName=driver.findElement(By.id("saveReportDlg_DeveloperName"));
		enterText(repUniqueName, "SepUniRep1", "UniqueRepName");

		Thread.sleep(3000);
		WebElement saveRun=driver.findElement(By.xpath(".//*[@id='dlgSaveAndRun']"));
		clickObj(saveRun, "SaveNRun");

		driver.close();

	}

	public static void TC15() throws IOException, InterruptedException
	{
		Properties pro=configProperty("./src/test/resources/Utility/Configuration.properties");
		driver=IntializeDriver(pro.getProperty("browser"));

		String path="C:\\Kavitha\\Udemy_Workspace\\JavaQaTraining\\SeleniumAutomation\\TestData\\TC15.xls";
		String uname=null;
		String pawd=null;

		String[][] data=readSheet(path, "Sheet1");
		for(int i=1;i<data.length;i++)
		{
			uname=data[i][0];
			pawd=data[i][1];
		}

		createTestScriptReport("TC15");
		//		DriverScript.IntializeDriver("browser");
		logger.log(Status.INFO,"Pass: Firefox driver is launched");
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.get(pro.getProperty("salesforceUrl"));
		String Title=driver.getTitle();
		if(Title.contains("Salesforce"))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel( "Salesforce application page is Verified..", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( "Salesforce page did not verified..", ExtentColor.RED));	
		}

		driver.manage().window().maximize();

		/*Enter username to username field*/
		WebElement un = driver.findElement(By.xpath("//input[@id='username']"));
		enterText(un, uname, "userName");

		/*Enter password to password field*/
		WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
		enterText(pwd, pawd, "Password");

		/*Click login*/
		WebElement login = driver.findElement(By.xpath("//input[@id='Login']")) ;
		clickObj(login, "Login Button");

		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		WebElement opport = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Opportunities']")));
		clickObj(opport, "Opportunities");

		WebElement alertwin=driver.findElement(By.xpath("//a[text()='Close']"));
		alertwin.click();

		String opptitle=driver.getTitle();
		String exptitle="Opportunities";
		if(opptitle.contains(exptitle))
		{
			logger.log(Status.PASS, "Opportunities page verified");
		}
		else
		{
			logger.log(Status.FAIL, "Opportunities page verification failed");
		}

		String[] options=new String[]{"Closing Next Month","Closing This Month","My Opportunities","New This Week","Recently Viewed Opportunities","Won"};
		List<String> expList=new ArrayList<String>(Arrays.asList(options));

		List<WebElement> list=driver.findElements(By.xpath(".//*[@id='fcf']"));

		Thread.sleep(4000);
		//		System.out.println(list);
		ArrayList<String> actData=new ArrayList<String>();
		for(WebElement ele:list){
			actData.add(ele.getText().trim());
		}
		//		System.out.println(actData);
		if(actData.contains(expList))
		{
			System.out.println("opp verified");
			logger.log(Status.PASS, "Opportunites drop down verified");
		}
		else
		{
			logger.log(Status.FAIL, "Opportunites drop down verification failed");
		}

		driver.close();
	}

	public static void TC16() throws IOException
	{

		Properties pro=configProperty("./src/test/resources/Utility/Configuration.properties");
		driver=IntializeDriver(pro.getProperty("browser"));

		String path="C:\\Kavitha\\Udemy_Workspace\\JavaQaTraining\\SeleniumAutomation\\TestData\\TC16.xls";
		String uname=null;
		String pawd=null;

		String[][] data=readSheet(path, "Sheet1");
		for(int i=1;i<data.length;i++)
		{
			uname=data[i][0];
			pawd=data[i][1];
		}

		createTestScriptReport("TC16");
		//		DriverScript.IntializeDriver("browser");
		logger.log(Status.INFO,"Pass: Firefox driver is launched");
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.get(pro.getProperty("salesforceUrl"));
		String Title=driver.getTitle();
		if(Title.contains("Salesforce"))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel( "Salesforce application page is Verified..", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( "Salesforce page did not verified..", ExtentColor.RED));	
		}

		driver.manage().window().maximize();

		/*Enter username to username field*/
		WebElement un = driver.findElement(By.xpath("//input[@id='username']"));
		enterText(un, uname, "userName");

		/*Enter password to password field*/
		WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
		enterText(pwd, pawd, "Password");

		/*Click login*/
		WebElement login = driver.findElement(By.xpath("//input[@id='Login']")) ;
		clickObj(login, "Login Button");

		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		WebElement opport = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Opportunities']")));
		clickObj(opport, "Opportunities");

		WebElement alertwin=driver.findElement(By.xpath("//a[text()='Close']"));
		clickObj(alertwin, "AlertWindow");

		String opptitle=driver.getTitle();
		String exptitle="Opportunities";
		if(opptitle.contains(exptitle))
		{
			logger.log(Status.PASS, "Opportunities page verified");
		}
		else
		{
			logger.log(Status.FAIL, "Opportunities page verification failed");
		}

		WebElement newoppt=driver.findElement(By.xpath("//input[@title='New']"));
		clickObj(newoppt, "NewOpptBtn");

		WebElement oppName=driver.findElement(By.xpath("//input[@id='opp3']"));
		enterText(oppName, "sepOppr", "OpportnityName");

		WebElement accntname=driver.findElement(By.xpath("//input[@id='opp4']"));
		enterText(accntname, "well 1", "Account Name");

		WebElement selectDate=driver.findElement(By.xpath(".//*[@id='ep']/div[2]/div[3]/table/tbody/tr[2]/td[4]/div/span/span/a"));
		selectDate.click();

		WebElement stage=driver.findElement(By.xpath(".//*[@id='opp11']"));
		Select stagesel=new Select(stage);
		stagesel.selectByVisibleText("Prospecting");

		//		WebElement probability=driver.findElement(By.xpath(".//*[@id='opp12']"));
		//		probability.sendKeys("2");

		WebElement leadSource=driver.findElement(By.xpath(".//*[@id='opp6']"));
		Select NewSelect=new Select(leadSource);
		NewSelect.selectByVisibleText("Web");

		WebElement primarySource=driver.findElement(By.xpath(".//*[@id='opp17_lkwgt']/img"));
		primarySource.click();

		String parentWindow=driver.getWindowHandle();

		Set<String> allWindows = driver.getWindowHandles();
		for(String curWindow : allWindows){
			driver.switchTo().window(curWindow);
		}

		driver.close();
		driver.switchTo().window(parentWindow);

		WebElement saveBtn=driver.findElement(By.xpath(".//*[@id='bottomButtonRow']/input[1]"));
		saveBtn.click();

		WebElement newOppDisp=driver.findElement(By.xpath(".//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2"));
		String page=newOppDisp.getText();


		//		if(page.equals("New opp1"))
		//			DriverFile.logger.log(Status.INFO,"new Opportunity page diaplyed");
		//		else
		//			DriverFile.logger.log(Status.INFO, "new Opportunity page not diaplyed");

		driver.close();
	}

	public static void TC17() throws IOException
	{

		Properties pro=configProperty("./src/test/resources/Utility/Configuration.properties");
		driver=IntializeDriver(pro.getProperty("browser"));

		String path="C:\\Kavitha\\Udemy_Workspace\\JavaQaTraining\\SeleniumAutomation\\TestData\\TC17.xls";
		String uname=null;
		String pawd=null;

		String[][] data=readSheet(path, "Sheet1");
		for(int i=1;i<data.length;i++)
		{
			uname=data[i][0];
			pawd=data[i][1];
		}

		createTestScriptReport("TC17");
		//		DriverScript.IntializeDriver("browser");
		logger.log(Status.INFO,"Pass: Firefox driver is launched");
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.get(pro.getProperty("salesforceUrl"));
		String Title=driver.getTitle();
		if(Title.contains("Salesforce"))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel( "Salesforce application page is Verified..", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( "Salesforce page did not verified..", ExtentColor.RED));	
		}

		driver.manage().window().maximize();

		/*Enter username to username field*/
		WebElement un = driver.findElement(By.xpath("//input[@id='username']"));
		enterText(un, uname, "userName");

		/*Enter password to password field*/
		WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
		enterText(pwd, pawd, "Password");

		/*Click login*/
		WebElement login = driver.findElement(By.xpath("//input[@id='Login']")) ;
		clickObj(login, "Login Button");

		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		WebElement opport = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Opportunities']")));
		clickObj(opport, "Opportunities");

		WebElement alertwin=driver.findElement(By.xpath("//a[text()='Close']"));
		clickObj(alertwin, "AlertWindow");

		String opptitle=driver.getTitle();
		String exptitle="Opportunities";
		if(opptitle.contains(exptitle))
		{
			logger.log(Status.PASS, "Opportunities page verified");
		}
		else
		{
			logger.log(Status.FAIL, "Opportunities page verification failed");
		}

		WebElement opppipeline=driver.findElement(By.xpath("//a[contains(text(),'Opportunity Pipeline')]"));
		clickObj(opppipeline, "Opportunity Pipeline");

		driver.close();
	}

	public static void TC18() throws IOException
	{
		Properties pro=configProperty("./src/test/resources/Utility/Configuration.properties");
		driver=IntializeDriver(pro.getProperty("browser"));

		String path="C:\\Kavitha\\Udemy_Workspace\\JavaQaTraining\\SeleniumAutomation\\TestData\\TC18.xls";
		String uname=null;
		String pawd=null;

		String[][] data=readSheet(path, "Sheet1");
		for(int i=1;i<data.length;i++)
		{
			uname=data[i][0];
			pawd=data[i][1];
		}

		createTestScriptReport("TC18");
		//		DriverScript.IntializeDriver("browser");
		logger.log(Status.INFO,"Pass: Firefox driver is launched");
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.get(pro.getProperty("salesforceUrl"));
		String Title=driver.getTitle();
		if(Title.contains("Salesforce"))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel( "Salesforce application page is Verified..", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( "Salesforce page did not verified..", ExtentColor.RED));	
		}

		driver.manage().window().maximize();

		/*Enter username to username field*/
		WebElement un = driver.findElement(By.xpath("//input[@id='username']"));
		enterText(un, uname, "userName");

		/*Enter password to password field*/
		WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
		enterText(pwd, pawd, "Password");

		/*Click login*/
		WebElement login = driver.findElement(By.xpath("//input[@id='Login']")) ;
		clickObj(login, "Login Button");

		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		WebElement opport = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Opportunities']")));
		clickObj(opport, "Opportunities");

		WebElement alertwin=driver.findElement(By.xpath("//a[text()='Close']"));
		clickObj(alertwin, "AlertWindow");

		String opptitle=driver.getTitle();
		String exptitle="Opportunities";
		if(opptitle.contains(exptitle))
		{
			logger.log(Status.PASS, "Opportunities page verified");
		}
		else
		{
			logger.log(Status.FAIL, "Opportunities page verification failed");
		}

		WebElement stuckOppt=driver.findElement(By.xpath("//a[contains(text(),'Stuck Opportunities')]"));
		clickObj(stuckOppt, "Stuck Opportunities");

		driver.close();
	}

	public static void TC19() throws IOException, InterruptedException
	{
		Properties pro=configProperty("./src/test/resources/Utility/Configuration.properties");
		driver=IntializeDriver(pro.getProperty("browser"));

		String path="C:\\Kavitha\\Udemy_Workspace\\JavaQaTraining\\SeleniumAutomation\\TestData\\TC19.xls";
		String uname=null;
		String pawd=null;

		String[][] data=readSheet(path, "Sheet1");
		for(int i=1;i<data.length;i++)
		{
			uname=data[i][0];
			pawd=data[i][1];
		}

		createTestScriptReport("TC19");
		//		DriverScript.IntializeDriver("browser");
		logger.log(Status.INFO,"Pass: Firefox driver is launched");
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.get(pro.getProperty("salesforceUrl"));
		String Title=driver.getTitle();
		if(Title.contains("Salesforce"))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel( "Salesforce application page is Verified..", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( "Salesforce page did not verified..", ExtentColor.RED));	
		}

		driver.manage().window().maximize();

		/*Enter username to username field*/
		WebElement un = driver.findElement(By.xpath("//input[@id='username']"));
		enterText(un, uname, "userName");

		/*Enter password to password field*/
		WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
		enterText(pwd, pawd, "Password");

		/*Click login*/
		WebElement login = driver.findElement(By.xpath("//input[@id='Login']")) ;
		clickObj(login, "Login Button");

		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		WebElement opport = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Opportunities']")));
		clickObj(opport, "Opportunities");

		WebElement alertwin=driver.findElement(By.xpath("//a[text()='Close']"));
		clickObj(alertwin, "AlertWindow");

		String opptitle=driver.getTitle();
		String exptitle="Opportunities";
		if(opptitle.contains(exptitle))
		{
			logger.log(Status.PASS, "Opportunities page verified");
		}
		else
		{
			logger.log(Status.FAIL, "Opportunities page verification failed");
		}

		Thread.sleep(5000);
		WebElement intertxt=driver.findElement(By.xpath("//select[@id='quarter_q']"));
		dropDown(intertxt, "Current FQ", "Interval");

		WebElement inclutxt=driver.findElement(By.xpath("//select[@id='open']"));
		dropDown(inclutxt, "Open Opportunities", "Include");

		WebElement runrepbtn=driver.findElement(By.xpath("//input[@title='Run Report']"));
		clickObj(runrepbtn, "RunReport");

		driver.close();
	}

	public static void TC20_leadsTab() throws IOException, InterruptedException
	{
		Properties pro=configProperty("./src/test/resources/Utility/Configuration.properties");
		driver=IntializeDriver(pro.getProperty("browser"));

		String path="C:\\Kavitha\\Udemy_Workspace\\JavaQaTraining\\SeleniumAutomation\\TestData\\TC20_leadsTab.xls";
		String uname=null;
		String pawd=null;

		String[][] data=readSheet(path, "Sheet1");
		for(int i=1;i<data.length;i++)
		{
			uname=data[i][0];
			pawd=data[i][1];
		}

		createTestScriptReport("TC20_leadsTab");
		//		DriverScript.IntializeDriver("browser");
		logger.log(Status.INFO,"Pass: Firefox driver is launched");
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.get(pro.getProperty("salesforceUrl"));
		String Title=driver.getTitle();
		if(Title.contains("Salesforce"))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel( "Salesforce application page is Verified..", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( "Salesforce page did not verified..", ExtentColor.RED));	
		}

		driver.manage().window().maximize();

		/*Enter username to username field*/
		WebElement un = driver.findElement(By.xpath("//input[@id='username']"));
		enterText(un, uname, "userName");

		/*Enter password to password field*/
		WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
		enterText(pwd, pawd, "Password");

		/*Click login*/
		WebElement login = driver.findElement(By.xpath("//input[@id='Login']")) ;
		clickObj(login, "Login Button");

		Thread.sleep(5000);
		WebElement leadtab=driver.findElement(By.xpath("//a[text()='Leads']"));
		clickObj(leadtab, "Leads");


		WebElement winAlert=driver.findElement(By.xpath("//a[text()='Close']"));
		clickObj(winAlert, "AlertClosed");

		String actText=driver.getTitle();
		if(actText.contains("Leads"))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel("Leads Home Page is verified", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel("Leads Home Page verification failed", ExtentColor.RED));
		}
		driver.close();
	}

	public static void TC21_leadsSelectView() throws InterruptedException, IOException
	{

		Properties pro=configProperty("./src/test/resources/Utility/Configuration.properties");
		driver=IntializeDriver(pro.getProperty("browser"));

		String path="C:\\Kavitha\\Udemy_Workspace\\JavaQaTraining\\SeleniumAutomation\\TestData\\TC21_leadsSelectView.xls";
		String uname=null;
		String pawd=null;

		String[][] data=readSheet(path, "Sheet1");
		for(int i=1;i<data.length;i++)
		{
			uname=data[i][0];
			pawd=data[i][1];
		}

		createTestScriptReport("TC21_leadsSelectView");
		//		DriverScript.IntializeDriver("browser");
		logger.log(Status.INFO,"Pass: Firefox driver is launched");
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.get(pro.getProperty("salesforceUrl"));
		String Title=driver.getTitle();
		if(Title.contains("Salesforce"))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel( "Salesforce application page is Verified..", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( "Salesforce page did not verified..", ExtentColor.RED));	
		}

		driver.manage().window().maximize();

		/*Enter username to username field*/
		WebElement un = driver.findElement(By.xpath("//input[@id='username']"));
		enterText(un, uname, "userName");

		/*Enter password to password field*/
		WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
		enterText(pwd, pawd, "Password");

		/*Click login*/
		WebElement login = driver.findElement(By.xpath("//input[@id='Login']")) ;
		clickObj(login, "Login Button");

		Thread.sleep(5000);
		WebElement leadtab=driver.findElement(By.xpath("//a[text()='Leads']"));
		clickObj(leadtab, "Leads");


		WebElement winAlert=driver.findElement(By.xpath("//a[text()='Close']"));
		clickObj(winAlert, "AlertClosed");

		String actText=driver.getTitle();
		if(actText.contains("Leads"))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel("Leads Home Page is verified", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel("Leads Home Page verification failed", ExtentColor.RED));
		}

		//		String[] explist= {"All Open Leads","My Unread Leads","Recently Viewed Leads","Today's Leads"};

		String[] options=new String[]{"All Open Leads","My Unread Leads","Recently Viewed Leads","Today's Leads"};
		List<String> expList=new ArrayList<String>();
		Arrays.asList(options);

		WebElement dropdown=driver.findElement(By.xpath(".//*[@id='fcf']"));
		Select dropdownlsitselect=new Select(dropdown);

		List<WebElement> list=dropdownlsitselect.getOptions();
		//				System.out.println(list);
		ArrayList<String> actData=new ArrayList<String>();
		for(WebElement ele:list){
			actData.add(ele.getText().trim());
		}
		System.out.println(actData);
		if(actData.containsAll(expList))
		{
			logger.log(Status.INFO,MarkupHelper.createLabel("Leads dropdown menu verified", ExtentColor.GREEN));
			//		System.out.println("lead pass");
		}
		else
		{
			logger.log(Status.INFO,MarkupHelper.createLabel("Lead dropdown menu not verfied", ExtentColor.RED));
			//		System.out.println("lead fail");
		}
		driver.close();
	}

	public static void TC22_defaultView() throws InterruptedException, IOException
	{
		Properties pro=configProperty("./src/test/resources/Utility/Configuration.properties");
		driver=IntializeDriver(pro.getProperty("browser"));

		String path="C:\\Kavitha\\Udemy_Workspace\\JavaQaTraining\\SeleniumAutomation\\TestData\\TC22_defaultView.xls";
		String uname=null;
		String pawd=null;

		String[][] data=readSheet(path, "Sheet1");
		for(int i=1;i<data.length;i++)
		{
			uname=data[i][0];
			pawd=data[i][1];
		}

		createTestScriptReport("TC22_defaultView");
		//		DriverScript.IntializeDriver("browser");
		logger.log(Status.INFO,"Pass: Firefox driver is launched");
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.get(pro.getProperty("salesforceUrl"));
		String Title=driver.getTitle();
		if(Title.contains("Salesforce"))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel( "Salesforce application page is Verified..", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( "Salesforce page did not verified..", ExtentColor.RED));	
		}

		driver.manage().window().maximize();

		/*Enter username to username field*/
		WebElement un = driver.findElement(By.xpath("//input[@id='username']"));
		enterText(un, uname, "userName");

		/*Enter password to password field*/
		WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
		enterText(pwd, pawd, "Password");

		/*Click login*/
		WebElement login = driver.findElement(By.xpath("//input[@id='Login']")) ;
		clickObj(login, "Login Button");

		Thread.sleep(5000);
		WebElement leadtab=driver.findElement(By.xpath("//a[text()='Leads']"));
		clickObj(leadtab, "Leads");


		WebElement winAlert=driver.findElement(By.xpath("//a[text()='Close']"));
		clickObj(winAlert, "AlertClosed");

		String actText=driver.getTitle();
		if(actText.contains("Leads"))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel("Leads Home Page is verified", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel("Leads Home Page verification failed", ExtentColor.RED));
		}

		WebElement drpdwn=driver.findElement(By.xpath("//select[@id='fcf']"));
		dropDown(drpdwn, "Today's Leads", "View");

		WebElement userMenu=driver.findElement(By.xpath(" //div[@id='userNav-arrow']"));
		clickObj(userMenu, "userMenu");

		WebElement logout=driver.findElement(By.xpath("//a[text()='Logout']"));
		clickObj(logout, "Logout");

		Thread.sleep(5000);
		WebElement un1 = driver.findElement(By.xpath("//input[@id='username']"));
		enterText(un1, uname, "userName");

		/*Enter password to password field*/
		WebElement pwd1 = driver.findElement(By.xpath("//input[@id='password']"));
		enterText(pwd1, pawd, "Password");

		/*Click login*/
		WebElement login1 = driver.findElement(By.xpath("//input[@id='Login']")) ;
		clickObj(login1, "Login Button");

		WebElement leadtab1=driver.findElement(By.xpath("//a[text()='Leads']"));
		clickObj(leadtab1, "Leads");

		//		Thread.sleep(5000);
		//		WebElement winAlert1=driver.findElement(By.xpath("//a[text()='Close']"));
		//		clickObj(winAlert1, "AlertClosed");
		//		
		Thread.sleep(5000);
		String actText1=driver.getTitle();
		if(actText1.contains("Leads"))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel("Leads Home Page is verified", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel("Leads Home Page verification failed", ExtentColor.RED));
		}

		Thread.sleep(5000);
		WebElement gobtn=driver.findElement(By.xpath("//input[@title='Go!']"));
		clickObj(gobtn, "GoButton");

		driver.close();
	}

	public static void TC23() throws InterruptedException, IOException
	{
		Properties pro=configProperty("./src/test/resources/Utility/Configuration.properties");
		driver=IntializeDriver(pro.getProperty("browser"));

		String path="C:\\Kavitha\\Udemy_Workspace\\JavaQaTraining\\SeleniumAutomation\\TestData\\TC23.xls";
		String uname=null;
		String pawd=null;

		String[][] data=readSheet(path, "Sheet1");
		for(int i=1;i<data.length;i++)
		{
			uname=data[i][0];
			pawd=data[i][1];
		}

		createTestScriptReport("TC23");
		//		DriverScript.IntializeDriver("browser");
		logger.log(Status.INFO,"Pass: Firefox driver is launched");
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.get(pro.getProperty("salesforceUrl"));
		String Title=driver.getTitle();
		if(Title.contains("Salesforce"))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel( "Salesforce application page is Verified..", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( "Salesforce page did not verified..", ExtentColor.RED));	
		}

		driver.manage().window().maximize();

		/*Enter username to username field*/
		WebElement un = driver.findElement(By.xpath("//input[@id='username']"));
		enterText(un, uname, "userName");

		/*Enter password to password field*/
		WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
		enterText(pwd, pawd, "Password");

		/*Click login*/
		WebElement login = driver.findElement(By.xpath("//input[@id='Login']")) ;
		clickObj(login, "Login Button");

		Thread.sleep(5000);
		WebElement leadtab=driver.findElement(By.xpath("//a[text()='Leads']"));
		clickObj(leadtab, "Leads");


		WebElement winAlert=driver.findElement(By.xpath("//a[text()='Close']"));
		clickObj(winAlert, "AlertClosed");

		String actText=driver.getTitle();
		if(actText.contains("Leads"))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel("Leads Home Page is verified", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel("Leads Home Page verification failed", ExtentColor.RED));
		}

		WebElement drpdwn=driver.findElement(By.xpath("//select[@id='fcf']"));
		dropDown(drpdwn, "Today's Leads", "View");

		Thread.sleep(5000);
		WebElement gobtn=driver.findElement(By.xpath("//input[@title='Go!']"));
		clickObj(gobtn, "GoButton");

		driver.close();
	}

	public static void TC24() throws IOException, InterruptedException
	{
		Properties pro=configProperty("./src/test/resources/Utility/Configuration.properties");
		driver=IntializeDriver(pro.getProperty("browser"));

		String path="C:\\Kavitha\\Udemy_Workspace\\JavaQaTraining\\SeleniumAutomation\\TestData\\TC24.xls";
		String uname=null;
		String pawd=null;

		String[][] data=readSheet(path, "Sheet1");
		for(int i=1;i<data.length;i++)
		{
			uname=data[i][0];
			pawd=data[i][1];
		}

		createTestScriptReport("TC24");
		//		DriverScript.IntializeDriver("browser");
		logger.log(Status.INFO,"Pass: Firefox driver is launched");
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.get(pro.getProperty("salesforceUrl"));
		String Title=driver.getTitle();
		if(Title.contains("Salesforce"))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel( "Salesforce application page is Verified..", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( "Salesforce page did not verified..", ExtentColor.RED));	
		}

		driver.manage().window().maximize();

		/*Enter username to username field*/
		WebElement un = driver.findElement(By.xpath("//input[@id='username']"));
		enterText(un, uname, "userName");

		/*Enter password to password field*/
		WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
		enterText(pwd, pawd, "Password");

		/*Click login*/
		WebElement login = driver.findElement(By.xpath("//input[@id='Login']")) ;
		clickObj(login, "Login Button");

		Thread.sleep(5000);
		WebElement leadtab=driver.findElement(By.xpath("//a[text()='Leads']"));
		clickObj(leadtab, "Leads");


		WebElement winAlert=driver.findElement(By.xpath("//a[text()='Close']"));
		clickObj(winAlert, "AlertClosed");

		String actText=driver.getTitle();
		if(actText.contains("Leads"))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel("Leads Home Page is verified", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel("Leads Home Page verification failed", ExtentColor.RED));
		}

		WebElement newbtn=driver.findElement(By.xpath("//input[@title='New']"));
		clickObj(newbtn, "NewBtn");

		String acptxt=driver.getTitle();
		if(acptxt.contains("Lead Edit"))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel("New Leads Creation Page is Opened", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel("New Leads Creation Page did not open", ExtentColor.RED));
		}

		WebElement lastname=driver.findElement(By.xpath("//input[@id='name_lastlea2']"));
		enterText(lastname, "ABCD", "lastName");

		WebElement company=driver.findElement(By.xpath("//input[@id='lea3']"));
		enterText(company, "ABCD", "Comapany");

		WebElement savebtn=driver.findElement(By.xpath("//td[@id='topButtonRow']//input[@title='Save']"));
		clickObj(savebtn, "Save");

		String exptxt=driver.getTitle();
		if(exptxt.contains("ABCD"))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel("Newly created Leads view  Page is Dispalyed", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel("Newly created Leads view  Page did not  Dispalyed", ExtentColor.RED));
		}

		driver.close();
	}

	public static void TC25() throws IOException
	{
		Properties pro=configProperty("./src/test/resources/Utility/Configuration.properties");
		driver=IntializeDriver(pro.getProperty("browser"));
		

		String path="C:\\Kavitha\\Udemy_Workspace\\JavaQaTraining\\SeleniumAutomation\\TestData\\TC25.xls";
		String uname=null;
		String pawd=null;

		String[][] data=readSheet(path, "Sheet1");
		for(int i=1;i<data.length;i++)
		{
			uname=data[i][0];
			pawd=data[i][1];
		}

		createTestScriptReport("TC25");
		//		DriverScript.IntializeDriver("browser");
		logger.log(Status.INFO,"Pass: Firefox driver is launched");
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.get(pro.getProperty("salesforceUrl"));
		String Title=driver.getTitle();
		if(Title.contains("Salesforce"))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel( "Salesforce application page is Verified..", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( "Salesforce page did not verified..", ExtentColor.RED));	
		}

		driver.manage().window().maximize();

		/*Enter username to username field*/
		WebElement un = driver.findElement(By.xpath("//input[@id='username']"));
		enterText(un, uname, "userName");

		/*Enter password to password field*/
		WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
		enterText(pwd, pawd, "Password");

		/*Click login*/
		WebElement login = driver.findElement(By.xpath("//input[@id='Login']")) ;
		clickObj(login, "Login Button");

		WebElement contacts=driver.findElement(By.xpath("//a[text()='Contacts']"));
		clickObj(contacts, "Contacts");

		WebElement alertpop=driver.findElement(By.xpath("//a[text()='Close']"));
		alertpop.click();

		String ActualText=driver.getTitle();
		if(ActualText.contains("Contacts"))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel( "Contacts page is Verified..", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( "Contacts page did not verified..", ExtentColor.RED));	
		}

		WebElement newcont=driver.findElement(By.xpath("//input[@title='New']"));
		clickObj(newcont, "NewBtn");
		String Acttxt=driver.getTitle();
		if(Acttxt.contains("New"))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel( "New Contacts Home  page is Verified..", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( "New Contacts Home did not verified..", ExtentColor.RED));	
		}

		WebElement lastname=driver.findElement(By.xpath("//input[@id='name_lastcon2']"));
		enterText(lastname, "Bhuvanp", "LastName");

		WebElement accntName=driver.findElement(By.xpath("//input[@id='con4']"));
		enterText(accntName, "well 1", "Account Name");

		WebElement savebtn=driver.findElement(By.xpath("//td[@id='topButtonRow']//input[@title='Save']"));
		clickObj(savebtn, "Save");

		String ActStr=driver.getTitle();
		if(ActStr.contains("Bhuvanp"))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel( "New Contacts created..", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( "New Contact creation failed..", ExtentColor.RED));	
		}
		driver.close();
	}
}
