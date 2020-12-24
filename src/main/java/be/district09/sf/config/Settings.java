package be.district09.sf.config;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class Settings implements Serializable {

    private static final long serialVersionUID = 1L;

    // Properties

    private String remoteHost;
    private int remotePort;
    private String username;
    private String keystore;
    private String keystorePass;
    private String remotePath;
    private String filter;
    private String localPath;
    private String direction;
    private String name;
    private String action;
    private String movePath;

// Getters and Setters


    public Settings() {
        super();
    }

    public String getRemoteHost() {
        return remoteHost;
    }

    @XmlElement
    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
    }

    public int getRemotePort() {
        return remotePort;
    }

    @XmlElement
    public void setRemotePort(int remotePort) {
        this.remotePort = remotePort;
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

    public String getRemotePath() {
        return remotePath;
    }

    @XmlElement
    public void setRemotePath(String remotePath) {
        this.remotePath = remotePath;
    }

    public String getDirection() {
        return direction;
    }

    @XmlElement
    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getFilter() {
        return filter;
    }

    @XmlElement
    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return action;
    }

    @XmlElement
    public void setAction(String action) {
        this.action = action;
    }

    public String getMovePath() {
        return movePath;
    }

    // Constructors

    @XmlElement
    public void setMovePath(String movePath) {
        this.movePath = movePath;
    }

    // Overrides

    @Override
    public String toString() {
        return "  remoteHost  => " + remoteHost + System.lineSeparator()
                + "  remotePort  => " + remotePort + System.lineSeparator()
                + "  remotePath  => " + remotePath + System.lineSeparator()
                + "  filter => " + filter + System.lineSeparator();
    }
}

