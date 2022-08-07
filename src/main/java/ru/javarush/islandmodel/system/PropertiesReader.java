package ru.javarush.islandmodel.system;

import ru.javarush.islandmodel.Application;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertiesReader {

    private PropertiesReader() {
    }

    public static int getProperty(String parameter) {
        Properties properties = new Properties();
        try(FileReader fileReader = new FileReader(Objects.requireNonNull(Application.class.getResource("/island.properties")).getFile())) {
            properties.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(properties.getProperty(parameter));
    }
}
