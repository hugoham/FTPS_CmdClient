package be.district09.sf;

import be.district09.sf.config.Configuration;
import be.district09.sf.config.Settings;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.util.KeyManagerUtils;
import org.apache.commons.net.util.TrustManagerUtils;

import javax.net.ssl.KeyManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.util.regex.Pattern;

import static java.lang.System.exit;

public class CommandLineRunner {

    public static void main(String[] args) {
        if (args.length == 0) {
            Logging.error("Usage: pass a settings.xml file as an argument");
            exit(1);
        }

        Logging.info(CommandLineRunner.class.getName() + " started");
        System.setProperty("jdk.tls.useExtendedMasterSecret", "false");

        Logging.info("Using settings from " + args[0]);
        Configuration config = readConfig(args[0]);

        assert config != null;
        for (Settings settings: config.getSettings()) {
            Logging.info("Using settings for: " + settings.getName());
            Logging.debug("settings.getDirection() = " + settings.getDirection());
            if (settings.getDirection().equalsIgnoreCase("GET")) {
                Logging.debug("GET");
                getFiles(settings);
            }
            if (settings.getDirection().equalsIgnoreCase("PUT")) {
                Logging.debug("PUT");
                putFiles(settings);
            }
        }
    }

    private static SSLSessionReuseFTPSClient getClient(Settings settings) {
        SSLSessionReuseFTPSClient ftpClient = new SSLSessionReuseFTPSClient();
        Logging.debug("Client constructed");
        ftpClient.addProtocolCommandListener(new PrintCommandListener(System.out));

        try {
            ftpClient.setKeyManager(getKeyManager(settings));
            Logging.debug("KeyManager set");
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
            exit(1);
        }

        ftpClient.setTrustManager(TrustManagerUtils.getAcceptAllTrustManager());
        Logging.debug("TrustManager set");

        try {
            ftpClient.connect(settings.getRemoteHost(), settings.getRemotePort());
            Logging.debug("Client connected");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ftpClient.login(settings.getUsername(), "");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ftpClient.execPBSZ(0);
            ftpClient.execPROT("P");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ftpClient.enterLocalPassiveMode();

        return ftpClient;
    }

    private static void putFiles(final Settings settings) {
        SSLSessionReuseFTPSClient ftpClient = getClient(settings);

        // do PUT stuff
        try {
            ftpClient.changeWorkingDirectory(settings.getRemotePath());

            File localDir = new File(settings.getLocalPath());
            File[] files = localDir.listFiles((dir, name) -> Pattern.matches(settings.getFilter(), name));

            assert files != null;
            for (File file:files) {

                FileInputStream inputStream = new FileInputStream(file);
                boolean b = ftpClient.storeFile(file.getName(), inputStream);
                inputStream.close();
                if (b) {
                    if (settings.getAction().equalsIgnoreCase("DELETE")) {
                        boolean deleted = file.delete();
                        if (deleted)
                            Logging.info(file.getPath() + " deleted.");
                    }
                    if (settings.getAction().equalsIgnoreCase("MOVE")) {
                        FileSystem fs = FileSystems.getDefault();
                        Path newPath = fs.getPath(settings.getMovePath(), file.getName());
                        Files.move(file.toPath(), newPath);
                        Logging.info(file.getPath() + " ==> " + newPath.toString());
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        logoutAndDisconnect(ftpClient);
    }

    private static void getFiles(final Settings settings) {
        SSLSessionReuseFTPSClient ftpClient = getClient(settings);

        try {
            ftpClient.changeWorkingDirectory(settings.getRemotePath());
            FTPFile[] files = ftpClient.listFiles("", ftpFile -> Pattern.matches(settings.getFilter(), ftpFile.getName()));

            Logging.debug("files.length = " + files.length);

            for (FTPFile file : files) {
                if (file.isFile()) {
                    FileSystem fs = FileSystems.getDefault();
                    Path localPath = fs.getPath(settings.getLocalPath(), file.getName());
                    boolean b = ftpClient.retrieveFile(file.getName(), new FileOutputStream(localPath.toString()));

                    if (b) {
                        if (settings.getAction().equalsIgnoreCase("DELETE")) {
                            ftpClient.deleteFile(file.getName());
                            Logging.info(file.getName() + " deleted.");
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        logoutAndDisconnect(ftpClient);
    }

    private static void logoutAndDisconnect(SSLSessionReuseFTPSClient ftpClient) {
        try {
            ftpClient.logout();
            Logging.debug("Client logged out");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ftpClient.disconnect();
            Logging.debug("Client disconnected");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static KeyManager getKeyManager(Settings settings) throws IOException, GeneralSecurityException {
//        String currentDir = FileSystems.getDefault()
//                .getPath("")
//                .toAbsolutePath()
//                .toString();

        File keyStoreFile = new File(settings.getKeystore());
        return KeyManagerUtils.createClientKeyManager(keyStoreFile, settings.getKeystorePass());
    }

    private static Configuration readConfig(String configFile) {
        File xmlFile = new File(configFile);

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Configuration.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Configuration config = (Configuration) jaxbUnmarshaller.unmarshal(xmlFile);
            Logging.info(config.toString());
            return config;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
