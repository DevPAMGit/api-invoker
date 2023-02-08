package baobab.libraries.requete.noyau;

/**
 * Enumération de la liste des méthodes HTTP disponibles.
 */
public enum MethodeHTTP {
    /**
     * La méthode HTTP POST.
     */
    POST("POST"),

    /**
     * La méthode HTTP GET.
     */
    GET("GET"),

    /**
     * La méthode HTTP PATCH.
     */
    PATCH("PATCH"),

    /**
     * La méthode HTTP DELETE.
     */
    DELETE("DELETE");

    /**
     * Le nom de la méthode HTTP.
     */
    public final String nom;

    /**
     * Initialise une nouvelle instance l'énumération MethodeHTTP */
    MethodeHTTP(String nom) {
        this.nom = nom;
    }
}
