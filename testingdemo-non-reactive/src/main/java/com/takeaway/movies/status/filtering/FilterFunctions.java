package com.takeaway.movies.status.filtering;

import com.takeaway.movies.model.Movie;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.Optional;
import java.util.function.Function;

@Configuration
@EnableConfigurationProperties(FilterFunctionsProperties.class)
public class FilterFunctions {

    @Bean
    Function<Movie, Movie> filter(
            FilterFunctionsProperties properties) {
        return movie -> {if (movie.getRating() >= properties.getRating()) {
            return movie;
        }
        else return null;
        };
    }

}
