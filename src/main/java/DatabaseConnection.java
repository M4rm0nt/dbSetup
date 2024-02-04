import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseConnection {
    private static final Logger logger = LogManager.getLogger(DatabaseConnection.class);

    private static final AppConfig CONFIG = AppConfig.getInstance();

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(CONFIG.getDbUrl(), CONFIG.getDbUser(), CONFIG.getDbPassword());
        } catch (SQLException e) {
            logger.error("Fehlermeldung: {}", e.getMessage());
            return null;
        }
    }
}