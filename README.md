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

To build the docker image run `docker  build --tag nicard/spring-security-example2 .`

Then if you want to install using the helm chart `helm install example2 ./example2-helm-chart`

#### Example 3 
This is an example that extends the service and allows you to register a new user.
This is example was taken from https://github.com/kamer/spring-boot-user-registration. 

#### Example 4
This is a continuation of example 3. It validates the new user email by sending a email
with a token that the user should provide. 

#### Example 5
This is a continuation of example 4. But really here I'm doing a full application. This should have it own repo.

#### Example 6
Here we are not really using springSecurity but we are doing MTLS with at WS.

#### Compiling
To compile all the project at once you have the root pom.xml