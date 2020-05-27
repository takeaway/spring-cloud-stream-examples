package com.takeaway.movies.status.filtering;

import com.takeaway.movies.model.Movie;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;


import java.util.Optional;
import java.util.function.Function;

@Configuration
@EnableConfigurationProperties(FilterFunctionsProperties.class)
public class FilterFunctions {

    @Bean
    Function<Flux<Movie>, Flux<Movie>> filter(
            FilterFunctionsProperties properties) {
        return flux -> flux.filter(movie -> movie.getRating() >= properties.getRating());
    }

}
