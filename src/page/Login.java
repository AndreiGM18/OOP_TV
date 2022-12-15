package page;

import constants.Constants;
import verifier.Verifier;

import java.util.ArrayList;

public final class Login extends Page implements Accepter {
    public Login() {
        this.pageFeatures = new ArrayList<>();
        this.pageConnections = new ArrayList<>();
        this.pageFeatures.add(Constants.Feature.LOGIN);
    }

    @Override
    public boolean accept(final Verifier verifier, final String string) {
        return verifier.verify(this, string);
    }
}
