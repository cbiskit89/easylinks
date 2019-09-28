package me.corymiller.easylinks;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EasyLinksServerProperties {
    static Properties appProps = new Properties();

    public EasyLinksServerProperties() {
        try {
            appProps.load(new FileInputStream("src/main/java/me/corymiller/easylinks/easylinks.conf"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
