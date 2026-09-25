Feature: Create an user profile

  Scenario: User registers a new customer from the home page
    Given User is on the login page of the site and click link create
    When User clicks on create button and select costumers
    When enters the info in the third party creation form
    Then it shows a success message

