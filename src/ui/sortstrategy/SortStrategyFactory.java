package ui.sortstrategy;

import fileio.SortInput;

public final class SortStrategyFactory {
    private SortStrategyFactory() {
    }

    /**
     *
     * @param sortInput
     * @return
     */
    public static SortStrategy createStrat(final SortInput sortInput) {
        if (sortInput.getDuration() == null) {
            switch (sortInput.getRating()) {
                case "increasing" -> {
                    return new RatingA();
                }
                case "decreasing" -> {
                    return new RatingD();
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
                        return new AA();
                    }
                    case "decreasing" -> {
                        return new AD();
                    }
                    default -> {
                        return null;
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
