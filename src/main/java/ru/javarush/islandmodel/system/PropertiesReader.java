package ru.javarush.islandmodel.system;

import ru.javarush.islandmodel.Application;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertiesReader {

    private PropertiesReader() {
    }

    public static int getLength() {
        return Integer.parseInt(getProperties().getProperty("length"));
    }

    public static int getWidth() {
        return Integer.parseInt(getProperties().getProperty("width"));
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        try(FileReader fileReader = new FileReader(Objects.requireNonNull(Application.class.getResource("/island.properties")).getFile())) {
            properties.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
