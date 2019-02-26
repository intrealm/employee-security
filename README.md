# Employee Security System
# Dependencies
 Java 8<br/>
 Spring tool suite 4<br/>
Spring boot<br/> 
    nodejs 8<br/>
# Installations
## Web application
    npm install @location webapplication
## Server side application<br/>
      
# Starting up
## Web application
      node server.js or npm start
## Server side application

##Running as a Packaged Application
If you use the Spring Boot Maven or Gradle plugins to create an executable jar, you can run your application using java -jar, as shown in the following example:

$ java -jar target/myapplication-0.0.1-SNAPSHOT.jar
It is also possible to run a packaged application with remote debugging support enabled. Doing so lets you attach a debugger to your packaged application, as shown in the following example:

$ java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n \
       -jar target/myapplication-0.0.1-SNAPSHOT.jar
       
       
       
##Using the Maven Plugin
The Spring Boot Maven plugin includes a run goal that can be used to quickly compile and run your application. Applications run in an exploded form, as they do in your IDE. The following example shows a typical Maven command to run a Spring Boot application:

$ mvn spring-boot:run
You might also want to use the MAVEN_OPTS operating system environment variable, as shown in the following example:

$ export MAVEN_OPTS=-Xmx1024m
      
    
  
  
# Added route serice



