package integratedMiniProject;


import org.junit.Test;



public class CreateLead2 extends homeworkweek4.WrapperMethods{
	@Test
	public void tesRun() throws Exception{

		launchBrowser("http://demo1.opentaps.org/","firefox");
		login("LoginOpenTaps.xls");
		clickByLinkText("CRM/SFA");
		//clickButtonByXpath("//*[@id='button']/a");
		clickByLinkText("Create Lead");
		String createLead[][]=readInput("CreateLead.xls");
		//System.out.println(createLead[1][0]);
		enterTextById(createLead[0][0],"createLeadForm_companyName");
		enterTextById(createLead[0][1],"createLeadForm_firstName");
		enterTextById(createLead[0][2],"createLeadForm_lastName");
		enterTextById(createLead[0][5], "createLeadForm_primaryEmail");
		//enterTextById(createLead[0][7], "createLeadForm_primaryPhoneCountryCode");
		//enterTextById(createLead[0][8], "createLeadForm_primaryPhoneAreaCode");
		enterTextById(createLead[0][6], "createLeadForm_primaryPhoneNumber");
		String indexSource=createLead[0][3];
		selectValueByIndexUsingID(Integer.parseInt(indexSource),"createLeadForm_dataSourceId");
		String indexMarketing=createLead[0][4];
		selectValueByIndexUsingID(Integer.parseInt(indexMarketing),"createLeadForm_marketingCampaignId");
		clickButtonByClassname("smallSubmit");
		String value = getTextById("viewLead_companyName_sp");
		System.out.println(value);
		//getValueByXpath("//*[@id='viewLead_companyName_sp']");
		int index1=value.indexOf("(");
		int index2=value.indexOf(")");	
		System.out.println(value.substring(index1, index2));
		



	}



}
