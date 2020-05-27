package com.takeaway.movies.status.describing;

import com.takeaway.movies.model.Movie;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DescribeFunctionsTest {

    // We set up these mocks below so we do not have to spin up the entire spring framework for each test

    // creates a dummy of the class so we can use it to set properties that we like for our test
    @Mock
    DescribeFunctionsProperties properties;

    // creates an actual instance of the class and injects created mocks
    @InjectMocks
    DescribeFunctions functions;

    // set the titles for adding descriptions for each test
    @BeforeEach
    void returnValidTitles() {
        when(properties.getBatmanTitles()).thenReturn(Arrays.array("batman", "dark knight"));
    }


    @Test
    void batmanMovieGetsDescription() {
        Movie testMovie = new Movie("Batman returns", 7);
        Movie resultMovie = new Movie("Batman returns", 7);
        resultMovie.setDescription("A movie about Batman!");

        Flux<Movie> testFlux = Flux.just(testMovie);
        Flux<Movie> resultFlux = functions.describeMovie(properties).apply(testFlux);
        StepVerifier.create(resultFlux).expectNext(resultMovie).verifyComplete();
    }


    @Test
    void nonBatmanMovieGetsDescription() {
        Movie testMovie = new Movie("Scary Movie", 2);
        Movie resultMovie = new Movie("Scary Movie", 2);
        resultMovie.setDescription("Some boring movie");

        Flux<Movie> testFlux = Flux.just(testMovie);
        Flux<Movie> resultFlux = functions.describeMovie(properties).apply(testFlux);
        StepVerifier.create(resultFlux).expectNext(resultMovie).verifyComplete();
    }


    @ParameterizedTest
    @CsvSource(value = {"Scary Movie:Some boring movie",
            "Batman:A movie about Batman!",
            "The Dark Knight:A movie about Batman!"}
            , delimiter = ':')
    void correctDescriptionIsAssigned(String input, String expected) {
        final var testMovie = new Movie(input, 8);
        final var resultMovie = new Movie(input, 8);
        resultMovie.setDescription(expected);

        Flux<Movie> testFlux = Flux.just(testMovie);
        Flux<Movie> resultFlux = functions.describeMovie(properties).apply(testFlux);
        StepVerifier.create(resultFlux).expectNext(resultMovie).verifyComplete();
    }


}