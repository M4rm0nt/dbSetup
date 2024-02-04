import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.trace("Start des Programms");

        try {
            initializeApplication();
            logger.info("Anwendung erfolgreich initialisiert");
        } catch (Exception e) {
            logger.error("Fehler bei der Initialisierung der Anwendung", e);
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                logger.info("Datenbankverbindung erfolgreich hergestellt");
            } else {
                logger.error("Datenbankverbindung konnte nicht hergestellt werden");
                return;
            }
        } catch (Exception e) {
            logger.error("Fehler beim Schließen der Datenbankverbindung", e);
        }

        logger.info("Anwendung wird beendet");
    }

    private static void initializeApplication() {
        AppConfig config = AppConfig.getInstance();
        logger.debug("Konfiguration geladen: URL - " + config.getDbUrl());
    }

}
