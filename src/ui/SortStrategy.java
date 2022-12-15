package ui;

import fileio.SortInput;
import implementation.Movie;

import java.util.ArrayList;
import java.util.Comparator;

abstract class SortStrategy {
    abstract void sort(ArrayList<Movie> currentMoviesList);
}

final class AA extends SortStrategy {
    @Override
    void sort(ArrayList<Movie> currentMoviesList) {
        currentMoviesList.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                if (o1.getDuration() == o2.getDuration()) {
                    if (o1.getRating() >= o2.getRating()) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else {
                    if (o1.getDuration() >= o2.getDuration()) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
        });
    }
}

final class AD extends SortStrategy {
    @Override
    void sort(ArrayList<Movie> currentMoviesList) {
        currentMoviesList.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                if (o1.getDuration() == o2.getDuration()) {
                    if (o1.getRating() >= o2.getRating()) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else {
                    if (o1.getDuration() <= o2.getDuration()) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
        });
    }
}

final class DA extends SortStrategy {
    @Override
    void sort(ArrayList<Movie> currentMoviesList) {
        currentMoviesList.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                if (o1.getDuration() == o2.getDuration()) {
                    if (o1.getRating() <= o2.getRating()) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else {
                    if (o1.getDuration() >= o2.getDuration()) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
        });
    }
}

final class DD extends SortStrategy {
    @Override
    void sort(ArrayList<Movie> currentMoviesList) {
        currentMoviesList.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                if (o1.getDuration() == o2.getDuration()) {
                    if (o1.getRating() <= o2.getRating()) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else {
                    if (o1.getDuration() <= o2.getDuration()) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
        });
    }
}

final class SortStrategyFactory {
    static SortStrategy createStrat(SortInput sortInput) {
        switch (sortInput.getDuration()) {
            case "increasing" -> {
                switch (sortInput.getRating()) {
                    case "increasing" -> {
                        return new AA();
                    }
                    case "decreasing" -> {
                        return new AD();
                    }
                }
            }

            case "decreasing" -> {
                switch (sortInput.getRating()) {
                    case "increasing" -> {
                        return new DA();
                    }
                    case "decreasing" -> {
                        return new DD();
                    }
                }
            }
        }
        return null;
    }
}
