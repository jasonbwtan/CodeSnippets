# Quick Poll App
Taken from examples in Spring REST (2015)

## Requirements
 * Users interact with QuickPoll services to create new polls
 * Each poll contains a set of options that are provided during poll creation
 * Options inside a poll can be updated at a later point
 * To keep things simple, QuickPoll restricts voting on a single option
 * Participants can cast any number of votes
 * Results of a poll can be viewed by anyone

## Steps
 * Resource Identification
 * Resource Representation
 * Endpoint Identification
 * Verb/Action Identification
 
### Resource Identification - extract nouns
User			Singleton User Resource
Users			Collection User Resource
Poll			Singleton Poll Resource			To make a poll
Polls			Collection Poll Resource		To query groups of polls
Vote			Singleton Vote Resource
Votes			Collection Vote Resource		To retrieve all votes for a given poll
ComputeResult	Count Processing Resource		To represent the outcome of a poll

### Resource Representation
A poll can be represented as
 * an id
 * a question
 * vote options made up of option objects (an option for voting with an id and value)

A vote can be represented as
 * an id
 * a reference to a vote option
 
A ComputeResult can be represented as
 * total votes
 * an array of counts for each vote option
 * a winner possibly?
 
 
### Endpoint identification
Conventions
 * if its an api for a website consider company/api or api.company/
 * name resource endpoints using plural nouns for resource collections
 * use uri hierarchys. a poll has votes therefore we should group vote actions from polls
 
 /users
 /users/{userId}
 /polls
 /polls/{pollId}
 /polls/{pollId}/votes
 /polls/{pollId}/votes/{voteId}
 /computeresult?{pollId}=
 
### Action Identification

#### Poll object 
GET		/polls							Retrieves all available polls
POST	/polls							Creates a new poll
PUT		/polls							Forbidden action
Delete	/polls							Forbidden action
GET		/polls/{pollId}					Retrieves an existing poll
POST	/polls/{pollId}					Forbidden
PUT		/polls/{pollId}					Updates an existing poll
Delete	/polls/{pollId}					Deletes an existing poll

#### Vote object
GET		/polls/{pollId}/votes			Retrieves all available votes for a given poll
POST	/polls/{pollId}/votes			Creates a new vote
PUT		/polls/{pollId}/votes			Forbidden action
Delete	/polls/{pollId}/votes			Forbidden action
GET		/polls/{pollId}/votes/{voteId}	Retrieves an existing vote	
POST	/polls/{pollId}/votes/{voteId}	Forbidden
PUT		/polls/{pollId}/votes/{voteId}	Forbidden as a casted vote can't be updated according to our 										requirements
Delete	/polls/{pollId}/votes/{voteId}	Forbidden as a casted vote can't be deleted according to our requirements

#### ComputeResult object
GET		/computeresult					Returns vote count

### Architecture

* Web API layer
* Domain Layer - domain objects
* Repository/Data Access Layer
* No service layer - this is for coarse-grained API methods

Coarse-grained - larger components than fine-grained, large subcomponents. Simply wraps one or more fine-grained services together into a more coarse­-grained operation.

Fine-grained - smaller components of which the larger ones are composed, lower­level service

## Spring Features
### Exception handling.
Spring Boot follows this practice and includes the following error format:
{
  "timestamp": 1453637876524,
  "status": 404,
  "error": "Not Found",
  "exception": "com.jason.exception.ResourceNotFoundException",
  "message": "Poll with id 100 not found",
  "path": "/polls/100"
}
 
but we can override this with an error DTO and handler class

@ControllerAdvice
public class RestExceptionHandler {

### Input validation

### Documentation
 * Swagger Config class
 * modified Swagger index.html
 * @Api annotations on controllers e.g @Api(value = "polls", description = "Poll API")

### API versioning
 URI versioning as opposed to headers
 Create a package for each version
 @RequestMapping("/version") to each controller and naming the controller
 
### Pagination
Implemented by PollRepository extends PagingAndSortingRepository<Poll, Long>
http://localhost:8080/v2/polls vs http://localhost:8080/v1/polls
default results returned set to 5

### Sorting
Implemented by PollRepository extends PagingAndSortingRepository<Poll, Long>
http://localhost:8080/v2/polls?sort=question,asc&sort=id,desc

### Security
security in the QuickPoll application to meet the following two requirements:
 * Registered users can create and access polls. This allows us to keep track of accounts, usage, and so on
 * Polls can be deleted only by users with Admin privileges

Spring boot security by default configures basic authentication with password provided in startup logs

 * initial import of users via the default import.sql file 

Basic Auth and oauth2 is on show here

Example oauth
1.curl -u quickpolliOSClient:top_secret -X POST http://localhost:8080/oauth/token -H "Accept: application/json" -d "username=admin&password=admin&grant_type=password"

2. add poll to DB

3. curl -X DELETE http://localhost:8080/oauth2/v3/polls/1 -H "Authorization: Bearer 0993abdc-0813-44cd-bb5b-5395462e846f"

4. verify: curl -u mickey:cheese http://localhost:8080/v3/polls

### Spring Testing
Integration tests
Mocks

### HATEOAS
HATEOAS, a constraint that allows us to build resilient REST services that function like a website e.g:
{
        "id" : 1,
        "body" : "My first blog post",
        "postdate" : "2015-05-30T21:41:12.650Z",
        "links" : [
                {
                    "rel" : "self",
                    "href" : http://blog.example.com/posts/1,
                    "method" : "GET"
                }
        ]
}

## API Versions
v1: HATEAOS
v2: pagination and sorting
v3: security

