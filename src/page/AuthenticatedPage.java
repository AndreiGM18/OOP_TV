package page;

import verifier.Verifier;

public final class AuthenticatedPage extends Page {
    @Override
    public void accept(Verifier verifier) {
        verifier.verify(this);
    }
}
