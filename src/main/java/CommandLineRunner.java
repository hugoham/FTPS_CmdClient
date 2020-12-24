import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.apache.commons.net.util.KeyManagerUtils;
import org.apache.commons.net.util.TrustManagerUtils;

import javax.net.ssl.KeyManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.regex.Pattern;

public class CommandLineRunner {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: pass a settings.xml file as an argument");
            System.exit(1);
        }

        log("CommandLineRunner started");
        System.setProperty("jdk.tls.useExtendedMasterSecret", "false");

        log("Using settings from " + args[0]);
        Settings settings = readSettings(args[0]);

        getFiles(settings);

    }

    private static void getFiles(final Settings settings) {
        SSLSessionReuseFTPSClient ftpClient = new SSLSessionReuseFTPSClient();
        log("Client constructed");

        ftpClient.addProtocolCommandListener(new PrintCommandListener(System.out));

        try {
            ftpClient.setKeyManager(getKeyManager(settings));
            log("KeyManager set");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        ftpClient.setTrustManager(TrustManagerUtils.getAcceptAllTrustManager());
        log("TrustManager set");

        try {
            ftpClient.connect(settings.getSourceHost(), settings.getSourcePort());
            log("Client connected");
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
        int reply = ftpClient.getReplyCode();
//        log("Reply: " + reply);

        try {
            ftpClient.changeWorkingDirectory(settings.getSourcePath());
//            FTPFile[] files = ftpClient.listFiles();
            FTPFile[] files = ftpClient.listFiles("", new FTPFileFilter() {
                @Override
                public boolean accept(FTPFile ftpFile) {
                    return Pattern.matches(settings.getSourceFiles(), ftpFile.getName());
                }
            });

            System.out.println("files.length = " + files.length);

            for (FTPFile file : files) {
                if (file.isFile()) {
                    boolean b = ftpClient.retrieveFile(file.getName(), new FileOutputStream(settings.getLocalPath() + file.getName()));
                }
            }

//            FTPFile[] dirs = ftpClient.listDirectories();
//
//            for (FTPFile dir : dirs) {
//                log(dir.getName());
//                ftpClient.changeWorkingDirectory(dir.getName());
//
//                FTPFile[] files = ftpClient.listFiles();
//
//                if (files.length == 0) {
//                    log("--> No files found");
//                }
//
//                for (FTPFile file : files) {
//                    log("--> " + file.getName());
//                }
//                ftpClient.changeToParentDirectory();
//            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ftpClient.logout();
            log("Client logged out");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ftpClient.disconnect();
            log("Client disconnected");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void log(String s) {
        System.out.println(s);
    }

    private static KeyManager getKeyManager(Settings settings) throws IOException, GeneralSecurityException {
//        String currentDir = FileSystems.getDefault()
//                .getPath("")
//                .toAbsolutePath()
//                .toString();

        File keyStoreFile = new File(settings.getKeystore());
        return KeyManagerUtils.createClientKeyManager(keyStoreFile, settings.getKeystorePass());
    }

    private static Settings readSettings(String settingsFile) {
        File xmlFile = new File(settingsFile);

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Settings.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Settings settings = (Settings) jaxbUnmarshaller.unmarshal(xmlFile);
            System.out.println(settings);
            return settings;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}
