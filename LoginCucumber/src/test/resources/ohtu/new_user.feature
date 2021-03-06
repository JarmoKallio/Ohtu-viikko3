Feature: A new user account can be created if a proper unused username and password are given

  Scenario: creation succesful with correct username and password
        Given command new user is selected
        When  username "arm" and password "chair" are entered
        Then  system will respond with "new user registered"

    Scenario: creation fails with correct username and too short password
        Given command new user is selected
        When  username "arm" and password "chair" are entered
        Then  system will respond with ""

    Scenario: creation fails with correct username and password consisting of letters
        Given command new user is selected
        When  ...
        Then  ...

    Scenario: creation fails with too short username and valid passord
        Given command new user is selected
        When  ...
        Then  ...

    Scenario: creation fails with already taken username and valid pasword
        Given command new user is selected
        When  ...
        Then  ...
    
    Scenario: can login with succesfully generated account
        Given user "eero" with password "salainen1" is created
        And   command login is selected
        When  ...
        Then  ...  

    Scenario: can not login with account that is not succesfully created
        Given user "aa" with password "aa" is created
        And   command login is selected
        When  ...
        Then  ... 