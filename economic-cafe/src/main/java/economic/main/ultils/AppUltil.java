package economic.main.ultils;

import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import economic.main.constants.TypeFileImage;

import java.net.UnknownHostException;

@Component
public class AppUltil {

    @Autowired
    private Environment environment;

    private String port;

    /**
     * Get port.
     *
     * @return
     */
    public String getPort() {
        if (port == null) port = environment.getProperty("local.server.port");
        return port;
    }

    public String getServerUrlPrefi() {
        return "http://" + InetAddress.getLoopbackAddress().getHostName() + ":" + getPort();
    }

    public String getUrlImage(String nameImage, TypeFileImage typeFileImage){
        return getServerUrlPrefi() + "/" + "images/" + typeFileImage.value() + "/" + nameImage;
    }
}
