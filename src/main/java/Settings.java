import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "settings")
public class Settings implements Serializable {

    private static final long serialVersionUID = 1L;

    // Properties

    private String sourceHost;
    private int sourcePort;
    private String username;
    private String keystore;
    private String keystorePass;
    private String sourcePath;
    private String sourceFiles;
    private String localPath;
    private String direction;

// Getters and Setters

    public Settings() {
        super();
    }

    public Settings(String sourceHost, int sourcePort) {
        super();
        this.sourceHost = sourceHost;
        this.sourcePort = sourcePort;
    }

    public String getSourceHost() {
        return sourceHost;
    }

    @XmlElement
    public void setSourceHost(String sourceHost) {
        this.sourceHost = sourceHost;
    }

    public int getSourcePort() {
        return sourcePort;
    }

    @XmlElement
    public void setSourcePort(int sourcePort) {
        this.sourcePort = sourcePort;
    }

    public String getUsername() {
        return username;
    }

    @XmlElement
    public void setUsername(String username) {
        this.username = username;
    }

    public String getKeystore() {
        return keystore;
    }

    @XmlElement
    public void setKeystore(String keystore) {
        this.keystore = keystore;
    }

    public String getKeystorePass() {
        return keystorePass;
    }

    @XmlElement
    public void setKeystorePass(String keystorePass) {
        this.keystorePass = keystorePass;
    }

    public String getLocalPath() {
        return localPath;
    }

    @XmlElement
    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    @XmlElement
    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getDirection() {
        return direction;
    }

    @XmlElement
    public void setDirection(String direction) {
        this.direction = direction;
    }

    // Constructors

    public String getSourceFiles() {
        return sourceFiles;
    }

    @XmlElement
    public void setSourceFiles(String sourceFiles) {
        this.sourceFiles = sourceFiles;
    }

    // Overrides

    @Override
    public String toString() {
        return "  sourceHost  => " + sourceHost + System.lineSeparator()
                + "  sourcePort  => " + sourcePort + System.lineSeparator()
                + "  sourcePath  => " + sourcePath + System.lineSeparator()
                + "  sourceFiles => " + sourceFiles + System.lineSeparator();
    }
}

