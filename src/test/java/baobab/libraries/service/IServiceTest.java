package baobab.libraries.service;

import baobab.libraries.requete.noyau.RequeteHTTPException;
import baobab.libraries.service.composant.*;
import org.json.JSONObject;
import org.junit.jupiter.api.*;

import java.io.IOException;

/**
 * Classe de test pour les classes implémentant les interfaces {@link IService}.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IServiceTest {
    /**
     * Modele de données pour les tests.
     */
    private static ModeleDonneesTest modele;

    /**
     * Initialisation du modèle de données de tests.
     */
    @BeforeAll
    public static void initialiser(){
        modele = new ModeleDonneesTest();
    }

    @Test
    @Order(1)
    @DisplayName("Test de l'envoie d'une méthode en méthode POST multipart/form-data.")
    public void creerEntite() throws IOException, RequeteHTTPException, InterruptedException {
        JSONObject entite = this.obtenirResultat(new EntiteCreerComposant(
                VariablesStatiques.HOTE, VariablesStatiques.LOGIN, VariablesStatiques.MOT_DE_PASSE));
        Assertions.assertTrue(entite.has("id_e"));
        modele.idEntite = entite.getString("id_e");
    }

    @Test
    @Order(2)
    @DisplayName("Test l'envoi d'une méthode GET.")
    public void consulterEntite() throws IOException, RequeteHTTPException, InterruptedException {
        JSONObject entite = this.obtenirResultat(new EntiteConsulterComposant(VariablesStatiques.HOTE,
                            VariablesStatiques.LOGIN, VariablesStatiques.MOT_DE_PASSE, modele.idEntite));
        Assertions.assertTrue(entite.has("id_e"));
        Assertions.assertEquals(modele.idEntite, entite.get("id_e"));
    }

    @Test
    @Order(3)
    @DisplayName("Test de l'envoi d'une méthode en DELETE.")
    public void supprimerEntite() throws IOException, RequeteHTTPException, InterruptedException {
        JSONObject resultat = this.obtenirResultat(new EntiteSupprimerComposant(VariablesStatiques.HOTE,
                              VariablesStatiques.LOGIN, VariablesStatiques.MOT_DE_PASSE, modele.idEntite));
        Assertions.assertTrue(resultat.has("result"));
        Assertions.assertEquals(resultat.getString("result"), "ok");
    }

    private JSONObject obtenirResultat(IService service)
            throws IOException, RequeteHTTPException, InterruptedException {
        return new JSONObject(service.appeler());
    }
}