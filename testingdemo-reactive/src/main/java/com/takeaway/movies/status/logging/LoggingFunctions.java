package com.takeaway.movies.status.logging;

import com.takeaway.movies.model.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Configuration
public class LoggingFunctions {

    @Bean
    Function<Flux<Movie>, Flux<Movie>> logIncoming() {
        return message -> {
            log.info("Incoming: {}", message);
            return message;
        };
    }

    @Bean
    Function<Flux<Movie>, Flux<Movie>> logOutgoing() {
        return message -> {
                log.info("Outgoing: {}", message);
                return message;
        };
    }

}
