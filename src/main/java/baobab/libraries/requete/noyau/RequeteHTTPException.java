package baobab.libraries.requete.noyau;

/**
 * Exception personnalisée, utilisé en cas d'erreur spécifique à la librairie.
 */
public class RequeteHTTPException extends Exception {
    /**
     * Initialise une nouvelle instance de la classe {@link RequeteHTTPException}.
     * @param message Le message de l'erreur.
     */
    public RequeteHTTPException(String message) {
        super(message);
    }
}

