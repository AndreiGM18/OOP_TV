import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import databases.Database;
import fileio.ActionsInput;
import fileio.Input;
import implementation.Movie;
import ui.UI;
import constants.Constants;
import verifier.VerifierChangePage;
import verifier.VerifierOnPage;
import implementation.User;

public final class Main {
    private Main() {
    }

    private static void createOut(final ArrayNode output, final UI ui, final String error) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode node = objectMapper.createObjectNode();

        if (error == null) {
            node.set(Constants.Output.ERR, null);
            node.set(Constants.Output.CURRMOVIES,
                    Movie.createMoviesArrayNode(ui.getCurrentMoviesList()));
            node.set(Constants.Output.CURRUSER, ui.getCurrentUser().createObjectNode());
        } else {
            node.put(Constants.Output.ERR, error);
            node.putPOJO(Constants.Output.CURRMOVIES, new ArrayList<>());
            node.set(Constants.Output.CURRUSER, null);
        }

        output.add(node);
    }

    /**
     *
     * @param args args[0] is the input file's path and args[1] is the output file's path
     */
    public static void main(final String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode output = objectMapper.createArrayNode();

        File in = new File(args[0]);
        File result = new File(args[1]);

        Input input;
        try {
            input = objectMapper.readValue(in, Input.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Database database = Database.getDatabase();
        database.clearDatabase();
        database.createDatabase(input.getUsers(), input.getMovies());
        UI ui = UI.getUI();
        ui.clearUI();

        for (ActionsInput action : input.getActions()) {
            switch (action.getType()) {
                case Constants.Command.CHANGE -> {
                    if (!ui.getCurrentPage().accept(new VerifierChangePage(), action.getPage())) {
                        Main.createOut(output, ui, Constants.Output.ERROR);
                    } else {
                        ui.setCurrentPage(ui.getPages().get(action.getPage()));

                        if (action.getPage().equals(Constants.Page.LOGOUT)) {
                            ui.clearUI();
                            ui.setCurrentPage(ui.getPages().get(Constants.Page.UNAUTH));
                        }

                        if (action.getPage().equals(Constants.Page.MOVIES)) {
                            ui.setCurrentMoviesList(new ArrayList<>(database.getMovieDatabase()));
                            Main.createOut(output, ui, null);
                        }

                        if (action.getPage().equals((Constants.Page.DETAILS))) {
                            if (ui.check(action.getMovie())) {
                                ArrayList<Movie> currentMovie = new ArrayList<>();
                                currentMovie.add(ui.getMovie(action.getMovie()));
                                ui.setCurrentMoviesList(new ArrayList<>(currentMovie));
                                Main.createOut(output, ui, null);
                            } else {
                                Main.createOut(output, ui, Constants.Output.ERROR);
                                ui.setCurrentPage(ui.getPages().get(Constants.Page.MOVIES));
                            }
                        }
                    }
                }

                case Constants.Command.ON -> {
                    if (!ui.getCurrentPage().accept(new VerifierOnPage(), action.getFeature())) {
                        Main.createOut(output, ui, Constants.Output.ERROR);
                    } else {
                        switch (action.getFeature()) {
                            case Constants.Feature.LOGIN -> {
                                User user = database.getUser(action.getCredentials());
                                if (user == null) {
                                    Main.createOut(output, ui, Constants.Output.ERROR);
                                    ui.setCurrentPage(ui.getPages().get(Constants.Page.UNAUTH));
                                } else {
                                    ui.setCurrentUser(user);
                                    ui.setCurrentPage(ui.getPages().get(Constants.Page.AUTH));
                                    Main.createOut(output, ui, null);
                                }
                            }

                            case Constants.Feature.REGISTER -> {
                                User user = database.getUser(action.getCredentials());
                                if (user != null) {
                                    Main.createOut(output, ui, Constants.Output.ERROR);
                                    ui.setCurrentPage(ui.getPages().get(Constants.Page.UNAUTH));
                                } else {
                                    User newUser = database.addUser(action.getCredentials());
                                    ui.setCurrentUser(newUser);
                                    ui.setCurrentPage(ui.getPages().get(Constants.Page.AUTH));
                                    Main.createOut(output, ui, null);
                                }
                            }

                            case Constants.Feature.SEARCH -> {
                                ui.setCurrentMoviesList(new ArrayList<>(database
                                        .getMovieDatabase()));
                                ui.search(action.getStartsWith());
                                Main.createOut(output, ui, null);
                            }

                            case Constants.Feature.FILTER -> {
                                ui.setCurrentMoviesList(new ArrayList<>(database
                                        .getMovieDatabase()));
                                ui.filter(action.getFilters());
                                Main.createOut(output, ui, null);
                            }

                            case Constants.Feature.TOKENS -> {
                                if (!ui.getCurrentUser().buyTokens(action.getCount())) {
                                    Main.createOut(output, ui, Constants.Output.ERROR);
                                }
                            }

                            case Constants.Feature.PREMIUM -> {
                                if (!ui.getCurrentUser().buyPremium()) {
                                    Main.createOut(output, ui, Constants.Output.ERROR);
                                }
                            }

                            case Constants.Feature.PURCHASE -> {
                                Movie movie = ui.getCurrentMoviesList().get(0);
                                if (ui.getCurrentUser().purchase(movie)) {
                                    Main.createOut(output, ui, null);
                                } else {
                                    Main.createOut(output, ui, Constants.Output.ERROR);
                                }
                            }

                            case Constants.Feature.WATCH -> {
                                    Movie movie = ui.getCurrentMoviesList().get(0);
                                    if (movie != null && ui.getCurrentUser().getPurchasedMovies()
                                            .contains(movie)) {
                                        ui.getCurrentUser().getWatchedMovies().add(movie);
                                        Main.createOut(output, ui, null);
                                    } else {
                                        Main.createOut(output, ui, Constants.Output.ERROR);
                                    }
                            }

                            case Constants.Feature.LIKE -> {
                                    Movie movie = ui.getCurrentMoviesList().get(0);
                                    if (movie != null && ui.getCurrentUser().getWatchedMovies()
                                            .contains(movie)) {
                                        ui.getCurrentUser().getLikedMovies().add(movie);
                                        movie.setNumLikes(movie.getNumLikes() + 1);
                                        Main.createOut(output, ui, null);
                                    } else {
                                        Main.createOut(output, ui, Constants.Output.ERROR);
                                    }
                            }

                            case Constants.Feature.RATE -> {
                                    Movie movie = ui.getCurrentMoviesList().get(0);
                                    if (movie != null && ui.getCurrentUser().getWatchedMovies()
                                            .contains(movie)) {
                                        if (action.getRate() > 5) {
                                            Main.createOut(output, ui, Constants.Output.ERROR);
                                        } else {
                                            movie.setNumRatings(movie.getNumRatings() + 1);
                                            movie.setSumRatings(movie.getSumRatings()
                                                    + action.getRate());
                                            movie.setRating((double) movie.getSumRatings()
                                                    / movie.getNumRatings());
                                            ui.getCurrentUser().getRatedMovies().add(movie);
                                            Main.createOut(output, ui, null);
                                        }
                                    } else {
                                        Main.createOut(output, ui, Constants.Output.ERROR);
                                    }
                            }

                            default -> {
                                Main.createOut(output, ui, Constants.Output.ERROR);
                            }
                        }
                    }
                }
                default -> {
                    Main.createOut(output, ui, Constants.Output.ERROR);
                }
            }
        }

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        try {
            objectWriter.writeValue(result, output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
