package baobab.libraries.requete.authentifie.base64;

import baobab.libraries.requete.noyau.RequeteHTTPException;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

/**
 * Requête permettant d'effectuer des requêtes HTTP multipart/form-data avec une authentication Basic encodée en Base 64
 * en méthode POST.
 */
public abstract class RequeteHTTPMulipartFormData extends RequeteHTTPPostAuthBasic {
    /**
     * Le séparateur d'argument de la requête.
     */
    private static final String SEPARATEUR = "------SEPARATEUR_ARGUMENT";

    /**
     * Le saut de ligne d'une requête.
     */
    protected final static String SAUT = "\r\n";

    /**
     * Initialise une nouvelle instance de la classe {@link RequeteHTTPMulipartFormData}.
     *
     * @param url        L'URL sur laquelle se connecter.
     * @param login      Le login d'authentification.
     * @param motDePasse Le mot de passe du login.
     * @throws MalformedURLException Si aucun protocole n'est spécifié, ou si un protocole inconnu est trouvé,
     *                                  ou si la spécification est nulle, ou si l'URL analysée ne respecte pas
     *                                  la syntaxe spécifique du protocole associé.
     * @throws ProtocolException     Si la méthode ne peut pas être réinitialisée ou si la méthode demandée
     *                                  n'est pas valide pour HTTP.
     * @throws SecurityException     Si un gestionnaire de sécurité est défini et que la méthode est "TRACE",
     *                                  mais que la NetPermission "allowHttpTrace" n'est pas accordée.
     * @throws IOException           Si une exception d'E/S se produit.
     */
    public RequeteHTTPMulipartFormData(@NotNull String url, @NotNull String login, @NotNull String motDePasse)
            throws IOException {
        super(url, login, motDePasse);
        this.setEnTete("Connection", "Keep-Alive");
        this.setEnTete("Cache-Control", "no-cache");
        this.setEnTete("Content-Type", String.format("multipart/form-data;boundary=\"%s\"", SEPARATEUR));
    }

    @Override
    public byte[] getCorps() throws RequeteHTTPException {
        byte[] resultat = new byte[]{};
        for(String cle : this.donnees.keySet())
            resultat = ArrayUtils.addAll(resultat, this.getDonnee(cle));
        return ArrayUtils.addAll(resultat, String.format("--%s--", SEPARATEUR).getBytes());
    }

    /**
     * Méthode permettant de récupérer et formater une donnée pour un envoi à l'hôte.
     * @param cle                       La clé de la données.
     * @return                          La donnée formatée pour envoi.
     * @throws RequeteHTTPException     Si une exception spécifique à l'utilisation de la librairie a lieu.
     */
    private byte[] getDonnee(String cle) throws RequeteHTTPException {
        Object valeur = this.donnees.get(cle);
        if(valeur instanceof String) return this.getDonneeString(cle, (String)valeur);
        throw new RequeteHTTPException(String.format("le type de donnée '%s' n'est pas gérer.",
                valeur.getClass().getSimpleName()));
    }

    /**
     * Méthode permettant de récupérer et formater une donnée de type {@link String} pour un envoi à l'hôte.
     * @param cle       La clé de la données.
     * @param valeur    La valeur de la donnée au format {@link String}.
     * @return          La donnée formatée pour envoi.
     */
    private byte[] getDonneeString(String cle, String valeur) {
        String resultat = String.format("--%s%s", SEPARATEUR, SAUT) +
                String.format("Content-Disposition: form-data; name=\"%s\"%s", cle, SAUT) +
                String.format("Content-Type: text/plain%s", SAUT) +
                String.format("%s%s%s", SAUT, valeur, SAUT);

        return resultat.getBytes();
    }
}
