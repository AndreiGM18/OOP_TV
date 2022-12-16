package implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.CredentialsInput;

import java.util.ArrayList;

public final class User {
    private Credentials credentials;
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
        this.likedMovies = builder.likedMovies;
        this.ratedMovies = builder.ratedMovies;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(final Credentials credentials) {
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

    public static final class UserBuilder {
        private Credentials credentials;
        private int tokensCount = 0;
        private int numFreePremiumMovies = 15;
        private ArrayList<Movie> purchasedMovies = new ArrayList<>();
        private ArrayList<Movie> watchedMovies = new ArrayList<>();
        private ArrayList<Movie> likedMovies = new ArrayList<>();
        private ArrayList<Movie> ratedMovies = new ArrayList<>();

        public UserBuilder(final CredentialsInput credentialsGiven) {
            this.credentials = new Credentials(credentialsGiven);
        }

        /**
         *
         * @param tokensCountGiven
         * @return
         */
        public UserBuilder tokensCount(final int tokensCountGiven) {
            this.tokensCount = tokensCountGiven;
            return this;
        }

        /**
         *
         * @param numFreePremiumMoviesGiven
         * @return
         */
        public UserBuilder numFreePremiumMovies(final int numFreePremiumMoviesGiven) {
            this.numFreePremiumMovies = numFreePremiumMoviesGiven;
            return this;
        }

        /**
         *
         * @param purchasedMoviesGiven
         * @return
         */
        public UserBuilder purchasedMovies(final ArrayList<Movie> purchasedMoviesGiven) {
            this.purchasedMovies = purchasedMoviesGiven;
            return this;
        }

        /**
         *
         * @param watchedMoviesGiven
         * @return
         */
        public UserBuilder watchedMovies(final ArrayList<Movie> watchedMoviesGiven) {
            this.watchedMovies = watchedMoviesGiven;
            return this;
        }

        /**
         *
         * @param likedMoviesGiven
         * @return
         */
        public UserBuilder likedMovies(final ArrayList<Movie> likedMoviesGiven) {
            this.likedMovies = likedMoviesGiven;
            return this;
        }

        /**
         *
         * @param ratedMoviesGiven
         * @return
         */
        public UserBuilder ratedMovies(final ArrayList<Movie> ratedMoviesGiven) {
            this.ratedMovies = ratedMoviesGiven;
            return this;
        }

        /**
         *
         * @return
         */
        public User build() {
            return new User(this);
        }
    }

    /**
     *
     * @param count
     * @return
     */
    public boolean buyTokens(final String count) {
        int cnt = Integer.parseInt(count);
        int bal = Integer.parseInt(credentials.getBalance());

        if (bal >= cnt) {
            bal -= cnt;
            tokensCount += cnt;
            String newBal = String.valueOf(bal);
            credentials.setBalance(newBal);
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @return
     */
    public boolean buyPremium() {
        if (tokensCount >= 10 && credentials.getAccountType().equals("standard")) {
            tokensCount -= 10;
            credentials.setAccountType("premium");
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @return
     */
    public ObjectNode createObjectNode() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();

        ObjectNode credentialsNode = objectMapper.createObjectNode();
        credentialsNode.put("name", credentials.getName());
        credentialsNode.put("password", credentials.getPassword());
        credentialsNode.put("accountType", credentials.getAccountType());
        credentialsNode.put("country", credentials.getCountry());
        credentialsNode.put("balance", credentials.getBalance());
        objectNode.set("credentials", credentialsNode);
        objectNode.put("tokensCount", tokensCount);
        objectNode.put("numFreePremiumMovies", numFreePremiumMovies);
        objectNode.put("purchasedMovies", Movie.createMoviesArrayNode(purchasedMovies));
        objectNode.put("watchedMovies", Movie.createMoviesArrayNode(watchedMovies));
        objectNode.put("likedMovies", Movie.createMoviesArrayNode(likedMovies));
        objectNode.put("ratedMovies", Movie.createMoviesArrayNode(ratedMovies));

        return objectNode;
    }

    /**
     *
     * @param movie
     * @return
     */
    public boolean purchase(final Movie movie) {
        if (credentials.getAccountType().equals("standard") && tokensCount >= 2) {
            tokensCount -= 2;
            purchasedMovies.add(movie);
            return true;
        }

        if (credentials.getAccountType().equals("premium")) {
            if (numFreePremiumMovies >= 1) {
                numFreePremiumMovies -= 1;
                purchasedMovies.add(movie);
                return true;
            } else if (tokensCount >= 2) {
                tokensCount -= 2;
                purchasedMovies.add(movie);
                return true;
            }
        }

        return false;
    }
}
