package com.excelutility.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MessageResource {
    public static Properties prop = new Properties();

    static {
        InputStream input = MessageResource.class.getResourceAsStream("/resources/messages.properties");

        // load a properties file
        try {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String message(Enum key, Object... arguments) {
        String value = prop.getProperty(key.toString());
        int i = 0;
        for (Object obj : arguments) {
            value = value.replace("{" + i + "}", obj.toString());
        }
        return value;
    }
}
