Feature: Viceo Call For Insurance

  Scenario: Verify the video call scheduling path
    Given User opens the video call url
    When User selects goal "Weight loss"
    And User clicks on "Continue" button
    And User selects the insurance "Aetna"
    And User clicks on "Continue" button
    #Then User verify the message "We've got a plan for you!" disaplayed
    
    #When User clicks on "Continue" button
    And User enters "random" into the email field
    And User clicks on "Continue" button
    And User selects "random" state option
    And User clicks on "Continue" button
    #Then User verify the message "Schedule Your Session!" disaplayed

    #When User clicks on "Schedule Call" button
    And User selects "random" avaiable time
    And User clicks on "Confirm" button
    And User enters "Something about the goal" into the goals text field
    And User clicks on "Continue" button
    #Then User verify the message "We're excited for you to start your health journey" disaplayed
    
    When User clicks on "Verify my coverage" button
    And User enters "Automation" into the firstName field
    And User enters "User" into the lastName field
    And User clicks on "Continue" button
    And User selects the DOB "greater than 18" years
    And User clicks on "Next" button
    
    And User enters "random" into the Insurance Member ID field
    And User clicks on "Next" button
    And User enters "random" into the Group Number field
    And User clicks on "Next" button
    And User select the primary insurance policyholder as "self"
    And User clicks on "Next" button
    And User enters "45 East 45th Street" into the address field
    And User clicks on "Continue" button
    
    And User enters valid card details
    And User clicks on "Confirm appointment" button
    Then User verify the message "Appointment confirmed" disaplayed
