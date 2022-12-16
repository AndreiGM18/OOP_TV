package page;

import java.util.ArrayList;

public abstract class Page implements Accepter {
    protected ArrayList<String> pageConnections;
    protected ArrayList<String> pageFeatures;

    /**
     *
     * @return
     */
    public ArrayList<String> getPageConnections() {
        return pageConnections;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getPageFeatures() {
        return pageFeatures;
    }
}
