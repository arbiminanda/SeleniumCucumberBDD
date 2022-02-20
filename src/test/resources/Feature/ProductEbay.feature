Feature: Access Product Ebay

	Scenario Outline: By search
    Given User is on ebay homepage
    When User fill search <keyword>
    And User choose category for search product
    And User click search submit button
    Then Page loaded and user match the search results with <keyword>
    
	Examples:
		|keyword|
		|MacBook| 