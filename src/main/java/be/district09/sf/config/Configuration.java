package be.district09.sf.config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "configuration")
public class Configuration implements Serializable {

    private static final long serialVersionUID = 2L;

    Settings[] settings;

    public Settings[] getSettings() {
        return settings;
    }

    @XmlElement
    public void setSettings(Settings[] settings) {
        this.settings = settings;
    }

    public Configuration() {
        super();
    }

    @Override
    public String toString() {
        return "Configuration : " + settings.length + " settings";
    }
}
