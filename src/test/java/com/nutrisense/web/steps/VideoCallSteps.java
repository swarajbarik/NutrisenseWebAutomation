package com.nutrisense.web.steps;

import com.nutrisense.web.pages.VideoCallPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class VideoCallSteps {

    private VideoCallPage videoCallPage;

    public VideoCallSteps() {
        videoCallPage = new VideoCallPage(Hooks.page);
    }

    @Given("User opens the video call url")
    public void user_opens_the_video_call_url() {
    	videoCallPage.navigateTo();
    }
    @When("User selects goal {string}")
    public void user_selects_goal(String goal) {
        videoCallPage.selectGoal(goal);
    }
    @When("User clicks on {string} button")
    public void user_clicks_on_button_button(String btnName) {
        videoCallPage.clickButtonByName(btnName);
    }
    @When("User selects the insurance {string}")
    public void user_selects_the_insurance(String insurance) {
        videoCallPage.selectInsurance(insurance);
    }
    @Then("User verify the message {string} disaplayed")
    public void user_verify_the_message_disaplayed(String message) {
    	videoCallPage.verifyMessage(message);
    }
    
    @When("User enters {string} into the email field")
    public void user_enters_into_the_email_field(String email) {
        videoCallPage.fillEmail(email);
    }
    @When("User selects {string} state option")
    public void user_selects_state_option(String state) {
        videoCallPage.selectState(state);
    }
    @When("User selects {string} avaiable time")
    public void user_selects_avaiable_time(String time) {
        videoCallPage.selectCallTime(time);
    }
    @When("User enters {string} into the goals text field")
    public void user_enters_into_the_goals_text_field(String agenda) {
        videoCallPage.fillAppointmentAgenda(agenda);
    }
    @When("User enters {string} into the firstName field")
    public void user_enters_into_the_first_name_field(String fName) {
        videoCallPage.fillFirstName(fName);
    }
    @When("User enters {string} into the lastName field")
    public void user_enters_into_the_last_name_field(String lName) {
        videoCallPage.fillLastName(lName);
    }
    @When("User selects the DOB {string} years")
    public void user_selects_the_dob_years(String date) {
        videoCallPage.chooseDate(date);
    }
    @When("User enters {string} into the Insurance Member ID field")
    public void user_enters_into_the_insurance_member_id_field(String memID) {
        videoCallPage.fillMemberID(memID);
    }
    @When("User enters {string} into the Group Number field")
    public void user_enters_into_the_group_number_field(String grNum) {
        videoCallPage.fillGroupNumber(grNum);
    }
    @When("User select the primary insurance policyholder as {string}")
    public void user_select_the_primary_insurance_policyholder_as(String string) {
        videoCallPage.selectSelfOption();
    }
    @When("User enters {string} into the address field")
    public void user_enters_into_the_address_field(String address) throws InterruptedException {
        videoCallPage.fillAddress(address);
    }
    @When("User enters valid card details")
    public void user_enters_valid_card_details() throws InterruptedException {
        videoCallPage.enterCardDetails();
    }
}
