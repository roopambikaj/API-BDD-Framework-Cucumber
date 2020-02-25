Feature: Validating nodejs API
	
##	
#Scenario: API should return list of user name
#  Given I call GET "http://localhost:3000"
#  Then The response status should be 200
#  And The response body should be valid json
#  And The value of "name[0]" should be "Elankeeran"
#  And The value list of "name" should have name of "Roopa"
  
  
Scenario Outline: API should be able to add new users
  Given User should be added new through POST "http://localhost:3000"
  When the body should contain "<Name>" and "<Age>"
  Then The response status should be 200 with valid Json
  And The response data of "name" should be "<Name>"
  And The response data of "age" should be "<Age>"
  
  Examples:
  | Name 	| 	Age	| 
  | Roopa	| 	33	| 
  | Elan 	| 	36	| 
  | Shee	| 	35	| 
  
  
Scenario: API should be able perform search specific user
  Given User should be search the user new through POST "http://localhost:3000"
  When the body should contain "<Name>" and "<Age>"
  Then The response status should be 200 with valid Json
  And The response data of "name" should be "<Name>"
  And The response data of "age" should be "<Age>" 