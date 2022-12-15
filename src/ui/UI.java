package ui;

import databases.Database;
import fileio.FilterInput;
import fileio.SortInput;
import implementation.Movie;
import implementation.User;
import page.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public final class UI {
    private static UI instance = null;

    private UI() {
    }

    /**
     *
     * @return
     */
    public static UI getUI() {
        if (instance == null) {
            instance = new UI();
        }

        return instance;
    }
    private Page currentPage;
    private User currentUser;
    private ArrayList<Movie> currentMoviesList = new ArrayList<>();

    private HashMap<String, Page> pages = new HashMap<>();

    public void init() {
        AuthenticatedPage authenticatedPage =  new AuthenticatedPage();
        Login login = new Login();
        Logout logout = new Logout();
        Movies moviesPage = new Movies();
        Register register = new Register();
        SeeDetails seeDetails = new SeeDetails();
        UnauthenticatedPage unauthenticatedPage = new UnauthenticatedPage();
        Upgrades upgrades = new Upgrades();
        this.pages.put("authenticated", authenticatedPage);
        this.pages.put("login", login);
        this.pages.put("logout", logout);
        this.pages.put("movies", moviesPage);
        this.pages.put("register", register);
        this.pages.put("see details", seeDetails);
        this.pages.put("unauthenticated", unauthenticatedPage);
        this.pages.put("upgrades", upgrades);

        this.currentPage = unauthenticatedPage;
    }

    public void clearUI() {
        this.currentUser = null;
        this.currentMoviesList.clear();
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(final Page currentPage) {
        this.currentPage = currentPage;
    }

    public HashMap<String, Page> getPages() {
        return pages;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(final User currentUser) {
        this.currentUser = currentUser;
    }

    public ArrayList<Movie> getCurrentMoviesList() {
        return currentMoviesList;
    }

    public void setCurrentMoviesList(final LinkedList<Movie> movies) {
        System.out.println(movies);
        System.out.println(currentUser);
        for (Movie movie : movies) {
            if (movie.getCountriesBanned().contains(currentUser.getCredentials().getCountry()) == false)
                this.currentMoviesList.add(movie);
        }
    }

    public void search(String substring) {
        this.currentMoviesList.clear();
        for (Movie movie : currentMoviesList) {
            if (movie.getName().contains(substring)) {
                this.currentMoviesList.add(movie);
            }
        }
    }

    public void filter(final FilterInput filter) {
        SortStrategy strategy = SortStrategyFactory.createStrat(filter.getSort());
        System.out.println(strategy);
        strategy.sort(this.currentMoviesList);
        System.out.println(this.currentMoviesList);
    }
}
