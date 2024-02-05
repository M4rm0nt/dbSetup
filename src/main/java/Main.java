import config.AppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        logger.trace("Start des Programms");

        try {
            initializeApplication();
            logger.info("Anwendung erfolgreich initialisiert");
        } catch (Exception e) {
            logger.error("Fehler bei der Initialisierung der Anwendung: {}", e.getMessage());
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                logger.info("Datenbankverbindung erfolgreich hergestellt");
            } else {
                logger.error("Datenbankverbindung konnte nicht hergestellt werden");
                return;
            }
        } catch (SQLException sqlEx) {
            logger.error("Fehler bei der Herstellung der Datenbankverbindung: {}", sqlEx.getMessage());
        } catch (Exception e) {
            logger.error("Allgemeiner Fehler: {}", e.getMessage());
        }
        logger.info("Anwendung wird beendet");
    }

    private static void initializeApplication() {
        AppConfig config = AppConfig.getInstance();
        logger.debug("Konfiguration geladen: URL - " + config.getDbUrl());
    }

}
