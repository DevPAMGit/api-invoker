package baobab.libraries.service.composant;

import baobab.libraries.requete.authentifie.base64.RequeteHTTPDeleteAuthBasic;
import baobab.libraries.requete.noyau.RequeteHTTPException;
import baobab.libraries.service.IService;
import org.jetbrains.annotations.NotNull;

/**
 * Classe composant de service testant la suppression d'une entité avec la méthode HTTP DELETE.
 */
public class EntiteSupprimerComposant extends RequeteHTTPDeleteAuthBasic implements IService {
    /**
     * Format de la resource à appeler.
     */
    private static final String RESOURCE_FORMATEE = "%s/api/v2/entite/%s";

    /**
     * Resource appelée.
     */
    private final String ressource;


    /**
     * Initialise une nouvelle instance de la classe {@link RequeteHTTPDeleteAuthBasic}.
     *
     * @param url        L'URL sur laquelle se connecter.
     * @param login      Le login d'authentification.
     * @param motDePasse Le mot de passe du login.
     */
    public EntiteSupprimerComposant(@NotNull String url, @NotNull String login, @NotNull String motDePasse,
                                    String idEntite) {
        super(String.format(RESOURCE_FORMATEE, url, idEntite), login, motDePasse);
        this.ressource = String.format(RESOURCE_FORMATEE, url, idEntite);
    }

    @Override
    public byte[] getCorps() throws RequeteHTTPException {
        return null;
    }

    @Override
    public String getRessource() {
        return this.ressource;
    }
}
