### Spring Security  examples

![Java CI with Maven](https://github.com/nicolasard/SpringSecurityStuff/workflows/Java%20CI%20with%20Maven/badge.svg)

#### Example 1 
This is the very basic Spring Batch security that allows you to secure 
a page.

By default using spring batch you will have a /login and /logout pre-formated
that you can use to login and logout.

In this example we have the unsecure page /health and we are applying security 
rules to every other page.

#### Example 2 
This example uses a DB to store the user and passwords. (This example is taken from
https://www.baeldung.com/spring-security-jdbc-authentication).
It uses a H2 database. 

This example also contains a DockerFile that allows you to create a docker image and a 
Helm chart that allows you to deploy in kubernetes the image.

#### Example 3 
This is an example that extends the service and allows you to register a new user.
This is example was taken from https://github.com/kamer/spring-boot-user-registration. 

#### Compiling
To compile all the project at once you have the root pom.xml