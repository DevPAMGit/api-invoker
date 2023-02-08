package baobab.libraries.service;

import baobab.libraries.requete.noyau.IRequeteHTTP;

/**
 * Interface contractuelle pour un service d'API ou Web.
 */
public interface IService extends IRequeteHTTP {
    /**
     * Retourne la resource sollicitée par le service.
     * @return La resource sollicité par le service.
     */
    String getRessource();
}
