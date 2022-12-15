package verifier;

import page.*;

public interface Verifier {
    boolean verify(AuthenticatedPage authenticatedPage, String string);
    boolean verify(Login login, String string);
    boolean verify(Logout logout, String string);
    boolean verify(Movies movies, String string);
    boolean verify(Register register, String string);
    boolean verify(SeeDetails seeDetails, String string);
    boolean verify(UnauthenticatedPage unauthenticatedPage, String string);
    boolean verify(Upgrades upgrades, String string);
}
