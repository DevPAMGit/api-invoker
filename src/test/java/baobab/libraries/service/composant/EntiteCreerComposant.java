package baobab.libraries.service.composant;

import baobab.libraries.requete.authentifie.base64.RequeteHTTPMulipartFormDataAuthBasic;
import baobab.libraries.service.IService;
import org.jetbrains.annotations.NotNull;

public class EntiteCreerComposant extends RequeteHTTPMulipartFormDataAuthBasic implements IService {

    private final static String RESOURCE_FORMATEE = "%s/api/v2/entite";

    /**
     * Initialise une nouvelle instance de la classe {@link EntiteCreerComposant}.
     *
     * @param url        L'URL sur laquelle se connecter.
     * @param login      Le login d'authentification.
     * @param motDePasse Le mot de passe du login.
     */
    public EntiteCreerComposant(@NotNull String url, @NotNull String login, @NotNull String motDePasse) {
        super(String.format(RESOURCE_FORMATEE, url), login, motDePasse);
        this.addDonnee("denomination", "foo");
        this.addDonnee("entite_mere", "0");
        this.addDonnee("type", "collectivite");
        this.addDonnee("siren", "000000000");
        this.addDonnee("centre_de_gestion", "0");
    }

    @Override
    public String getRessource() {
        return "";
    }
}
