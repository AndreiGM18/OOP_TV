package implementation;

import constants.Constants;
import fileio.CredentialsInput;

import java.util.ArrayList;

public final class User {
    private CredentialsInput credentials;
    private int tokensCount;
    private int numFreePremiumMovies;
    private ArrayList<Movie> purchasedMovies;
    private ArrayList<Movie> watchedMovies;
    private ArrayList<Movie> likedMovies;
    private ArrayList<Movie> ratedMovies;

    private User(final UserBuilder builder) {
        this.credentials = builder.credentials;
        this.tokensCount = builder.tokensCount;
        this.numFreePremiumMovies = builder.numFreePremiumMovies;
        this.purchasedMovies = builder.purchasedMovies;
        this.watchedMovies = builder.watchedMovies;
        this.likedMovies = builder.watchedMovies;
        this.ratedMovies = builder.ratedMovies;
    }

    public CredentialsInput getCredentials() {
        return credentials;
    }

    public void setCredentials(final CredentialsInput credentials) {
        this.credentials = credentials;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(final ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(final ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(final ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(final ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    static final class UserBuilder {
        private CredentialsInput credentials;
        private int tokensCount = 0;
        private int numFreePremiumMovies = Constants.Integers.STARTING_TOKENS;
        private ArrayList<Movie> purchasedMovies = new ArrayList<>();
        private ArrayList<Movie> watchedMovies = new ArrayList<>();
        private ArrayList<Movie> likedMovies = new ArrayList<>();
        private ArrayList<Movie> ratedMovies = new ArrayList<>();

        UserBuilder(final CredentialsInput credentialsGiven) {
            this.credentials = credentialsGiven;
        }

        public UserBuilder tokensCount(final int tokensCountGiven) {
            this.tokensCount = tokensCountGiven;
            return this;
        }

        public UserBuilder numFreePremiumMovies(final int numFreePremiumMoviesGiven) {
            this.numFreePremiumMovies = numFreePremiumMoviesGiven;
            return this;
        }

        public UserBuilder purchasedMovies(final ArrayList<Movie> purchasedMoviesGiven) {
            this.purchasedMovies = purchasedMoviesGiven;
            return this;
        }

        public UserBuilder watchedMovies(final ArrayList<Movie> watchedMoviesGiven) {
            this.watchedMovies = watchedMoviesGiven;
            return this;
        }

        public UserBuilder likedMovies(final ArrayList<Movie> likedMoviesGiven) {
            this.likedMovies = likedMoviesGiven;
            return this;
        }

        public UserBuilder ratedMovies(final ArrayList<Movie> ratedMoviesGiven) {
            this.ratedMovies = ratedMoviesGiven;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
