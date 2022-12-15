package constants;

/**
 * Constant class - used in order to handle magic numbers and strings
 */
public class Constants {
    public static final class Credentials {
        public static final String PREMIUM = "premium";
    }
    public static class Command {
        public static final String ON = "on page";
        public static final String CHANGE = "change page";
    }

    public static class Output {
        public static final String ERROR = "Error";
        public static final String ERR = "error";
        public static final String CURRUSER = "currentUser";
        public static final String CURRMOVIES = "currentMoviesList";

    }

    public static class Page {
        public static final String LOGIN = "login";
        public static final String LOGOUT = "logout";
        public static final String REGISTER = "register";
        public static final String MOVIES = "movies";
        public static final String DETAILS = "see details";
        public static final String UPGRADES = "upgrades";
        public static final String AUTH = "authenticated";
        public static final String UNAUTH = "unauthenticated";
    }

    public static class Integers {
        public static final int PREMIUM_STARTING_TOKENS = 15;
    }

    public static class Feature {
        public static final String LOGIN = "login";
        public static final String LOGOUT = "logout";
        public static final String REGISTER = "register";
        public static final String SEARCH = "search";
        public static final String FILTER = "filter";
        public static final String PURCHASE = "purchase";
        public static final String WATCH = "watch";
        public static final String LIKE = "like";
        public static final String RATE = "rate";
        public static final String TOKENS = "buy tokens";
        public static final String PREMIUM = "buy premium account";
    }
}
