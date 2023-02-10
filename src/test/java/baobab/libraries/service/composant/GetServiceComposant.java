package baobab.libraries.service.composant;

import baobab.libraries.requete.authentifie.base64.RequeteHTTPGetAuthBasic;
import baobab.libraries.requete.noyau.RequeteHTTPException;
import baobab.libraries.service.IService;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

/**
 * Classe composante de test implémentant une requête de methode GET.
 */
public class GetServiceComposant extends RequeteHTTPGetAuthBasic implements IService {
    /**
     * La resource à consulter sur l'hôte.
     */
    private final static String RESOURCE_FORMATEE = "%s/api/v2/entite/%s/document/%s";

    /**
     * La ressource du service à consulter.
     */
    private final String ressource;

    /**
     * Initialise une nouvelle instance de la classe {@link RequeteHTTPGetAuthBasic}.
     *
     * @param url        L'URL sur laquelle se connecter.
     * @param login      Le login d'authentification.
     * @param motDePasse Le mot de passe du login.
     * @param idDossier  L'identifiant du dossier à consulter.
     * @throws MalformedURLException Si aucun protocole n'est spécifié, ou si un protocole inconnu est trouvé,
     *                               ou si la spécification est nulle, ou si l'URL analysée ne respecte pas
     *                               la syntaxe spécifique du protocole associé.
     * @throws ProtocolException     Si la méthode ne peut pas être réinitialisée ou si la méthode demandée
     *                               n'est pas valide pour HTTP.
     * @throws SecurityException     Si un gestionnaire de sécurité est défini et que la méthode est "TRACE",
     *                               mais que la NetPermission "allowHttpTrace" n'est pas accordée.
     * @throws IOException           Si une exception d'E/S se produit.
     */
    public GetServiceComposant(@NotNull String url, @NotNull String login, @NotNull String motDePasse, String idDossier)  {
        super(String.format(RESOURCE_FORMATEE, url, VariablesStatiques.ENTITE, idDossier), login, motDePasse);
        this.ressource = String.format(RESOURCE_FORMATEE, url, VariablesStatiques.ENTITE, idDossier);
    }

    @Override
    public String getRessource() {
        return this.ressource;
    }
}
