package ru.javarush.islandmodel;

import ru.javarush.islandmodel.model.island.Island;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class Application {
    public static void main(String[] args) {
        //   System.out.println("Введите длину острова:");
        //   Scanner scanner = new Scanner(System.in);
        //   int length = scanner.nextInt();
        //   System.out.println("Введите ширину острова:");
        //   int width = scanner.nextInt();

        Properties properties = new Properties();
        try(FileReader fileReader = new FileReader(Objects.requireNonNull(Application.class.getResource("/island.properties")).getFile())) {
            properties.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int length = Integer.parseInt(properties.getProperty("length"));
        int width = Integer.parseInt(properties.getProperty("width"));

        System.out.println("Остров размером " + length + " X " + width);

        Island island = new Island(length, width);
        island.initialize();

        //    ExecutorService executorService = Executors.newFixedThreadPool(2);
        //    while (island.getCountAnimals() > 0) {
        //            executorService.execute(new Tact(island));
        //    }

        System.out.println("--------------------------------------------------------------");
        System.out.println("Животные покушали:");
        island.startEating();
        island.print();

        System.out.println("--------------------------------------------------------------");
        System.out.println("Животные перешли в другие локации:");
        island.startMoving();
        island.print();

        System.out.println("--------------------------------------------------------------");
        System.out.println("Животные размножаются:");
        island.startBreeding();
        island.print();
    }
}

