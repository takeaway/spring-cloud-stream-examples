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


import static org.assertj.core.api.Assertions.assertThat;
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
        assertThat(functions.describeMovie(properties)
                .apply(testMovie)).isEqualTo(resultMovie);
    }

    @Test
    void nonBatmanMovieGetsDescription() {
        Movie testMovie = new Movie("Scary Movie", 2);
        Movie resultMovie = new Movie("Scary Movie", 2);
        resultMovie.setDescription("Some boring movie");
        assertThat(functions.describeMovie(properties)
                .apply(testMovie)).isEqualTo(resultMovie);
    }


    @ParameterizedTest
    @CsvSource(value = {"Scary Movie:Some boring movie",
            "Batman:A movie about Batman!",
            "The Dark Knight:A movie about Batman!"}
            , delimiter = ':')
    void correctDescriptionIsAssigned(String input, String expected) {
        final var movie = new Movie(input, 8);
        assertThat(functions.describeMovie(properties)
                .apply(movie)
                .getDescription())
                .isEqualTo(expected);
    }


}