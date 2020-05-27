package com.example.demoprocessor;

import java.util.Objects;

public class Movie {

    private String name;
    private int rating;

    public Movie() {
    }

    public Movie(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return rating == movie.rating &&
                Objects.equals(name, movie.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rating);
    }
}
