# Power of Attorney Service
This awesome web service provides REST API for accessing power of attorney information of a user:

  - Power of attorney details such as grantee, grantor and account details (`/power-of-attorneys/{id}`)
  - Details for card products authorized by the power of attorney (`/debit-cards/{id}` and `/credit-cards/{id}`)
  - Account details (`/accounts/{id}`)
  - Some developer might have made an error somewhere

1. **To build and run:** use `mvn compile exec:java`
2. **Application runs on:** http://localhost:8080
3. **REST API documentation:** http://localhost:8080/swagger/

## Exercise:

  - Build a REST API presenting aggregated information from different services
  - Only show data that a user is actually authorized for
  - Handle any server errors you might run into gracefully

## Requirements
  - Requirements of the code and functionality is up to the candidate
  - We suggest using Java 11, Spring-Boot & Maven, but using Kotlin or Gradle is also fine
  - Perform whatever validation seems necessary
  - Don't return inactive products or accounts
  - (Optional) Expose the API over HTTPS
 
## Tips
  - Because every candidate has different experience and background, the candidate should decide on how complex code they want to show us
  - If the assignment is unclear, do what you feel is best and focus on the code, not the exercise
  - We look at the quality and readability of code that has been delivered more than if the functionality matches our expectations
  - Impress us!

---
## Candidate steps

In the very beginning I took some time to figure out how the application works and what it should do. 
After this analysis, I decided to follow the steps below:
- [x] Create entity mappings according existent test files
- [x] Apply spring boot to provide proper services
- [x] Create a separation of concerns considering the context bounds of application functionality
- [x] Cover code with unit tests to prepare ground for refactoring

Once it was done, I applied the logic on the exercise as 2 different stories:
- [ ] Retrieve only authorized data for user
- [x] Retrieve only active products and accounts
- [ ] Expose over HTTPS

Also I mapped the following points to improvement
- [ ] Swagger by annotations
- [ ] Expose contracts on build to help teams integrating with application to implement it
- [ ] Jenkinsfile and Dockerfile to CI/CD
- [ ] Point to real database instead mocked data
