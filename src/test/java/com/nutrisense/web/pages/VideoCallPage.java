package com.nutrisense.web.pages;


import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.junit.Assert;

import com.microsoft.playwright.ElementHandle.FillOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Locator.IsVisibleOptions;
import com.microsoft.playwright.Locator.WaitForOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.WaitForConditionOptions;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import com.nutrisense.web.utils.Utility;

public class VideoCallPage extends BasePage {

    public VideoCallPage(Page page) {
        super(page);
    }

    public void selectGoal(String goal) {
        page.getByText(goal).click();
    }

    public void selectInsurance(String insurance) {
        page.locator("label:has-text('"+insurance+"')").click();
    }

    public void fillEmail(String email) {
    	if(email.equalsIgnoreCase("random"))
    		email = "VC_"+Utility.generateRandomString()+"@nutrisense.io";
        page.locator("input[name='email']").fill(email);
    }

    public void selectState(String state) {
    	page.locator("input[aria-autocomplete='list']").click();	
    	if(state.equalsIgnoreCase("random")) {
    		List<String> allStates = page.getByRole(AriaRole.OPTION).allTextContents();
    		int randomIndex = new Random().nextInt(allStates.size());
            state = allStates.get(randomIndex);
    	}
    		
        page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(state)).nth(0).click();
    }

    public void scheduleCall() {
        page.getByText("Schedule Your Session!Select a time that works for you to meet with an expert nu").click();
    }

    public void selectCallTime(String time) {
    	if(time.equalsIgnoreCase("random")) {
			page.locator("//p[text()='Available slots']/..//button").nth(0).waitFor();
    		Locator times = page.locator("//p[text()='Available slots']/..//button[not(contains(text(),'See more'))]");
    		int randomIndex = new Random().nextInt(times.count());
    		time = times.nth(randomIndex).textContent();
    	}
    		
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(time)).click();
        if(!page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Confirm")).isVisible())
        	page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(time)).click();
    }

    public void fillAppointmentAgenda(String agenda) {
        page.getByPlaceholder("Please elaborate on your goals and expectations to help us serve you better").fill(agenda);
    }

    public void verifyCoverage() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Verify my coverage")).click();
    }

    public void fillFirstName(String firstName) {
        page.locator("input[name='firstName']").fill(firstName);
    }

    public void fillLastName(String lastName) {
        page.locator("input[name='lastName']").fill(lastName);
    }

    public void chooseDate(String date) {
    	page.locator("input[placeholder='MM/DD/YYYY']").fill("01/01/1990");
    }

    public void fillAnswer(String answer) {
        page.getByPlaceholder("Type your answer").fill(answer);
    }

    public void selectSelfOption() {
        page.locator("label:has-text('Self')").click();
    }

    public void fillAddress(String address) throws InterruptedException {
        page.getByRole(AriaRole.COMBOBOX).fill(address);
        page.getByRole(AriaRole.OPTION).nth(0).waitFor();
        List<String> addressOptions = page.getByRole(AriaRole.OPTION).allTextContents();
        page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(addressOptions.get(0))).click();
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        Thread.sleep(3000);
    }

	public void verifyMessage(String message) {
//		page.waitForLoadState(LoadState.DOMCONTENTLOADED);
		boolean messageDisplayed = page.getByText(Pattern.compile("^"+ message)).isVisible();
		if(!messageDisplayed)
		{
			page.getByText(Pattern.compile("^"+ message)).waitFor();
			messageDisplayed = page.getByText(Pattern.compile("^"+ message)).isVisible();
		}
		Assert.assertTrue(message + " header is not displaying.", messageDisplayed);
	}

	public void fillMemberID(String memID) {
		page.locator("input[placeholder='Type your answer']").fill(Utility.generateRandomString());
	}

	public void fillGroupNumber(String grNum) {
		page.locator("input[placeholder='Type your answer']").fill(Utility.generateRandomString());
	}

	public void enterCardDetails() throws InterruptedException {
		Thread.sleep(3000);
		page.frameLocator("[title='Secure payment input frame']").locator("input[name='number']").fill("4242424242424242");
		page.frameLocator("[title='Secure payment input frame']").locator("input[name='expiry']").fill("08 / 29");
		page.frameLocator("[title='Secure payment input frame']").locator("input[name='cvc']").fill("888");
	}
}
