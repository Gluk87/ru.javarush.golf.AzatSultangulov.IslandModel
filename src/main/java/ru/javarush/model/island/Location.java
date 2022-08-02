package ru.javarush.model.island;

import lombok.Getter;
import ru.javarush.exceptions.MissingAnnotationException;
import ru.javarush.model.animals.Animal;
import ru.javarush.model.animals.AnimalFactory;
import ru.javarush.model.animals.AnimalTypes;
import ru.javarush.model.animals.Characteristics;
import ru.javarush.model.animals.herbivores.*;
import ru.javarush.model.animals.predators.*;
import ru.javarush.model.plants.Plant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@SuppressWarnings("unchecked")
public class Location {
    private final Coordinates coordinates;
    //  private final int borderX;
    //  private final int borderY;
    private final List<Predator> predators;
    private final List<Herbivore> herbivores;
    private final List<Plant> plants;

    public Location(Coordinates coordinates) {
        this.coordinates = coordinates;
        //   this.borderX = borderX;
        //   this.borderY = borderY;
        predators = (List<Predator>) createAnimals(new PredatorFactory());
        herbivores = (List<Herbivore>) createAnimals(new HerbivoreFactory());
        plants = createPlants();
        Collections.shuffle(predators);
        Collections.shuffle(herbivores);
    }

    public void eating() {
        predators.forEach(predator -> predator.eat(herbivores));
        for (int i = 0; i < predators.size(); i++) {
            predators.get(i).eat(predators);
        }
        herbivores.forEach(herbivore -> herbivore.eat(plants));
    }

    public void moving() {
        predators.removeIf(predator -> predator.move(this));
        herbivores.removeIf(herbivore -> herbivore.move(this));
    }

    public void breeding() {
        for (int i = 0; i < predators.size(); i++) {
            predators.get(i).breed(this);
        }
        for (int i = 0; i < herbivores.size(); i++) {
            herbivores.get(i).breed(this);
        }
    }

    public void dying() {
        predators.removeIf(Animal::die);
        herbivores.removeIf(Animal::die);
    }

    public void addAnimal(Animal animal) {
        if (animal instanceof Herbivore herbivore) {
            herbivores.add(herbivore);
        } else if (animal instanceof Predator predator) {
            predators.add(predator);
        }
    }

    public void printInfo() {
        System.out.println("==================================================");
        System.out.println("Location [" + coordinates.getX() + ", " + coordinates.getY() + "]");
        System.out.print("Predators [ ");
        // for (Predator predator: PredatorTypes.values()) {
        //     System.out.print(predator.toString() + ":" + predators.stream().filter(predator::equals).count() + " ");
        // }
        System.out.print(predators);

        System.out.println("]");
        System.out.print("Herbivores [ ");
        //   for (Herbivore herbivore: HerbivoreTypes.getInstance().getTypes()) {
        //        System.out.print(herbivore.toString() + ":" + herbivores.stream().filter(herbivore::equals).count() + " ");
        //   }
        System.out.print(herbivores);
        System.out.println("]");
        System.out.print("Plants [ ");
        //   for (Plant plant: PlantTypes.getInstance().getTypes()) {
        //       System.out.print(plant.toString() + ":" + plants.stream().filter(plant::equals).count() + " ");
        //   }
        System.out.println(plants);
        System.out.println("]");
    }

    private List<? extends Animal> createAnimals(AnimalFactory factory) {
        List<Animal> animalList = new ArrayList<>();
        AnimalTypes[] animalTypes = AnimalTypes.values();
        for (AnimalTypes animalType: animalTypes) {
            Animal animal = factory.createAnimal(animalType);
            if (animal == null) {
                continue;
            }
            int animalCount = getRandomCount(getMaxOnOneLocation(animal));
            for (int i = 0; i < animalCount; i++) {
                animalList.add(factory.createAnimal(animalType));
            }
        }
        return animalList;
    }

    private List<Plant> createPlants() {
        List<Plant> plantList = new ArrayList<>();
        int plantCount = getRandomCount(getMaxOnOneLocation(new Plant()));
        for (int i = 0; i < plantCount; i++) {
            plantList.add(new Plant());
        }
        return plantList;
    }

    private int getRandomCount(int maxOnOneLocation) {
        return ThreadLocalRandom.current().nextInt(maxOnOneLocation);
    }

    private int getMaxOnOneLocation(Object o) {
        if (!o.getClass().isAnnotationPresent(Characteristics.class)){
            throw new MissingAnnotationException("Missing annotation " + Characteristics.class +" for " + o.getClass().getName());
        }
        Characteristics characteristics = o.getClass().getAnnotation(Characteristics.class);
        return characteristics.maxOnOneLocation();
    }
}

