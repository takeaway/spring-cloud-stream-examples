package com.takeaway.movies.status.filtering;


import com.takeaway.movies.model.Movie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class FilterFunctionsTests {
    private static final int VALID_MOVIE_RATING = 9;
    private static final String VALID_MOVIE_NAME = "Batman Begins";

    // We set up these mocks below so we do not have to spin up the entire spring framework for each test

    // creates a dummy of the class so we can use it to set properties that we like for our test
    @Mock
    FilterFunctionsProperties properties;

    // creates an actual instance of the class and injects created mocks
    @InjectMocks
    FilterFunctions functions;

    // set the filter value for each test
    @BeforeEach
    void returnValidRating() {
        when(properties.getRating()).thenReturn(3);
    }


    @Test
    void goodMovieIsNotFilteredOut() {
        Flux<Movie> testFlux = Flux.just(new Movie(VALID_MOVIE_NAME, VALID_MOVIE_RATING));
        Flux<Movie> resultFlux = functions.filter(properties).apply(testFlux);
        StepVerifier.create(resultFlux).expectNext(new Movie(VALID_MOVIE_NAME, VALID_MOVIE_RATING)).verifyComplete();
    }


    @ParameterizedTest(name = "run #{index} with rating [{arguments}]")
    @ValueSource(ints = {
            3, 10, Integer.MAX_VALUE
    })
    void passWhenRatingIsHighEnough(int rating) {
        final var movie = new Movie("The Godfather", rating);
        Flux<Movie> testFlux = Flux.just(movie);
        Flux<Movie> resultFlux = functions.filter(properties).apply(testFlux);
        StepVerifier.create(resultFlux).expectNext(movie).verifyComplete();
    }


    @ParameterizedTest
    @ValueSource(ints = {
            Integer.MIN_VALUE, 0, 1, 2
    })
    void filterWhenRatingIsLow(int rating) {
        final var movie = new Movie("Scary Movie", rating);
        Flux<Movie> testFlux = Flux.just(movie);
        Flux<Movie> resultFlux = functions.filter(properties).apply(testFlux);
        StepVerifier.create(resultFlux).verifyComplete();
    }



}
