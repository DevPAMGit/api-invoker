package baobab.libraries.requete.noyau;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/** Classer permettant d'effectuer des requête HTTP vers un hote distant. */
public abstract class RequeteHTTP implements IRequeteHTTP {
    /**
     * Instance de connexion vers l'URL de la requête.
     */
    protected final HttpURLConnection connexion;

    /**
     * Liste des données à intégrer dans la requête.
     */
    protected final HashMap<String, Object> donnees;

    /**
     * Initialise une nouvelle instance de la classe {@link RequeteHTTP}.
     * @param methode                           La méthode de la requête HTTP.
     * @param url                               L'URL sur laquelle se connecter.
     * @throws java.net.MalformedURLException   Si aucun protocole n'est spécifié, ou si un protocole inconnu est trouvé,
     *                                          ou si la spécification est nulle, ou si l'URL analysée ne respecte pas
     *                                          la syntaxe spécifique du protocole associé.
     * @throws java.net.ProtocolException       Si la méthode ne peut pas être réinitialisée ou si la méthode demandée
     *                                          n'est pas valide pour HTTP.
     * @throws SecurityException                Si un gestionnaire de sécurité est défini et que la méthode est "TRACE",
     *                                          mais que la NetPermission "allowHttpTrace" n'est pas accordée.
     * @throws IOException                      si une exception d'E/S se produit.
     */
    public RequeteHTTP(@NotNull MethodeHTTP methode, @NotNull String url) throws IOException {
        this.connexion = (HttpURLConnection) new URL(url).openConnection();
        this.connexion.setRequestMethod(methode.nom);
        this.donnees = new HashMap<>();
    }

    /**
     * Ajoute un entête à la requête.
     * @param cle                       La clé de la requête.
     * @param valeur                    La valeur de la requête.
     * @throws IllegalStateException    Si l'instance est déjà connectée.
     * @throws NullPointerException     Si la clé est null.
     */
    protected void setEnTete(@NotNull String cle, @NotNull String valeur) {
        this.connexion.setRequestProperty(cle, valeur);
    }

    /**
     * Méthode permettant d'envoyer des données sur l'hôte de connexion.
     * @throws IOException  Si une exception d'entrée/sortie se produit.
     * @throws RequeteHTTPException Si une exception spécifique à l'utilisation de la librairie a lieu.
     */
    protected void envoyer() throws IOException, RequeteHTTPException {
        byte[] corps = this.getCorps();
        OutputStream fluxSortie;

        if(corps == null || corps.length == 0) return;

        this.connexion.setDoOutput(true);
        fluxSortie = this.connexion.getOutputStream();
        fluxSortie.write(corps);

        fluxSortie.flush();
        fluxSortie.close();
    }

    /**
     * Méthode permettant de récupérer des données retournées par l'hôte.
     * @return              Les données retournées par l'hôte en chaîne de caractères.
     * @throws IOException Si une exception d'entrée/sortie se produit.
     */
    protected String recevoir() throws IOException {
        BufferedReader lecteur;

        try{ lecteur = new BufferedReader(new InputStreamReader(this.connexion.getInputStream())); }
        catch (IOException e) { lecteur = new BufferedReader(new InputStreamReader(this.connexion.getErrorStream())); }

        String buffer;
        StringBuilder resultat = new StringBuilder();

        while((buffer = lecteur.readLine()) != null) resultat.append(buffer);

        lecteur.close();
        return resultat.toString();
    }

    /**
     * Ajoute des données à ajouter dans la requête.
     * @param cle       Le nom de la donnée.
     * @param valeur    La valeur de la donnée.
     */
    public void addDonnee(@NotNull String cle, @NotNull Object valeur) {
        this.donnees.put(cle, valeur);
    }

    /**
     * Permet de récupérer le corps de la requête.
     * @return  Le corps de la requête.
     * @throws RequeteHTTPException Si une exception spécifique à l'utilisation de la librairie a lieu.
     */
    public abstract byte[] getCorps() throws RequeteHTTPException;
}
