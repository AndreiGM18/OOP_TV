package verifier;

import page.AuthenticatedPage;
import page.Login;
import page.Logout;
import page.Movies;
import page.Register;
import page.SeeDetails;
import page.UnauthenticatedPage;
import page.Upgrades;

public interface Verifier {
    /**
     *
     * @param authenticatedPage
     * @param string
     * @return
     */
    boolean verify(AuthenticatedPage authenticatedPage, String string);

    /**
     *
     * @param login
     * @param string
     * @return
     */
    boolean verify(Login login, String string);

    /**
     *
     * @param logout
     * @param string
     * @return
     */
    boolean verify(Logout logout, String string);

    /**
     *
     * @param movies
     * @param string
     * @return
     */
    boolean verify(Movies movies, String string);

    /**
     *
     * @param register
     * @param string
     * @return
     */
    boolean verify(Register register, String string);

    /**
     *
     * @param seeDetails
     * @param string
     * @return
     */
    boolean verify(SeeDetails seeDetails, String string);

    /**
     *
     * @param unauthenticatedPage
     * @param string
     * @return
     */
    boolean verify(UnauthenticatedPage unauthenticatedPage, String string);

    /**
     *
     * @param upgrades
     * @param string
     * @return
     */
    boolean verify(Upgrades upgrades, String string);
}
