package baobab.libraries.requete.noyau;

import java.io.IOException;

/**
 * Interface contractuelle pour une requête.
 */
public interface IRequeteHTTP {
    /**
     * Méthode permettant d'appeler la requête Web.
     * @throws IOException          Si une exception d'entrée/sortie se produit.
     * @throws RequeteHTTPException Si une exception spécifique à l'utilisation de la librairie a lieu.
     */
    String appeler() throws IOException, RequeteHTTPException, InterruptedException;
}
