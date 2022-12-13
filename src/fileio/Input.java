package fileio;

import java.util.ArrayList;

public final class Input {
    private ArrayList<UsersInput> users;
    private ArrayList<MoviesInput> movies;
    private ArrayList<ActionsInput> actions;
    public Input() {
    }

    public ArrayList<UsersInput> getUsers() {
        return users;
    }

    public ArrayList<MoviesInput> getMovies() {
        return movies;
    }

    public ArrayList<ActionsInput> getActions() {
        return actions;
    }
}
