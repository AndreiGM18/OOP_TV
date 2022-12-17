package application.sortstrategy;

import fileio.SortInput;

public final class SortStrategyFactory {
    private SortStrategyFactory() {
    }

    /**
     * Creates a strategy that sorts the currentMoviesList in different ways
     * @param sortInput how the sorting should be done
     * @return
     */
    public static SortStrategy createStrat(final SortInput sortInput) {
        /* The duration field may be null */
        if (sortInput.getDuration() == null) {
            switch (sortInput.getRating()) {
                case "increasing" -> {
                    return new RatingInc();
                }
                case "decreasing" -> {
                    return new RatingDec();
                }
                default -> {
                    return null;
                }
            }
        }

        switch (sortInput.getDuration()) {
            case "increasing" -> {
                switch (sortInput.getRating()) {
                    case "increasing" -> {
                        return new DurationIncRatingInc();
                    }
                    case "decreasing" -> {
                        return new DurationIncRatingDec();
                    }
                    default -> {
                        return null;
                    }
                }
            }

            case "decreasing" -> {
                switch (sortInput.getRating()) {
                    case "increasing" -> {
                        return new DurationDecRatingInc();
                    }
                    case "decreasing" -> {
                        return new DurationDecRatingDec();
                    }
                    default -> {
                        return null;
                    }
                }
            }

            default -> {
                return null;
            }
        }
    }
}
