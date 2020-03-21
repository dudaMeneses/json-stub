[![CircleCI](https://circleci.com/gh/dudaMeneses/json-stub.svg?style=shield)](https://circleci.com/gh/dudaMeneses/json-stub/master)
[![CodeFactor](https://www.codefactor.io/repository/github/dudameneses/json-stub/badge)](https://www.codefactor.io/repository/github/dudameneses/json-stub)

# Power of Attorney
This is a version of assignment done for Rabobank.

## Candidate steps
In the very beginning I took some time to figure out how the application works and what it should do. 
After this analysis, I decided to follow the steps below:
- [x] Create entity mappings according existent test files
- [x] Apply spring boot to provide proper services
- [x] Create a separation of concerns considering the context bounds of application functionality
- [x] Cover code with unit tests to prepare ground for refactoring

Once it was done, I applied the logic on the exercise as some different stories:
- [x] Retrieve only authorized data for user
- [x] Retrieve only active products and accounts
- [ ] Expose over HTTPS

Also I mapped the following points to improvement
- [x] Swagger by annotations
- [x] JaCoCo report
- [x] Add Circle-CI pipeline to validate application
- [x] Add CodeFactor to check code quality
- [ ] Point to real database instead mocked data

## Important considerations

- Once I am using MongoDB on this test, data redundancy is expected inside entities
- I decided to change data to mock to better manipulate it. Also, I fixed the account ids mappings

## Pre-Requirements

- [jdk11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
- [maven](https://maven.apache.org/download.cgi)

## Tech stack

- Spring Boot (Webflux)
- MongoDB

## How to's
### Run
1. `mvn clean spring-boot:run`
2. [Local Swagger](http://localhost:8080/swagger-ui.html)

### Test
1. `mvn clean test`

### Build with report
1. `mvn clean package`
2. You can access the report at `C:/{project_path}/target/site/jacoco/index.html`
 
