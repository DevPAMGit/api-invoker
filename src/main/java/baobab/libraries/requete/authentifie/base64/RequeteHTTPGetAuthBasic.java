package baobab.libraries.requete.authentifie.base64;

import baobab.libraries.requete.noyau.MethodeHTTP;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

/**
 * Requête permettant d'effectuer des requêtes HTTP avec une authentication Basic encodée en Base 64 en méthode GET.
 */
public abstract class RequeteHTTPGetAuthBasic extends RequeteHTTPAuthBasic {
    /**
     * Initialise une nouvelle instance de la classe {@link RequeteHTTPGetAuthBasic}.
     *
     * @param url                    L'URL sur laquelle se connecter.
     * @param login                  Le login d'authentification.
     * @param motDePasse             Le mot de passe du login.
     * @throws MalformedURLException Si aucun protocole n'est spécifié, ou si un protocole inconnu est trouvé,
     *                                  ou si la spécification est nulle, ou si l'URL analysée ne respecte pas
     *                                  la syntaxe spécifique du protocole associé.
     * @throws ProtocolException     Si la méthode ne peut pas être réinitialisée ou si la méthode demandée
     *                                  n'est pas valide pour HTTP.
     * @throws SecurityException        Si un gestionnaire de sécurité est défini et que la méthode est "TRACE",
     *                                  mais que la NetPermission "allowHttpTrace" n'est pas accordée.
     * @throws IOException           Si une exception d'E/S se produit.
     */
    public RequeteHTTPGetAuthBasic(@NotNull String url, @NotNull String login,
                                   @NotNull String motDePasse) throws IOException {
        super(MethodeHTTP.GET, url, login, motDePasse);
    }
}
