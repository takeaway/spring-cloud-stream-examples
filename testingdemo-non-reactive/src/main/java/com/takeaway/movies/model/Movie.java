package com.takeaway.movies.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

public class Movie {

    private String name;
    private int rating;
    private String description;

    @JsonCreator
    public Movie(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

    public static Movie from(String inputmovie) {
        if ("null".equalsIgnoreCase(inputmovie)) {
            return null;
        }
        String[] input = inputmovie.split("/");
        return new Movie(input[0], Integer.parseInt(input[1]));
    }


    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        if (description==null) {
            return "Movie{" +
                    "name='" + name + '\'' +
                    ", rating=" + rating +
                    '}';
        }
        return "Movie{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                ", description=" + description +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return rating == movie.rating &&
                Objects.equals(name, movie.name) && Objects.equals(description, movie.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rating, description);
    }


}
