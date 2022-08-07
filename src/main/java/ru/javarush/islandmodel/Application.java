package ru.javarush.islandmodel;

import ru.javarush.islandmodel.model.island.Island;
import ru.javarush.islandmodel.system.PlantGrowth;
import ru.javarush.islandmodel.system.PropertiesReader;
import ru.javarush.islandmodel.system.Statistic;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Application {
    public static void main(String[] args) {
        int length = PropertiesReader.getProperty("length");
        int width = PropertiesReader.getProperty("width");

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

