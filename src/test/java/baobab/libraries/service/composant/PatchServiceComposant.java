package baobab.libraries.service.composant;

import baobab.libraries.requete.authentifie.base64.RequeteHTTPPatchAuthBasic;
import baobab.libraries.requete.noyau.RequeteHTTPException;
import baobab.libraries.service.IService;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

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
     * @throws MalformedURLException Si aucun protocole n'est spécifié, ou si un protocole inconnu est trouvé,
     *                               ou si la spécification est nulle, ou si l'URL analysée ne respecte pas
     *                               la syntaxe spécifique du protocole associé.
     * @throws ProtocolException     Si la méthode ne peut pas être réinitialisée ou si la méthode demandée
     *                               n'est pas valide pour HTTP.
     * @throws SecurityException     Si un gestionnaire de sécurité est défini et que la méthode est "TRACE",
     *                               mais que la NetPermission "allowHttpTrace" n'est pas accordée.
     * @throws IOException           Si une exception d'E/S se produit.
     */
    public PatchServiceComposant(@NotNull String url, @NotNull String login, @NotNull String motDePasse, String idDossier)
            throws IOException {
        super(String.format(RESOURCE_FORMATEE, url, VariablesStatique.ENTITE, idDossier), login, motDePasse);
        this.ressource = String.format(RESOURCE_FORMATEE, url, VariablesStatique.ENTITE, idDossier);

        this.addDonnee("input_01", "valeur01");
        this.addDonnee("input_03", "valeur02");
        this.addDonnee("input_date_01", "08/02/2023");
    }

    @Override
    public String appeler() throws IOException, RequeteHTTPException {
        this.envoyer();
        return this.recevoir();
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
