package baobab.libraries.service.composant;

import baobab.libraries.requete.authentifie.base64.RequeteHTTPPatchAuthBasic;
import baobab.libraries.requete.noyau.RequeteHTTPException;
import baobab.libraries.service.IService;
import org.jetbrains.annotations.NotNull;

/**
 * Classe composante de test implémentant une requête de methode PATCH.
 */
public class PatchServiceComposant extends RequeteHTTPPatchAuthBasic implements IService {
    /**
     * La resource à consulter sur l'hôte.
     */
    private final static String RESOURCE_FORMATEE = "%s/api/v2/entite/%s/document/%s";

    /**
     * La ressource du service à consulter.
     */
    private final String ressource;

    /**
     * Initialise une nouvelle instance de la classe {@link PatchServiceComposant}.
     *
     * @param url        L'URL sur laquelle se connecter.
     * @param login      Le login d'authentification.
     * @param motDePasse Le mot de passe du login.
     * @param idDossier  L'identifiant du dossier à consulter.
     */
    public PatchServiceComposant(@NotNull String url, @NotNull String login, @NotNull String motDePasse,
                                 String idDossier) {
        super(String.format(RESOURCE_FORMATEE, url, VariablesStatiques.ENTITE, idDossier), login, motDePasse);
        this.ressource = String.format(RESOURCE_FORMATEE, url, VariablesStatiques.ENTITE, idDossier);

        this.addDonnee("input_01", "valeur01");
        this.addDonnee("input_02", "valeur02");
        this.addDonnee("input_date_01", "08/02/2023");
    }

    @Override
    public byte[] getCorps() throws RequeteHTTPException {
        if(this.donnees.size() == 0) return null;
        StringBuilder buffer = new StringBuilder();

        boolean estPremier = true;
        for(String cle : this.donnees.keySet()) {
            if(estPremier) estPremier = false;
            else buffer.append("&");

            buffer.append(String.format("%s=%s", cle, donnees.get(cle)));
        }

        return buffer.toString().getBytes();
    }

    @Override
    public String getRessource() {
        return this.ressource;
    }
}
