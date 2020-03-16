Microserver Sample :

Story: I want to add, update, view, delete, or change what's in the fridge
Constraint: implement a guardrail to prevent more than 12 cans of soda in the fridge at any time
Constraint: there are multiple refrigerators

Requirements :
Publicly-accessible code (i.e. GitLab, GitHub)
Automated testing w/code coverage
Authorization and authentication
Traceability (logging, metrics, performance)


Technologies :
Java 11
Use of a scalable HTTP framework, webflux

**SOLUTION**:

Authentication and Authorization:
	2 users has been registered 
		1. Userid/PW - user/user
		2. Userid/PW - admin/admin

		User "user" is authorized for GET API's. User "admin" is authorized for all API's


Code Coverage: 
	Code coverage is implemented with Jacoco. gradlew jacocoTestReport command will generate the coverage report to /build/coverage-report dir

Log Details:
	 Currently Log is reporting in console.
 
APIs: 

	1.	Method GET:    Path: /fridge/hello 
		Description: Just to display a greeting message.
		
	2.	Method POST:    Path: /fridge/create
		Description: Creates a Fridge with and Unique ID and empty Items. ID is auto incremented from 0 with an increment of 1.
		
	3.	Method GET:    Path: /fridge/{id}
	    Description: Return the fridge details identified by the {id}.
	    
	4.	Method GET:    Path: /fridge/all
		Description: Return all fridge details as a list.
		
	5.	Method PUT:    Path: /fridge/addItem/{id}
	    Description: Add an item to the fridge identified by the {id}. This api takes the item data as a body of the request.
	    Its a JSON object as below
	    
	      {
			"type": ItemType,     Des - Types of item to store
			"quantity": Number    Des-  Amount of the item
          }
	    
	    Currently allowed ItemTypes are :
	        	  APPLE,
	  			  MILK,
	  			  SODA,
	  			  EGG


         Example :
          {
			"type": "SODA",
			"quantity": 10
          }	
            			   
	    Note : For Type SODA , a fridge can not contain more than 12 at a time. If its more than 12 , response will return as a Bad Request.
	    
	6.	Method PUT:    Path: /fridge/removeItem/{id}
		Description: Removes an item from the fridge identified by the {id}. This api takes the item data as a body of the request.
	    Its a JSON object as below
	    
	      {
			"type": ItemType,     Des - Types of item to store
			"quantity": Number    Des-  Amount of the item
          }
	    
	    Currently allowed ItemTypes are :
	        	  APPLE,
	  			  MILK,
	  			  SODA,
	  			  EGG


         Example :
          {
			"type": "SODA",
			"quantity": 10
          }	
 
	7.	Method DELETE:    Path: /fridge/delete/{id}
	    Description: Delete the fridge details identified by the {id}.
