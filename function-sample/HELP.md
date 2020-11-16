# Read Me First
The following was discovered as part of building this project:

* The JVM level was changed from '15' to '11', review the [JDK Version Range](https://github.com/spring-projects/spring-framework/wiki/Spring-Framework-Versions#jdk-version-range) on the wiki for more details.
* The original package name 'com.example.function-sample' is invalid and this project uses 'com.example.functionsample' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.0/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.0/gradle-plugin/reference/html/#build-image)
* [Function](https://cloud.spring.io/spring-cloud-function/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)
* [Various sample apps using Spring Cloud Function](https://github.com/spring-cloud/spring-cloud-function/tree/master/spring-cloud-function-samples)

Run the sample
This runs the app and exposes its functions over HTTP (because of spring-cloud-starter-function-webflux is present on classpath)
    java -jar build/libs/*.jar 
you can convert a string to uppercase, like this:
    curl -H "Content-Type: text/plain" localhost:8080/uppercase -d Hello
    curl -H "Content-Type: application/json" localhost:8080/uppercase -d Hello
You can convert multiple strings (a Flux<String>) by separating them with new lines
    // this command it didn't work because it sends the body in 1 message (1 chunk) => onNext(Hello
                                                                                       World)
    curl -H "Content-Type: text/plain" localhost:8080/uppercase -d 'Hello
    World' 
    // this command it didn't work because it ignored the header Transfer-Encoding and it sends the body in 1 message (1 chunk)
    curl -H "Content-Type: application/json" -H "Transfer-Encoding: chunked" localhost:8080/uppercase -d 'Hello
    

    
    


