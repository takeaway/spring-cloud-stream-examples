package com.takeaway.movies.status.describing;

import com.takeaway.movies.model.Movie;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;


import java.util.Arrays;
import java.util.function.Function;

@Configuration
@EnableConfigurationProperties(DescribeFunctionsProperties.class)
public class DescribeFunctions {


    @Bean
    Function<Flux<Movie>, Flux<Movie>> describeMovie(DescribeFunctionsProperties properties) {
        return flux -> flux.map(movie -> {if (isBatmanMovie(movie, properties)) {
            movie.setDescription("A movie about Batman!");
        }
        else {
            movie.setDescription("Some boring movie");
        }
            return movie;
        });
    }

    private boolean isBatmanMovie(Movie movie, DescribeFunctionsProperties properties) {
        return Arrays.stream(properties.getBatmanTitles())
                .parallel()
                .anyMatch(movie.getName().toLowerCase()::contains);
    }

}
