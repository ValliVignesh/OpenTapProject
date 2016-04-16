package homeworkweek4;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class WrapperMethods {
	public RemoteWebDriver driver;
	/*
	 * To launch the browser
	 * @param URL pass the valid url
	 * @param browswer pass the browser in which url to be launched
	 */
	public RemoteWebDriver launchBrowser(String URL,String browser){
		try {
			if (browser.equalsIgnoreCase("firefox"))
			{
				driver = new FirefoxDriver();
			}else if(browser.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
				driver = new ChromeDriver();
			}
		}
				catch (NullPointerException e)
				{
					System.out.println("Browser has been closed.Please try again");
				}
		catch (WebDriverException e) {
			System.out.println("Browser is not launched");
		}

		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);	
		driver.manage().window().maximize();
		return driver;
	}

	/*
	 * To select a value in dropdown through index vaue
	 * @param index the index too be selected
	 * @param id the id of the webelement dropdown to be found
	 */

	public void selectValueByIndexUsingID(int index,String id){
		try {
			WebElement element =driver.findElementById(id);
			Select dropdown=new Select(element);
			dropdown.selectByIndex(index);
			System.out.println("The value is selected from the dropdown");
		} catch (NoSuchElementException e) {
			System.out.println("No Such Element is not available");	
		}
		catch (WebDriverException e) {
			System.out.println("Browser is not launched properly");
		}

	}

	/* To switch to parent/primary window
	 */
	public void switchToPrimaryWindow(){
		try {
			driver.switchTo().defaultContent();
			String parentwindow=driver.getWindowHandle();
			driver.switchTo().window(parentwindow);
			System.out.println("Swiched to primary window");
		} catch(NoSuchWindowException e) {
			System.out.println("Window is not available");
		}
		catch (WebDriverException e) {
			System.out.println("Browser is not launched properly");
		}
	}
	/* To switch to Last window
	 * @param null value passed
	 */
	public void switchToLastWindow(){
		try{
			/*Set<String> allwindow = driver.getWindowHandles();
			for (String window : allwindow) {
				driver.switchTo().window(window);
			System.out.println(driver.getWindowHandle());
			}*/
			Set <String> eachwindow = driver.getWindowHandles();

			for (String lastwindow : eachwindow) {
				driver.switchTo().window(lastwindow);
				//driver.getWindowHandle();
				System.out.println("Switched to Last Window");
			} 
		}
		catch (NoSuchWindowException e) {
			System.out.println("Window is not available");
		}
		catch (WebDriverException e) {
			System.out.println("Browser is not launched properly");
		}

	}
	/* To switch to a frame by the webelement
	 * @param xpath pass the Xpath of the element
	 */

	public void switchToFrameByElementUsingXpath(String xpath){
		try {
			driver.switchTo().frame(driver.findElementByXPath(xpath));
			System.out.println("Switched to the requried frame "  +xpath);
		}catch (NoSuchFrameException e) {
			System.out.println("Frame is not available");
		}
		catch (WebDriverException e) {
			System.out.println("Browser is not launched properly");
		}
	}
	/* To switch to a frame by the index value
	 * @param index pass the index of the element
	 */

	public void switchToFrame(int index){
		try {
			driver.switchTo().frame(index);
			System.out.println("Switched to the requried frame " + index);
		} catch (NoSuchFrameException e) {
			System.out.println("Frame is not available");
		}
		catch (WebDriverException e) {
			System.out.println("Browser is not launched properly");
		}
	}
	/* To switch to the first frame by the index value
	 * @param null
	 * @return returns null
	 */
	public void switchToFirstFrame(){
		try{
			driver.switchTo().frame(0);
			System.out.println("Switched to the first frame");
		}
		catch (NoSuchFrameException e) {
			System.out.println("Frame is not available");
		}
		catch (WebDriverException e) {
			System.out.println("Browser is not launched properly");
		}
	}
	/* To accept a alert
	 */
	public void acceptAlert( ){
		try {
			driver.switchTo().alert().accept();
			System.out.println("Accepted the alert");
		} catch (NoAlertPresentException e) {
			System.out.println("Alert is not available");
		}
		catch (WebDriverException e) {
			System.out.println("Browser is not launched properly");
		}
	}

	/* To dismiss a alert
	 */
	public void dismissAlert( ){
		try {
			driver.switchTo().alert().dismiss();
			System.out.println("Alert has been dismissed");
		} catch (NoAlertPresentException e) {
			System.out.println("Alert is not available");
		}
		catch (WebDriverException e) {
			System.out.println("Browser is not launched properly");
		}
	}
	/*
	 * To verify the URL
	 * @param URL pass the valid url
	 * @return returns boolean value - stating whether the url is same as the passed one or not
	 */

	public boolean verifyUrl(String url){
		boolean flag1=false;

		try {
			if(driver.getCurrentUrl().equalsIgnoreCase(url)){
				flag1 =true;
				System.out.println("The current URL is same as" +url);

			} else{

			}

		} catch (Exception e) {
			System.out.println("The url " + url + "is not equal");
		}
		return flag1;
	}


	/* To verify the title
	 * @param pagetitle-pagetitle to be verified
	 * @return returns a boolean value to state whether the title is same or not
	 */
	@SuppressWarnings("finally")
	public boolean verifyTitle(String pagetitle){

		boolean flag = false;
		try {
			if(driver.getTitle().equalsIgnoreCase(pagetitle)){
				flag = true;	
				System.out.println("The title is same as mentioned " + driver.getTitle());
			}
		}
		finally{
			return flag;
		}

	}
	/* To click a button by xpath
	 * @param path-path  of the webelement to be clicked
	 */
	public void clickButtonByXpath(String path){
		try {
			driver.findElementByXPath(path).click();
			System.out.println();
		} catch (NoSuchElementException e) {
			System.out.println("The xpath is not found" +path);
		}
		catch (WebDriverException e) {
			System.out.println("Browser is not launched properly");
		}

	}
	/* To verify text by xpath
	 * @param Xpath-Xpath  of the webelement to get the text
	 * @param expectedText- text value to be compared with
	 */

	public void verifyText(String Xpath,String expectedText){

		String verifyText=driver.findElement(By.xpath(Xpath)).getText();
		try {
			if (verifyText.equals(expectedText)){
				System.out.println("The text matches..");
			}
			else{
				System.out.println("Text did not match");
				System.out.println("Expected Text" +expectedText);
				System.out.println("Actual Text" +verifyText);
			}
		} catch (NoSuchElementException e) {
			System.out.println("The Element with path "+Xpath +"is not found");
		}
		catch (WebDriverException e) {
			System.out.println("Browser is not launched properly");
		}
	}


	/* To enter a value by name
	 * @param name-name  of the webelement to be clicked
	 * @param value-value to be entered in the webelement
	 */

	public void enterByName(String name,String value) {
		try {
			driver.findElementByName(name).clear();
			driver.findElementByName(name).sendKeys(value);
		} catch (NoSuchElementException e) {
			System.out.println("Element is not available");	
		}
		catch (WebDriverException e) {
			System.out.println("Browser is not launched properly");
		}
	}	
	/* To enter a value by classname
	 * @param classname-classname  of the webelement to be clicked
	 * @param value-value to be entered in the webelement
	 */
	public void enterByClassName(String classname,String value){
		try {
			driver.findElementByClassName(classname).clear();
			driver.findElementByClassName(classname).sendKeys(value);
			System.out.println("Entered the text");
		} catch (NoSuchElementException e) {
			System.out.println("Element is not available");	
		}
		catch (WebDriverException e) {
			System.out.println("Browser is not launched properly");
		}
	}
	/* To enter text by ID
	 * @param id-id  of the webelement
	 * @param value - value to be entered in the webelement
	 */

	public void enterTextById(String value,String id) throws Exception{

		try {//driver.findElement(By.id(id)).clear();
		//Thread.sleep(30);
			driver.findElement(By.id(id)).sendKeys(value);
			System.out.println("Entered the text");
		} catch (NoSuchElementException e) {
			System.out.println("Element with id"+id+"not found");
		}	
		catch (WebDriverException e) {
			System.out.println("Browser is not launched properly");
		}
	}
	/* To click a button by classname
	 * @param name-name  of the webelement to be clicked
	 */
	public void clickButtonByClassname(String name){
		try {
			driver.findElementByClassName(name).click();
			System.out.println("clicked by class name"+ " " + name);
		} catch (NoSuchElementException e) {
			System.out.println("The classname is not found" +name);
		}
		catch (WebDriverException e) {
			System.out.println("Browser is not launched properly");
		}
	}
	/* To click a element by name
	 * @param name-name  of the webelement to be clicked
	 */
	public void findElementByName(String name){
		try {
			driver.findElementByName(name).click();
		} catch (NoSuchElementException e) {
			System.out.println("No Such Element is not available");	
		}
		catch (WebDriverException e) {
			System.out.println("Browser is not launched properly");
		}
	}
	/* To click a element by linktext
	 * @param linktext-linktext  of the webelement to be clicked
	 */
	public void clickByLinkText(String linktext){
		try{
			driver.findElementByLinkText(linktext).click();	
			System.out.println("Th elemen has been clicked" + " " + linktext);
		}
		catch (NoSuchElementException e) {
			System.out.println("Element is not available");	
		}
		catch (WebDriverException e) {
			System.out.println("Browser is not launched properly");
		}
	}
	
	/* To verify the text
	 * @param id-id of the element to verify the text
	 * @param textvalue-textvalue to be verified
	 * @return returns a boolean value to state whether the textalue is same or not
	 */
	public boolean verifyTextByID(String textvalue,String id){
		boolean flag=false;
		try {
			if( driver.findElementById(id).getText().contains(textvalue))
				flag=true;
			System.out.println("Value is present");
		} catch (NoSuchElementException e) {
			System.out.println("Element is not available");	
		}
		catch (WebDriverException e) {
			System.out.println("Browser is not launched properly");
		}
		return flag;
			
			
	}
	/* To enter a text by xpath
	 * @param xpath-xpath  of the webelement to enter  value
	 */
		public void enterTextByXpath(String xpath,String value) {
		try {
			driver.findElementByXPath(xpath).sendKeys(value);
		} catch (NoSuchElementException e) {
			System.out.println("Xpath is not available");	
		}
		catch (WebDriverException e) {
			System.out.println("Browser is not launched properly");
		}
	}
	/* To get a text by name
     * @param name-name  of the webelement to get text
     * @return String value of the text 
	*/
	public String getTextByName(String name){
		String textvalue="";
		try {
			textvalue=driver.findElementByName(name).getText();
            System.out.println("The text value received is " + " "+textvalue);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			System.out.println("No such elemenent found with the name: " +name);
		}
		catch (WebDriverException e) {
			System.out.println("Browser is not launched properly");
		}
		return textvalue;
	}
	/*public String readInput(String fileName, int rowNo, int cellNo) throws IOException{

		FileInputStream fis = new FileInputStream(new File ("./data/"+fileName));

		HSSFWorkbook workbook = new HSSFWorkbook(fis);

		HSSFSheet sheet = workbook.getSheetAt(0);

		HSSFRow currentRow = sheet.getRow(rowNo);

		String currentCellValue = currentRow.getCell(cellNo).getStringCellValue();	
		System.out.println(currentCellValue);

		return currentCellValue;

	}*/
	
	
	/*To readinput from the excel
	 * @param fileName- file to be read
	 * @return String array of the values in excel
	 */
	public String[][] readInput(String fileName) throws IOException{

		String [][] currentCellValue = null;
		FileInputStream fis = new FileInputStream(new File ("./data/"+fileName));
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
		HSSFSheet sheet = workbook.getSheetAt(0);
		int a=0,b;
		int totalNoOfRows=sheet.getPhysicalNumberOfRows();
		int totalNoOfColumns=sheet.getRow(1).getPhysicalNumberOfCells();
		currentCellValue=new String[totalNoOfRows][totalNoOfColumns];
		DataFormatter formatter=new DataFormatter();

		System.out.println(sheet.getPhysicalNumberOfRows());
		System.out.println(sheet.getRow(0).getLastCellNum());

		try {
			for (int startRow= 1; startRow <totalNoOfRows; startRow++,a++){
				b=0;
				for (int startColumn= 0;startColumn <totalNoOfColumns;startColumn++,b++) 
				{

					currentCellValue[a][b] = formatter.formatCellValue(sheet.getRow(startRow).getCell(startColumn));	
					String l = currentCellValue[a][b];
					System.out.println(l);
				}

				System.out.println("");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("");
		return currentCellValue;

	}

/*To get text by id
 * @param id- id of the element to get text
 * @return String- returns the text
 */
	public String getTextById(String id){
		String idText = null;
		try {
			idText = driver.findElement(By.id(id)).getText();
            System.out.println("The text has been received"+ " "+idText);
		} catch (NoSuchElementException e) {
			System.out.println("Element could not be found:"+id);
		}
		catch (WebDriverException e) {
			System.out.println("Browser is not launched properly");
		}
		return idText;
	}
	/*To get value by Xpath
	 * @param xpath- xpath of the element to get value
	 * @return String- returns the text
	 */
	public String getValueByXpath(String xpath){
		String id = null;
		try {
			id = driver.findElement(By.xpath(xpath)).getText();
			System.out.println("Value Received"+ " " +id);
		} catch (NoSuchElementException e) {
			System.out.println("Element could not be found:"+xpath);
		}
		catch (WebDriverException e) {
			System.out.println("Browser is not launched properly");
		}
		return id;
	}
/*To get the printed text
 * 
 */
	public void getTextPrint()
	{
		String value = getid("viewLead_companyName_sp");

		//String[] a = value.split("\\(", 5);
		System.out.println(value);

		String val = getid("viewLead_companyName_sp");
		//getValueByXpath("//*[@id='viewLead_companyName_sp']");
		System.out.println("value : " + val.substring(val.indexOf("(")+1, val.indexOf(")")));

	}
/*To get the string from excel
 * @param string-string 
 */
	private String getid(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	/*To login the application
	 * @param fileName-fileName of the excel to be read
	 */
	public void login(String fileName) throws Exception{
		   String [][] login = readInput(fileName);
		  
	 enterTextById(login[0][0],"username");
		enterTextById(login[0][1],"password");
		clickButtonByClassname("decorativeSubmit");
		}
	/* To handle windows
	 * @param parentWindow-Xpath  of the webelement to get the text
	 * @param expectedText- text value to be compared with
	 */
	public void handleWindows(String parentWindow){
		Set<String> wHandles=driver.getWindowHandles();


		for (String CurrentPopUpWindow : wHandles) {
			if (!CurrentPopUpWindow .equals(parentWindow)) {
				driver.switchTo().window(CurrentPopUpWindow);
			
			}
}
	}
	
}