package page;

import verifier.Verifier;

public interface Accepter {
    /**
     *
     * @param verifier
     */
    boolean accept(Verifier verifier, String string);
}
