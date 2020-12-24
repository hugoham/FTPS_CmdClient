package be.district09.sf;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Logging {
    private static Logger LOGGER = null;

    static {
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
