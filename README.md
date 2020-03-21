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
- [ ] Retrieve only authorized data for user
- [x] Retrieve only active products and accounts
- [ ] Expose over HTTPS

Also I mapped the following points to improvement
- [x] Swagger by annotations
- [x] JaCoCo report
- [ ] Expose contracts on build to help teams integrating with application to implement it
- [ ] Jenkinsfile and Dockerfile to CI/CD
- [ ] Docker Compose to have Jenkins and SonarQube running locally
- [ ] Point to real database instead mocked data

## Tech stack
- Spring Boot (Webflux)
- MongoDB
- Docker
- Jenkins

## How to's
### Run
1. `mvn clean spring-boot:run`
2. [Local Swagger](http://localhost:8080/swagger-ui.html)

### Test
1. `mvn clean test`

### Build with report
1. `mvn clean package`
2. You can access the report at `C:/{project_path}/target/site/jacoco/index.html`
 
