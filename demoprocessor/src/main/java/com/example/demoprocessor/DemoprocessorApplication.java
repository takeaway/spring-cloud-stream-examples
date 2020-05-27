package com.example.demoprocessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@SpringBootApplication
@Controller
public class DemoprocessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoprocessorApplication.class, args);
    }

    @Autowired
    private StreamBridge streamBridge;

    @PostMapping("/movies")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void send(@RequestBody Movie movie) {
        streamBridge.send("goodmovies", movie);
    }

    @Bean
    public Function<Flux<Movie>, Flux<Movie>> processorBean() {
        return flux -> flux.map(this::batmanFactor).filter(this::filterByRating);
    }

    private boolean filterByRating(Movie movie) {
        return movie.getRating() >= 6;
    }

    private Movie batmanFactor(Movie movie) {
        if (movie.getName().toLowerCase().contains("batman")) {
            return new Movie(movie.getName(), movie.getRating() * 2);
        } else {
            return movie;
        }
    }
}
