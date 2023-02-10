package baobab.libraries.requete.noyau;

import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.util.HashMap;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/** Classer permettant d'effectuer des requête HTTP vers un hote distant. */
public abstract class RequeteHTTP implements IRequeteHTTP {
    /**
     * Instance de connexion vers l'URL de la requête.
     */
    protected HttpRequest.Builder connexion;

    /**
     * La requête envoyée à l'hôte.
     */
    private HttpRequest requete;

    /**
     * La méthode de la requête.
     */
    protected final MethodeHTTP methode;

    /**
     * Liste des données à intégrer dans la requête.
     */
    protected final HashMap<String, Object> donnees;

    /**
     * Le code statut retour de la requête HTTP.
     */
    private int codeStatut;

    /**
     * Initialise une nouvelle instance de la classe {@link RequeteHTTP}.
     * @param methode La méthode de la requête HTTP.
     * @param url     L'URL sur laquelle se connecter.
     */
    public RequeteHTTP(@NotNull MethodeHTTP methode, @NotNull String url) {
        this.connexion = HttpRequest.newBuilder(URI.create(url));
        this.donnees = new HashMap<>();
        this.methode = methode;
        this.requete = null;
    }

    /**
     * Ajoute un entête à la requête.
     * @param cle    La clé de la requête.
     * @param valeur La valeur de la requête.
     */
    protected void setEnTete(@NotNull String cle, @NotNull String valeur) {
        this.connexion = this.connexion.header(cle, valeur);
    }

    /**
     * Méthode permettant d'envoyer des données sur l'hôte de connexion.
     * @throws RequeteHTTPException Si une exception spécifique à l'utilisation de la librairie a lieu.
     */
    protected void envoyer() throws RequeteHTTPException {
        // Récupération du corps de la requête.
        byte[] corps = this.getCorps();

        // Écriture du corps de la requête.
        if(corps != null && corps.length > 0)
            this.connexion = this.connexion.method(this.methode.nom, HttpRequest.BodyPublishers.ofByteArray(corps));

        // Envoi/Execution de la requête.
        this.requete = this.connexion.build();
    }

    /**
     * Méthode permettant de récupérer des données retournées par l'hôte au format {@link String}.
     * @return             Les données retournées par l'hôte au format {@link String}.
     * @throws IOException Si une exception d'entrée/sortie se produit.
     */
    protected String recevoir() throws IOException, InterruptedException {
        HttpResponse<String> reponse =  HttpClient.newHttpClient().send(this.requete, HttpResponse.BodyHandlers.ofString());
        this.codeStatut = reponse.statusCode();
        return reponse.body();
    }


    @Override
    public String appeler() throws IOException, RequeteHTTPException, InterruptedException {
        this.envoyer();
        return this.recevoir();
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

    @Override
    public int geStatut() throws RequeteHTTPException {
        if(this.requete == null)
            throw new RequeteHTTPException("La requête n'a pas été envoyé à l'hôte. En conséquence aucun statut n'est" +
                    " disponible.");

        return this.codeStatut;
    }
}
