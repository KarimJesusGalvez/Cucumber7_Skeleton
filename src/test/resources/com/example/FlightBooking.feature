Feature: I book a flight in the iberia.com domain

  Background:
    Given I navigate to iberia.com domain
    And I accept all cookies

  Scenario: I check a date
  Given I am in the main page
  When I write the data
  Then I check the data and submit it
  And see flight availability

#  Scenario Outline: I check a date
#  Given I am in the main page
#  When I write the data
#  Then I check the '<origin>', '<destiny>' and submit it
#  And see flight availability
#
#    Examples:
#      | Origin | Destiny |
#      | Madrid | Paris   |
#      | Zurich | Malaga  |


