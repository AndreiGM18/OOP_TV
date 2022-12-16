package ui.sortstrategy;

import implementation.Movie;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class SortStrategy {
    /**
     *
     * @param currentMoviesList
     */
    public abstract void sort(ArrayList<Movie> currentMoviesList);
}

final class AA extends SortStrategy {
    @Override
    public void sort(final ArrayList<Movie> currentMoviesList) {
        currentMoviesList.sort(new Comparator<Movie>() {
            @Override
            public int compare(final Movie o1, final Movie o2) {
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
    public void sort(final ArrayList<Movie> currentMoviesList) {
        currentMoviesList.sort(new Comparator<Movie>() {
            @Override
            public int compare(final Movie o1, final Movie o2) {
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

final class DA extends SortStrategy {
    @Override
    public void sort(final ArrayList<Movie> currentMoviesList) {
        currentMoviesList.sort(new Comparator<Movie>() {
            @Override
            public int compare(final Movie o1, final Movie o2) {
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

final class DD extends SortStrategy {
    @Override
    public void sort(final ArrayList<Movie> currentMoviesList) {
        currentMoviesList.sort(new Comparator<Movie>() {
            @Override
            public int compare(final Movie o1, final Movie o2) {
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

final class RatingA extends SortStrategy {
    @Override
    public void sort(final ArrayList<Movie> currentMoviesList) {
        currentMoviesList.sort(new Comparator<Movie>() {
            @Override
            public int compare(final Movie o1, final Movie o2) {
                if (o1.getRating() >= o2.getRating()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }
}

final class RatingD extends SortStrategy {
    @Override
    public void sort(final ArrayList<Movie> currentMoviesList) {
        currentMoviesList.sort(new Comparator<Movie>() {
            @Override
            public int compare(final Movie o1, final Movie o2) {
                if (o1.getRating() >= o2.getRating()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }
}
