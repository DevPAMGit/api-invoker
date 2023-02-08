package baobab.libraries.requete.authentifie.base64;

import baobab.libraries.requete.noyau.MethodeHTTP;
import baobab.libraries.requete.noyau.RequeteHTTP;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.Base64;

/**
 * Requête permettant d'effectuer des requêtes HTTP avec une authentication Basic encodée en Base 64.
 */
public abstract class RequeteHTTPAuthBasic extends RequeteHTTP {
    /**
     * La valeur préformatée de l'entête 'Authorization'.
     */
    private static final String VALEUR_AUTHORIZATION = "Basic %s";

    /**
     * La valeur préformatée du login et du mot de passe dans 'VALEUR_AUTHORIZATION'.
     */
    private static final String LOGIN_MDP = "%s:%s";

    /**
     * Initialise une nouvelle instance de la classe {@link RequeteHTTPAuthBasic}.
     *
     * @param methode                La méthode de la requête HTTP.
     * @param url                    L'URL sur laquelle se connecter.
     * @param login                  Le login d'authentification.
     * @param motDePasse             Le mot de passe du login.
     * @throws MalformedURLException Si aucun protocole n'est spécifié, ou si un protocole inconnu est trouvé,
     *                                        ou si la spécification est nulle, ou si l'URL analysée ne respecte pas
     *                                        la syntaxe spécifique du protocole associé.
     * @throws ProtocolException     Si la méthode ne peut pas être réinitialisée ou si la méthode demandée
     *                                        n'est pas valide pour HTTP.
     * @throws SecurityException              Si un gestionnaire de sécurité est défini et que la méthode est "TRACE",
     *                                        mais que la NetPermission "allowHttpTrace" n'est pas accordée.
     * @throws IOException           Si une exception d'E/S se produit.
     */
    public RequeteHTTPAuthBasic(@NotNull MethodeHTTP methode, @NotNull String url, @NotNull String login,
                                @NotNull String motDePasse) throws IOException {
        super(methode, url);
        this.setEnTete("Authorization", String.format(VALEUR_AUTHORIZATION, Base64.getEncoder().encodeToString(
                String.format(LOGIN_MDP, login, motDePasse).getBytes()
        )));
    }
}
