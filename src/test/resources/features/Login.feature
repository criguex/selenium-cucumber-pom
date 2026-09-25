Feature: Login

  Scenario: User logs in with valid credentials
    Given user is on the login page of the site
    When enters the credentials
    Then it shows the user profile and your home page

