package ru.javarush.islandmodel;

import ru.javarush.islandmodel.model.island.Island;
import ru.javarush.islandmodel.system.PlantGrowth;
import ru.javarush.islandmodel.system.Statistic;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Application {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try(FileReader fileReader = new FileReader(Objects.requireNonNull(Application.class.getResource("/island.properties")).getFile())) {
            properties.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int length = Integer.parseInt(properties.getProperty("length"));
        int width = Integer.parseInt(properties.getProperty("width"));

        System.out.println("Game Start. Island size: " + length + " X " + width);

        Island island = new Island(length, width);
        island.initialize();
        Statistic statistic = new Statistic(island);
        PlantGrowth plantGrowth = new PlantGrowth(island);

        ScheduledExecutorService executorStat = Executors.newScheduledThreadPool(5);
        executorStat.scheduleWithFixedDelay(statistic, 3, 1, TimeUnit.SECONDS);

        ScheduledExecutorService executorLifeCycle = Executors.newScheduledThreadPool(5);
        executorLifeCycle.scheduleWithFixedDelay(island, 2, 1, TimeUnit.SECONDS);

        ScheduledExecutorService executorPlantGrowth = Executors.newScheduledThreadPool(5);
        executorPlantGrowth.scheduleWithFixedDelay(plantGrowth, 3, 3, TimeUnit.SECONDS);

        while (true) {
            if (island.getCountAnimals() == 0) {
                break;
            }
        }
        executorLifeCycle.shutdown();
        executorStat.shutdown();
        executorPlantGrowth.shutdown();
        System.out.println("==================================================");
        System.out.println("Game over");
    }
}

