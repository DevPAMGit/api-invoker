package baobab.libraries.requete.authentifie.base64;

import baobab.libraries.requete.noyau.MethodeHTTP;
import org.jetbrains.annotations.NotNull;

/**
 * Requête permettant d'effectuer des requêtes HTTP avec une authentication Basic encodée en Base 64 en méthode POST.
 */
public abstract class RequeteHTTPPostAuthBasic extends RequeteHTTPAuthBasic {
    /**
     * Initialise une nouvelle instance de la classe {@link RequeteHTTPPostAuthBasic}.
     *
     * @param url        L'URL sur laquelle se connecter.
     * @param login      Le login d'authentification.
     * @param motDePasse Le mot de passe du login.
     */
    public RequeteHTTPPostAuthBasic(@NotNull String url, @NotNull String login, @NotNull String motDePasse) {
        super(MethodeHTTP.POST, url, login, motDePasse);
    }
}
