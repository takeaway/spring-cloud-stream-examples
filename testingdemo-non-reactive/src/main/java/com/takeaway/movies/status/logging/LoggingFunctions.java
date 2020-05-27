package com.takeaway.movies.status.logging;

import com.takeaway.movies.model.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Configuration
public class LoggingFunctions {

    @Bean
    Function<Movie, Movie> logIncoming() {
        return message -> {
            log.info("Incoming: {}", message);
            return message;
        };
    }

    @Bean
    Function<Movie, Movie> logOutgoing() {
        return message -> {
            log.info("Outgoing: {}", message);
            return message;
        };
    }

    /*
    @Bean
    Function<Optional<Movie>, Movie> logOutgoing() {
        return message -> {
            if (message.isPresent()) {
                log.info("Outgoing: {}", message);
                return message.get();
            }
            return null;
        };
    }

     */

}
