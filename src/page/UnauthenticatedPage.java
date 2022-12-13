package page;

import verifier.Verifier;

public class UnauthenticatedPage extends Page {
    @Override
    public void accept(Verifier verifier) {
        verifier.verify(this);
    }
}
