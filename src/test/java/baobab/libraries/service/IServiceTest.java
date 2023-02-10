package baobab.libraries.service;

import baobab.libraries.requete.noyau.RequeteHTTPException;
import baobab.libraries.service.composant.*;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ProtocolException;

/**
 * Classe de test pour les classes implémentant les interfaces {@link IService}.
 */
public class IServiceTest {
    /**
     * Teste l'appel de différents services avec différentes méthodes.
     * @throws ProtocolException     Si la méthode ne peut pas être réinitialisée ou si la méthode demandée
     *                                  n'est pas valide pour HTTP.
     * @throws SecurityException     Si un gestionnaire de sécurité est défini et que la méthode est "TRACE",
     *                                  mais que la NetPermission "allowHttpTrace" n'est pas accordée.
     * @throws IOException           Si une exception d'E/S se produit.
     * @throws RequeteHTTPException  Si une exception spécifique à l'utilisation de la librairie a lieu.
     */
    @Test
    public void testerService() throws IOException, RequeteHTTPException, InterruptedException {
        // Test d'un service en utilisant une méthode de type multipart/form-data.
        JSONObject resultatMultipart1 = this.obtenirResultat(new MultipartFormDataServiceComposantDonnee1(
                VariablesStatiques.HOTE, VariablesStatiques.LOGIN, VariablesStatiques.MOT_DE_PASSE));
        Assertions.assertTrue(resultatMultipart1.has("id_d"));

        String idDossier = resultatMultipart1.getString("id_d");

        // Test d'un service utilisant une méthode de type GET.
        JSONObject resultatGet = this.obtenirResultat(new GetServiceComposant(
                VariablesStatiques.HOTE, VariablesStatiques.LOGIN, VariablesStatiques.MOT_DE_PASSE, idDossier));
        Assertions.assertTrue(resultatGet.has("info"));
        Assertions.assertEquals(idDossier, resultatGet.getJSONObject("info").getString("id_d"));

        // Test d'un service utilisant une méthode de type PATCH.
        JSONObject resultatPatch = this.obtenirResultat(new PatchServiceComposant(
                VariablesStatiques.HOTE, VariablesStatiques.LOGIN, VariablesStatiques.MOT_DE_PASSE, idDossier));
        Assertions.assertEquals("ok", resultatPatch.getString("result"));

        // Test d'un service utilisant une méthode de type PATCH.
        Assertions.assertThrows(RequeteHTTPException.class, () -> this.obtenirResultat(
                new MultipartFormDataServiceComposantDonnee2(
                VariablesStatiques.HOTE, VariablesStatiques.LOGIN, VariablesStatiques.MOT_DE_PASSE)));


        // Test d'un service utilisant une méthode de type multipart/form-data.
        JSONObject resultatMultipart2 = this.obtenirResultat(new MultipartFormDataServiceComposantFichier(
                VariablesStatiques.HOTE, VariablesStatiques.LOGIN, VariablesStatiques.MOT_DE_PASSE, idDossier));
        Assertions.assertEquals("ok", resultatMultipart2.getString("result"));
    }

    private JSONObject obtenirResultat(IService service)
            throws IOException, RequeteHTTPException, InterruptedException {
        return new JSONObject(service.appeler());
    }
}