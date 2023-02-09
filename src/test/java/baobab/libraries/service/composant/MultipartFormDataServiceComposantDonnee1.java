package baobab.libraries.service.composant;

import baobab.libraries.requete.authentifie.base64.RequeteHTTPMulipartFormData;
import baobab.libraries.requete.noyau.RequeteHTTPException;
import baobab.libraries.service.IService;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Classe composante de test implémentant une requête de méthode POST Multipart/form-data.
 */
public class MultipartFormDataServiceComposantDonnee1 extends RequeteHTTPMulipartFormData implements IService {
    /**
     * La resource à consulter sur l'hôte.
     */
    private final static String RESOURCE_FORMATEE = "%s/api/v2/entite/%s/document";

    /**
     * La ressource du service à consulter.
     */
    private final String ressource;

    /**
     * Initialise une nouvelle instance de la classe {@link RequeteHTTPMulipartFormData}.
     *
     * @param url                    L'URL sur laquelle se connecter.
     * @param login                  Le login d'authentification.
     * @param motDePasse             Le mot de passe du login.
     */
    public MultipartFormDataServiceComposantDonnee1(
            @NotNull String url, @NotNull String login, @NotNull String motDePasse) {
        super(String.format(RESOURCE_FORMATEE, url, VariablesStatiques.ENTITE), login, motDePasse);
        this.ressource = String.format(RESOURCE_FORMATEE, url, VariablesStatiques.ENTITE);
        this.addDonnee("type", VariablesStatiques.TYPE);
    }

    @Override
    public String appeler() throws IOException, RequeteHTTPException, InterruptedException {
        this.envoyer();
        String result = this.recevoir();
        return result;
    }

    @Override
    public String getRessource() {
        return this.ressource;
    }
}
