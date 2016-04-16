package integratedMiniProject;


import org.junit.Test;

import homeworkweek4.WrapperMethods;




public class EditTaps extends WrapperMethods{
	@Test
	public void edittaps() throws Exception {

		launchBrowser("http://demo1.opentaps.org/", "chrome");
		login("LoginOpenTaps.xls");
		clickButtonByXpath("//*[@id='button']/a");		
		Thread.sleep(30);
		// edit 
		clickByLinkText("Leads");
		clickButtonByXpath("//a[contains(text(),'Find Leads')]");
		String editlead[][]=readInput("EditLead.xls");
		enterTextByXpath("//*[@id='ext-gen248']", editlead[0][0]);
		clickButtonByXpath("//button[contains(text(),'Find Leads')]");
		//Thread.sleep(30);
		clickByLinkText(editlead[0][1]);
		verifyTitle("View Lead | opentaps CRM");
		clickButtonByXpath("//a[contains(text(),'Edit')]");
		selectValueByIndexUsingID(Integer.parseInt(editlead[0][2]), "addDataSourceForm_dataSourceId");
		clickButtonByXpath("//input[@value='Add']");
		String textsource=getTextById("addDataSourceForm_dataSourceId");
		selectValueByIndexUsingID(Integer.parseInt(editlead[0][3]),"addMarketingCampaignForm_marketingCampaignId");
		clickButtonByXpath("(//input[@value='Add'])[2]");
		String textmarketingcampaign=getTextById("addMarketingCampaignForm_marketingCampaignId");
		clickButtonByXpath("//input[@value='Update']");
	    verifyTextByID(textsource, "viewLead_dataSources_sp");
	    verifyTextByID(textmarketingcampaign, "viewLead_marketingCampaigns_sp");
	}


}


