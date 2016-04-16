package integratedMiniProject;




import org.junit.Test;

import homeworkweek4.WrapperMethods;


public class DeleteLeads extends WrapperMethods{

	@Test
	public void deletelead() throws Exception{
		
launchBrowser("http://demo1.opentaps.org/opentaps/control/main", "chrome");
login("LoginOpenTaps.xls");
clickByLinkText("CRM/SFA");
clickByLinkText("Leads");
clickByLinkText("Find Leads");
clickButtonByXpath("(//*[@class='x-tab-strip-text '])[2]");
String deletelead[][]=readInput("DeleteLead.xls");
enterByName("phoneNumber", deletelead[0][0]);		
clickButtonByXpath("//button[contains(text(),'Find Leads')]");
clickByLinkText(deletelead[0][1]); 
clickByLinkText("Delete");
clickByLinkText("Find Leads");				
enterByName("id", deletelead[0][1]);
clickButtonByXpath("//button[contains(text(),'Find Leads')]");
Thread.sleep(3000);
verifyText("//div[@class='x-panel-bbar']/div/div", "No records to display");
}
}
