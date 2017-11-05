# Credit Card Manager

An small application allowing user to create accounts, add credit cards to the system an visualize them.

## Getting Started

These instructions will guide you to setup and run the application.

### Prerequities

You need a Database instance running (MySQL). For other Databases, you may have to update the pom.xml file to add your DB implementation.

### Setup the Database
Step 1 : Run /src/main/resources/scripts/ccmanagement_create_db.sql script
Step 2 : Run /src/main/resources/scripts/ccmanagement_user.sql script
Step 3 : Run /src/main/resources/scripts/ccmanagement_credit_card.sql script
Step 4 : Go to /src/main/resources/application.properties file and update the username and password for your DB

NB : Feeling lazy ? Just do step 1 and change the value of spring.jpa.hibernate.ddl-auto to create

### Run the application

#### Maven
- Go at the root of the project folder and run the command : mvn package
IMPORTANT : The DB information need to be correct

- Execute command : java -jar target/credit-card-manager-0.0.1-SNAPSHOT.jar

- The application is now running

## Frameworks and Libraries used
### Java
#### Spring boot
- https://projects.spring.io/spring-boot/ (Make it easy to run applications and develop micro-services)

##### Thymeleaf
- http://www.thymeleaf.org/ (Server side java templating integrating with Spring)

##### Security
- https://spring.io/guides/gs/securing-web/ (Spring module dealing with security for web applications)

##### JPA
- https://spring.io/guides/gs/accessing-data-jpa/ (Module allowing easy ORM from your application to your DB)

#### MySQL
- https://www.mysql.com/ (Probably the most famous RDBMS)

#### Commons Validator
- https://commons.apache.org/proper/commons-validator/ (allows to use validator classes for CreditCards and many others)

#### Commons Lang
- https://commons.apache.org/proper/commons-lang/ (Utility classes to deal with String objects)

#### Google Gson
- https://github.com/google/gson (Google library to interact with JSon Data)

### Javascript / CSS
#### jQuery
- https://jquery.com/ (Probably the most know JS library of all time, makes the DOM Manipulation a pleasure)

#### Bootstrap
- http://getbootstrap.com/ (CSS and Javascript framework helping to quickly build better looking and responsive web applications)

## Any issue ?
Do not hesitate to contact me through Github (https://github.com/0liv06)