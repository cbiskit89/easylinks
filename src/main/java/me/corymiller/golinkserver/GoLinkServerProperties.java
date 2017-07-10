package me.corymiller.golinkserver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GoLinkServerProperties {
    static Properties appProps = new Properties();

    public GoLinkServerProperties() {
        try {
            appProps.load(new FileInputStream("src/main/java/me/corymiller/golinkserver/golinkserver.conf"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
