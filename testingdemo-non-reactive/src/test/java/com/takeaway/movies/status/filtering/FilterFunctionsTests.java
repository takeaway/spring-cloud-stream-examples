package com.takeaway.movies.status.filtering;


import com.takeaway.movies.model.Movie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class FilterFunctionsTests {

    private static final int FILTER_VALUE = 3;
    private static final int VALID_MOVIE_RATING = 9;
    private static final String VALID_MOVIE_NAME = "Batman Begins";
    private static final Object NO_OUTPUT = null; // for readability

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
        when(properties.getRating()).thenReturn(FILTER_VALUE);
    }


    @Test
    void goodMovieIsNotFilteredOut() {
        final var testMovie = new Movie(VALID_MOVIE_NAME, VALID_MOVIE_RATING);
        assertThat(functions.filter(properties)
                .apply(testMovie)).isEqualTo(testMovie);
    }


    @ParameterizedTest(name = "run #{index} with rating [{arguments}]")
    @ValueSource(ints = {
            3, 10, Integer.MAX_VALUE
    })
    void passWhenRatingIsHighEnough(int rating) {
        final var movie = new Movie("The Godfather", rating);
        assertThat(functions.filter(properties)
                .apply(movie)).isEqualTo(movie);
    }


    @ParameterizedTest
    @ValueSource(ints = {
            Integer.MIN_VALUE, 0, 1, 2
    })
    void filterWhenRatingIsLow(int rating) {
        final var movie = new Movie("Scary Movie", rating);
        assertThat(functions.filter(properties)
                .apply(movie)).isEqualTo(NO_OUTPUT);
    }

    // The below test can pass in a String as a movie due to the static factory method "from" in the Movie class
    @DisplayName("Test filter function with parameters using static factory method \"from\"")
    @ParameterizedTest(name = "Movie \"{0}\" with rating {1} should be {2}")
    @CsvSource(value = {"Batman Begins:8:Batman Begins/8",
                        "Scary Movie:2:null"}
            , delimiter = ':' )
    void passWhenRatingIsHighEnoughFilterWhenRatingIsLow(String name, int rating, Movie result) {
        final var movie = new Movie(name, rating);
        assertThat(functions.filter(properties)
                .apply(movie)).isEqualTo(result);
    }




}
