package implementation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.MovieInput;

import java.util.ArrayList;

public final class Movie {
    private String name;
    private int year;
    private int duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;
    private int numLikes;
    private double rating;
    private int numRatings;
    @JsonIgnore
    private int sumRatings;

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public int getDuration() {
        return duration;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public double getRating() {
        return rating;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public int getSumRatings() {
        return sumRatings;
    }

    public Movie(final MovieBuilder builder) {
        this.name = builder.name;
        this.year = builder.year;
        this.duration = builder.duration;
        this.genres = builder.genres;
        this.actors = builder.actors;
        this.countriesBanned = builder.countriesBanned;
        this.numLikes = builder.numLikes;
        this.rating = builder.rating;
        this.numRatings = builder.numRatings;
        this.sumRatings = builder.sumRatings;
    }

    static class MovieBuilder {
        private String name;
        private int year;
        private int duration;
        private ArrayList<String> genres;
        private ArrayList<String> actors;
        private ArrayList<String> countriesBanned;
        private int numLikes = 0;
        private double rating = 0;
        private int numRatings = 0;
        private int sumRatings = 0;

        MovieBuilder(final MovieInput movie) {
            this.name = movie.getName();
            this.year = movie.getYear();
            this.duration = movie.getDuration();
            this.genres = movie.getGenres();
            this.actors = movie.getActors();
            this.countriesBanned = movie.getCountriesBanned();
        }

        public MovieBuilder numLikes(final int numLikesGiven) {
            this.numLikes = numLikesGiven;
            return this;
        }

        public MovieBuilder rating(final double ratingGiven) {
            this.rating = ratingGiven;
            return this;
        }

        public MovieBuilder numRatings(final int numRatingsGiven) {
            this.numRatings = numRatingsGiven;
            return this;
        }

        public MovieBuilder sumRatings(final int sumRatingsGiven) {
            this.sumRatings = sumRatingsGiven;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }
}
