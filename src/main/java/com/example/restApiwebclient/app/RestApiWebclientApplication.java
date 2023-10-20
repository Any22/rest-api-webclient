package com.example.restApiwebclient.app;

import com.example.restApiwebclient.dto.CountryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/***********************************************************************************************************************
 * In Spring Boot, a `CommandLineRunner` is an interface provided by Spring Framework. It is a functional interface that
 * provides a single method: `void run(String... args) throws Exception`. Classes that implement `CommandLineRunner`
 * can be registered as Spring beans in a Spring Boot application context. When the Spring Boot application starts,
 * the `run` method of these `CommandLineRunner` beans will be executed automatically, allowing you
 * to perform certain tasks during the application startup.
 *
 * The purpose of `CommandLineRunner` in Spring Boot can be summarized as follows:
 *
 * 1. **Performing Startup Operations:**
 *    `CommandLineRunner` is often used to perform operations that should be executed once the Spring Boot application
 *    starts. This could include tasks such as initializing data, loading configurations, or preparing the application
 *    environment.
 *
 * 2. **Data Seeding:**
 *    If your application requires some initial data to be loaded into the database or some data to be preprocessed
 *    during application startup, you can use `CommandLineRunner` to seed the data.
 *
 * 3. **Integration with External Systems:**
 *    `CommandLineRunner` can be used to establish connections with external systems, APIs, databases, or other services
 *    that your application depends on. This is useful for ensuring that connections are established and services are
 *    ready to be used when the application starts.
 *
 * 4. **Performing Maintenance Tasks:**
 *    It can be used to schedule and execute maintenance tasks, such as cleaning up temporary files, sending
 *    notifications, or performing health checks on the application's dependencies.
 *
 * Here's an example of a class implementing `CommandLineRunner` in a Spring Boot application:
 *
 * ```java
 * import org.springframework.boot.CommandLineRunner;
 * import org.springframework.stereotype.Component;
 *
 * @Component
 * public class MyCommandLineRunner implements CommandLineRunner {
 *
 *     @Override
 *     public void run(String... args) throws Exception {
 *         // Code inside this method will be executed when the Spring Boot application starts.
 *         System.out.println("Application started, performing startup tasks...");
 *         // Perform startup tasks here
 *     }
 * }
 * ```
 *

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

 @Component
 public class MyCommandLineRunner implements CommandLineRunner {

 @Override
 public void run(String... args) throws Exception {
 // Code inside this method will be executed when the Spring Boot application starts.
 System.out.println("Application started, performing startup tasks...");
 // Perform startup tasks here
 }
 }
 ```

 In this example, `MyCommandLineRunner` is annotated with `@Component`, making it a Spring bean. Its `run` method will
 be automatically invoked when the Spring Boot application starts. You can have multiple classes implementing `CommandLineRunner`
 if you need to execute different tasks at startup.
 *************************************************************************************************************************/

@SpringBootApplication
@Slf4j
public class RestApiWebclientApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RestApiWebclientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        getCountryByName("pakistan");
    }

    public List<CountryDTO> getCountryByName(String countryName) {
        String url = "https://restcountries.com/v3.1/name/{countryName}";

        WebClient webClient = WebClient.create();
        List<CountryDTO> countryDTOList = webClient
                .get()
                .uri(url, countryName)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CountryDTO>>() {})
                .block();

        if (countryDTOList != null && !countryDTOList.isEmpty()) {
            log.info("Country Info: {}", countryDTOList);
        } else {
            log.warn("No country found with the name: {}", countryName);
        }

        return countryDTOList;
    }
}

