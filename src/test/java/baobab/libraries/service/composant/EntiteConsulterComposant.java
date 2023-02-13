package baobab.libraries.service.composant;

import baobab.libraries.requete.authentifie.base64.RequeteHTTPGetAuthBasic;
import baobab.libraries.service.IService;
import org.jetbrains.annotations.NotNull;

public class EntiteConsulterComposant extends RequeteHTTPGetAuthBasic implements IService {
    /**
     * Format de la resource à appeler.
     */
    private final static String RESOURCE_FORMATEE = "%s/api/v2/entite/%s";

    /**
     * Resource appelée.
     */
    private final String ressource;

    /**
     * Initialise une nouvelle instance de la classe {@link EntiteConsulterComposant}.
     *
     * @param url        L'URL sur laquelle se connecter.
     * @param login      Le login d'authentification.
     * @param motDePasse Le mot de passe du login.
     * @param idEntite  L'identifiant de l'entité à consulter.
     */
    public EntiteConsulterComposant(@NotNull String url, @NotNull String login, @NotNull String motDePasse,
                                    String idEntite) {
        super(String.format(RESOURCE_FORMATEE, url, idEntite), login, motDePasse);
        this.ressource = String.format(RESOURCE_FORMATEE, url, idEntite);
    }

    @Override
    public String getRessource() {
        return this.ressource;
    }
}
