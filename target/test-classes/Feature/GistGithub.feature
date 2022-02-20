Feature: Create, See List, Edit, and Delete Gist

	Scenario Outline: Create Gist
    Given User is on gist homepage
    When User navigate to sign in page
    And User fill <username> and <password>
    And User click sign in button
    Then User logged in to gist
    When User click create new gist button
    And User fill new gist name
    And User fill gist content
    And User choose to create public gist
    And User click create public gist button
    Then User see that gist has been created
    
	Examples:
		|username		|password			|
		|arbiminanda|P3rs!bjaya10	|

	Scenario Outline: See List Gist
    Given User is on gist homepage
    When User navigate to sign in page
    And User fill <username> and <password>
    And User click sign in button
    Then User logged in to gist
    When User click on profile menu
    And User click your gists
    Then User see the list of gists
    
	Examples:
		|username		|password			|
		|arbiminanda|P3rs!bjaya10	|
		
	Scenario Outline: Edit Gist
    Given User is on gist homepage
    When User navigate to sign in page
    And User fill <username> and <password>
    And User click sign in button
    Then User logged in to gist
    When User click on profile menu
    And User click your gists
    Then User see the list of gists
    When User click on spesific gist
    And User click on edit gist
    And User edit gist content
    And User click update gist button
    Then User see gist edited
    
	Examples:
		|username		|password			|
		|arbiminanda|P3rs!bjaya10	|
		
	Scenario Outline: Delete Gist
    Given User is on gist homepage
    When User navigate to sign in page
    And User fill <username> and <password>
    And User click sign in button
    Then User logged in to gist
    When User click on profile menu
    And User click your gists
    Then User see the list of gists
    When User click on spesific gist
    And User click on delete gist
    And User confirm delete alert
    
	Examples:
		|username		|password			|
		|arbiminanda|P3rs!bjaya10	|