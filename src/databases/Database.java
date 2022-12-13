package databases;

import fileio.CredentialsInput;
import implementation.Movie;
import implementation.User;

import java.util.HashMap;

public final class Database {
    HashMap<String, User> userDatabase;
    HashMap<String, Movie> movieDatabase;

    public HashMap<String, User> getUserDatabase() {
        return userDatabase;
    }

    public HashMap<String, Movie> getMovieDatabase() {
        return movieDatabase;
    }
}
