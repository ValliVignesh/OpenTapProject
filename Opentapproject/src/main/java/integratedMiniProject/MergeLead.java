package integratedMiniProject;



import org.junit.Test;

public class MergeLead extends homeworkweek4.WrapperMethods{
	@Test
	public void merge() throws Exception{
		launchBrowser("http://demo1.opentaps.org/opentaps/control/main","firefox");
		login("LoginOpenTaps.xls");
		clickByLinkText("CRM/SFA");
		//clickButtonByXpath("//*[@id='login']/p[3]/input");
		//clickButtonByXpath("//*[@id='button']/a/img");x
		clickByLinkText("Leads");
		clickByLinkText("Merge Leads");
		String parent_Window=driver.getWindowHandle();
		clickButtonByXpath("(//img[@alt='Lookup'])[1]");

		handleWindows(parent_Window);
		String mergeLead[][]=readInput("MergeLead.xls");
		enterTextByXpath("//input[contains(@name,'id')]", mergeLead[0][0]);
		clickButtonByXpath("(//button[@class='x-btn-text'])[1]");
		clickByLinkText(mergeLead[0][0]);
		driver.switchTo().window(parent_Window);

		clickButtonByXpath("(//img[@alt='Lookup'])[2]");

		handleWindows(parent_Window);
		enterByName("id",mergeLead[0][1]);
		clickButtonByXpath("(//button[@class='x-btn-text'])[1]");
		clickByLinkText(mergeLead[0][1]);
		driver.switchTo().window(parent_Window);
		clickByLinkText("Merge");
		acceptAlert();

		clickByLinkText("Find Leads");
		enterByName("id",mergeLead[0][0]);
		clickButtonByXpath("(//button[@class='x-btn-text'])[7]");
		Thread.sleep(3000);
		verifyText("//div[@class='x-toolbar x-small-editor']/div", "No records to display");

	}
}
