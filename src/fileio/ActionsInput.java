package fileio;
public final class ActionsInput {
    private String type;
    private String page;
    private String movie;
    private String feature;
    private CredentialsInput credentials;
    private String startsWith;
    private FilterInput filters;
    private String count;
    private int rate;

    public ActionsInput() {
    }

    public String getType() {
        return type;
    }

    public String getPage() {
        return page;
    }

    public String getMovie() {
        return movie;
    }

    public String getFeature() {
        return feature;
    }

    public CredentialsInput getCredentials() {
        return credentials;
    }

    public String getStartsWith() {
        return startsWith;
    }

    public FilterInput getFilters() {
        return filters;
    }

    public String getCount() {
        return count;
    }

    public int getRate() {
        return rate;
    }
}
