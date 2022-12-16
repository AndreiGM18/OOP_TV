package ui;

import fileio.ContainsInput;
import fileio.FilterInput;
import implementation.Movie;
import implementation.User;
import page.AuthenticatedPage;
import page.Login;
import page.Logout;
import page.Movies;
import page.Page;
import page.Register;
import page.SeeDetails;
import page.UnauthenticatedPage;
import page.Upgrades;
import ui.sortstrategy.SortStrategy;
import ui.sortstrategy.SortStrategyFactory;

import java.util.ArrayList;
import java.util.HashMap;

public final class UI {
    private static UI instance = null;

    private UI() {
        this.init();
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

    /**
     *
     */
    public void init() {
        AuthenticatedPage authenticatedPage = new AuthenticatedPage();
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

    /**
     *
     */
    public void clearUI() {
        this.currentUser = null;
        this.currentMoviesList.clear();
        this.pages.clear();
        this.init();
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

    /**
     *
     * @param movies
     */
    public void setCurrentMoviesList(final ArrayList<Movie> movies) {
        currentMoviesList.clear();
        for (Movie movie : movies) {
            if (!movie.getCountriesBanned().contains(currentUser.getCredentials().getCountry())) {
                this.currentMoviesList.add(movie);
            }
        }
    }

    /**
     *
     * @param name
     * @return
     */
    public Movie getMovie(final String name) {
        for (Movie movie : currentMoviesList) {
            if (movie.getName().equals(name)) {
                return movie;
            }
        }
        return null;
    }

    /**
     *
     * @param substring
     */
    public void search(final String substring) {
        ArrayList<Movie> newCurrentMoviesList = new ArrayList<>();
        for (Movie movie : currentMoviesList) {
            if (movie.getName().startsWith(substring)) {
               newCurrentMoviesList.add(movie);
            }
        }
        currentMoviesList = newCurrentMoviesList;
    }

    /**
     *
     * @param containsInput
     */
    public void contains(final ContainsInput containsInput) {
        if (containsInput == null) {
            return;
        }

        ArrayList<Movie> newCurrentMoviesList = new ArrayList<>();
        for (Movie movie : currentMoviesList) {
            boolean checkActors = true, checkGenre = true;
            if (containsInput.getActors() != null) {
                for (String actor : containsInput.getActors()) {
                    if (!movie.getActors().contains(actor)) {
                        checkActors = false;
                        break;
                    }
                }
            }

            if (containsInput.getGenre() != null) {
                for (String genre : containsInput.getGenre()) {
                    if (!movie.getGenres().contains(genre)) {
                        checkGenre = false;
                        break;
                    }
                }
            }

            if (checkActors && checkGenre) {
                newCurrentMoviesList.add(movie);
            }
        }

        currentMoviesList = newCurrentMoviesList;
    }

    /**
     *
     * @param filter
     */
    public void filter(final FilterInput filter) {
        if (filter.getSort() != null) {
            SortStrategy strategy = SortStrategyFactory.createStrat(filter.getSort());
            if (strategy != null) {
                strategy.sort(this.currentMoviesList);
            }
        }

        this.contains(filter.getContains());
    }

    /**
     *
     * @param name
     * @return
     */
    public boolean check(final String name) {
        for (Movie movie : currentMoviesList) {
            if (movie.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
