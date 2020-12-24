import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Logging {
    private static Logger LOGGER = null;

    static {
//        System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %5$s %n");
//        LOGGER = Logger.getLogger(CommandLineRunner.class.getName());
//        LOGGER.setLevel(Level.ALL);

        InputStream stream = CommandLineRunner.class.getClassLoader().
                getResourceAsStream("logging.properties");
        try {
            LogManager.getLogManager().readConfiguration(stream);
            LOGGER= Logger.getLogger(CommandLineRunner.class.getName());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void error(String s) {
        LOGGER.severe(s);
    }

    public static void info(String s) {
        LOGGER.info(s);
    }

    public static void debug(String s) {
        LOGGER.fine(s);
    }

}
