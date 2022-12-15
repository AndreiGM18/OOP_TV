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

    private static void createOut(ArrayNode output, UI ui, String error) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode node = objectMapper.createObjectNode();

        if (error == null) {
            node.set(Constants.Output.ERR, null);
            System.out.println(ui.getCurrentMoviesList() + "here");

            ArrayNode arrayNode = objectMapper.createArrayNode();
            for (Movie movie : ui.getCurrentMoviesList()) {
                arrayNode.addPOJO(movie);
            }

            node.set(Constants.Output.CURRMOVIES, arrayNode);
            node.putPOJO(Constants.Output.CURRUSER, ui.getCurrentUser());
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

        Input input = null;
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

        ui.init();

        for (ActionsInput action : input.getActions()) {
            System.out.println();
            System.out.println(ui.getCurrentPage());
            System.out.println(action.getType());
            System.out.println();
            switch (action.getType()) {
                case Constants.Command.CHANGE -> {
                    if (ui.getCurrentPage().accept(new VerifierChangePage(), action.getPage()) == false) {
                        System.out.println("wrong page");
                        Main.createOut(output, ui, Constants.Output.ERROR);
                    } else {
                        System.out.println("correct page");
                        ui.setCurrentPage(ui.getPages().get(action.getPage()));

                        if (action.getPage().equals(Constants.Page.LOGOUT)) {
                            System.out.println("user logout");
                            ui.clearUI();
                            ui.setCurrentPage(ui.getPages().get(Constants.Page.UNAUTH));
                        }

                        if (action.getPage().equals(Constants.Page.MOVIES)) {
                            System.out.println("movies are here   " + database.getMovieDatabase());
                            ui.setCurrentMoviesList(database.getMovieDatabase());
                            Main.createOut(output, ui, null);
                        }
                    }
                }

                case Constants.Command.ON -> {
                    if (ui.getCurrentPage().accept(new VerifierOnPage(), action.getFeature()) == false) {
                        System.out.println("wrong feature");
                        Main.createOut(output, ui, Constants.Output.ERROR);
                    } else {
                        switch (action.getFeature()) {
                            case Constants.Feature.LOGIN -> {
                                User user = database.getUser(action.getCredentials());
                                if (user == null) {
                                    System.out.println("user login not found");
                                    Main.createOut(output, ui, Constants.Output.ERROR);
                                    ui.setCurrentPage(ui.getPages().get(Constants.Page.UNAUTH));
                                } else {
                                    System.out.println("user login found");
                                    ui.setCurrentUser(user);
                                    ui.setCurrentPage(ui.getPages().get(Constants.Page.AUTH));
                                    Main.createOut(output, ui, null);
                                }
                            }

                            case Constants.Feature.REGISTER -> {
                                User user = database.getUser(action.getCredentials());
                                if (user != null) {
                                    System.out.println("user register exists");
                                    Main.createOut(output, ui, Constants.Output.ERROR);
                                    ui.setCurrentPage(ui.getPages().get(Constants.Page.UNAUTH));
                                } else {
                                    System.out.println("user register success");
                                    User newUser = database.addUser(action.getCredentials());
                                    ui.setCurrentUser(newUser);
                                    ui.setCurrentPage(ui.getPages().get(Constants.Page.AUTH));
                                    System.out.println(newUser);
                                    Main.createOut(output, ui, null);
                                }
                            }

                            case Constants.Feature.SEARCH -> {
                                ui.search(action.getStartsWith());
                                Main.createOut(output, ui, null);
                            }

                            case Constants.Feature.FILTER -> {
                                ui.filter(action.getFilters());
                                Main.createOut(output, ui, null);
                            }
                        }
                    }
                }
            }
        }

        ui.setCurrentPage(ui.getPages().get(Constants.Page.UNAUTH));
        ui.clearUI();
        database.clearDatabase();
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        try {
            objectWriter.writeValue(result, output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
