package databases;

import fileio.CredentialsInput;
import fileio.MovieInput;
import fileio.UserInput;
import implementation.Movie;
import implementation.User;

import java.util.ArrayList;
import java.util.LinkedList;

public final class Database {
    private static Database instance = null;

    private Database() {
    }

    /**
     *
     * @return
     */
    public static Database getDatabase() {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    private LinkedList<User> userDatabase = new LinkedList<>();
    private LinkedList<Movie> movieDatabase = new LinkedList<>();

    /**
     *
     * @param user
     */
    public void putUser(final User user) {
        userDatabase.addLast(user);
    }

    /**
     *
     * @param movie
     */
    public void putMovie(final Movie movie) {
        movieDatabase.addLast(movie);
    }

    /**
     *
     * @param credentialsInput
     * @return
     */
    public User getUser(final CredentialsInput credentialsInput) {
        for (User user : userDatabase) {
            if (user.getCredentials().getName().equals(credentialsInput.getName())
                    && user.getCredentials().getPassword().equals(credentialsInput.getPassword())) {
                return user;
            }
        }
        return null;
    }

    /**
     *
     * @param
     * @return
     */

    public LinkedList<Movie> getMovieDatabase() {
        return movieDatabase;
    }

    /**
     *
     */
    public void clearDatabase() {
        userDatabase.clear();
        movieDatabase.clear();
    }

    /**
     *
     * @param credentialsInput
     * @return
     */
    public User addUser(final CredentialsInput credentialsInput) {
        User user = new User.UserBuilder(credentialsInput)
                .build();
        this.putUser(user);

        return user;
    }
    /**
     *
     * @param userInputs
     * @param movieInputs
     */
    public void createDatabase(final ArrayList<UserInput> userInputs,
                               final ArrayList<MovieInput> movieInputs) {
        for (UserInput userInput : userInputs) {
            User user = new User.UserBuilder(userInput.getCredentials())
                    .build();
            this.putUser(user);
        }

        for (MovieInput movieInput : movieInputs) {
            Movie movie = new Movie.MovieBuilder(movieInput)
                    .build();
            this.putMovie(movie);
        }
    }
}
