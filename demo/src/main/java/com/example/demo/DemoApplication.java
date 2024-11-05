package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.service.annotation.GetExchange;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    RestClient restClient(RestClient.Builder builder) {
        return builder.build();
    }
}

class Functions {
    void function() {

        Array<String> arrggsImAPirate;
        List<String> list;

        Function<Integer, Integer> square = this::square;

        Function<Integer, Integer> squareManual = new Function<Integer, Integer>() {

            @Override
            public Integer apply(Integer integer) {
                return integer * integer;
            }
        };

    }

    int square(int x) {
        return x * x;
    }

}

//
//@Configuration
//class MyDataSourceConfig {
//    
//    @Bean
//    DataSource myDataSource() { 
//        return null;
//    }
//}



@RestController
class CustomerController {

    private final CustomerRepository customerRepository;

    CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customers")
    Collection<Customer> customers() {
        return this.customerRepository.findAll();
    }
}


@Component
class CustomerRunner implements ApplicationRunner {

    private final CustomerRepository customerRepository;

    CustomerRunner(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        this.customerRepository.findAll().forEach(System.out::println);
        this.customerRepository.findByName("LouService").forEach(System.out::println);
    }
}


@Component
class MessageRunner implements ApplicationRunner {

    private final String message;

    MessageRunner(@Value("${message}") String message) {
        this.message = message;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("message: " + this.message);
    }

}


// Cora Iberkleid 
// @ciberkleid

@RestController
class CorasDemoController {

    private final RestClient http;

//    // int,float, boolean, char, byte, double, long,..
//    // java.lang.Object 
//    Collection <int> collection ;

    CorasDemoController(RestClient http) {
        this.http = http;
    }

    @GetMapping("/delay")
    String delay() {
        return this.http.get().uri("https://httpbin.org/delay/5").retrieve().body(String.class);
    }
}






/*
@Component
class MyCustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        return Health.down().build();
    }
}*/


interface HttpClient {

    @GetExchange("https://adobe.com/customers")
    Collection<Customer> getCustomers();
}

// DOMAIN DRIVEN DESIGN 
interface CustomerRepository extends ListCrudRepository<Customer, Integer> {

    //    // select * from customer where name = ?
//    @Query("""
//        select * from customer where name = :name
//    """)
    Collection<Customer> findByName(String name);
}

record Customer(@Id int id, String name, Set<Cart> carts) {
}

record Cart(@Id int id, Set<LineItem> lineItems) {
}

record LineItem(@Id int id, String sku) {
}


// DATA ORIENTED PROGRAMMING
// - sealed types
// - pattern matching
// - records
// - smart switch
/*

sealed interface Loan permits SecuredLoan, UnsecuredLoan {
}

final class SecuredLoan implements Loan {
}

record UnsecuredLoan(float interest) implements Loan {
}

class Loans {
    
    String displayMessageFor(Loan loan) {
        return switch (loan) {
            case UnsecuredLoan(var interest) -> "ouch! that interest rate (" + interest + "%) is going to hurt!";
            case SecuredLoan sl -> "good job. nice loan";
        };
    }
}
*/

