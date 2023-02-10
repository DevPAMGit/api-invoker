package baobab.libraries.requete.authentifie.base64;

import baobab.libraries.requete.noyau.MethodeHTTP;
import baobab.libraries.requete.noyau.RequeteHTTPException;
import org.jetbrains.annotations.NotNull;

/**
 * Requête permettant d'effectuer des requêtes HTTP avec une authentication Basic encodée en Base 64 en méthode GET.
 */
public abstract class RequeteHTTPGetAuthBasic extends RequeteHTTPAuthBasic {
    /**
     * Initialise une nouvelle instance de la classe {@link RequeteHTTPGetAuthBasic}.
     *
     * @param url        L'URL sur laquelle se connecter.
     * @param login      Le login d'authentification.
     * @param motDePasse Le mot de passe du login.
     */
    public RequeteHTTPGetAuthBasic(@NotNull String url, @NotNull String login, @NotNull String motDePasse) {
        super(MethodeHTTP.GET, url, login, motDePasse);
    }

    @Override
    public byte[] getCorps() throws RequeteHTTPException {
        return null;
    }
}
