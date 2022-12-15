package page;

import java.util.ArrayList;

public abstract class Page implements Accepter {
    protected ArrayList<String> pageConnections;
    protected ArrayList<String> pageFeatures;

    public ArrayList<String> getPageConnections() {
        return pageConnections;
    }

    public ArrayList<String> getPageFeatures() {
        return pageFeatures;
    }
}
