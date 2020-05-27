package com.example.demoproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

@SpringBootApplication
public class DemoproducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoproducerApplication.class, args);
    }

    @Bean
    public Supplier<Movie> supplierBean() {
        return () -> {
            List<Movie> movies = new LinkedList<>(List.of(
                    new Movie("Batman Begins", 7),
                    new Movie("Attack of the Killer Tomatoes", 2),
                    new Movie("Lego Batman", 8)
            ));
            Collections.shuffle(movies);
            return movies.get(0);
        };
    }
}
