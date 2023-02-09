package baobab.libraries.requete.authentifie.base64;

import baobab.libraries.requete.noyau.MethodeHTTP;
import org.jetbrains.annotations.NotNull;

/**
 * Requête permettant d'effectuer des requêtes HTTP avec une authentication Basic encodée en Base 64 en méthode PATCH.
 */
public abstract class RequeteHTTPPatchAuthBasic extends RequeteHTTPAuthBasic {
    /**
     * Initialise une nouvelle instance de la classe {@link RequeteHTTPPatchAuthBasic}.
     *
     * @param url                    L'URL sur laquelle se connecter.
     * @param login                  Le login d'authentification.
     * @param motDePasse             Le mot de passe du login.
     */
    public RequeteHTTPPatchAuthBasic(@NotNull String url, @NotNull String login, @NotNull String motDePasse)  {
        super(MethodeHTTP.PATCH, url, login, motDePasse);
    }
}
