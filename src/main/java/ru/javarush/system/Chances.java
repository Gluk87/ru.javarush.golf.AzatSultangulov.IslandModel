package ru.javarush.system;

import ru.javarush.model.animals.Animal;
import ru.javarush.model.animals.herbivores.*;
import ru.javarush.model.animals.predators.*;

public class Chances {
    private static final Chances CHANCES = new Chances();

    private Chances() {
    }

    public static Chances getInstance() {
        return CHANCES;
    }

    public int getChanceToEat(Animal hunter, Animal victim) {
        int chance = 0;
        if (hunter instanceof Wolf) {
            if (victim instanceof Horse) chance = 10;
            else if (victim instanceof Deer) chance = 15;
            else if (victim instanceof Rabbit) chance = 60;
            else if (victim instanceof Mouse) chance = 80;
            else if (victim instanceof Goat) chance = 60;
            else if (victim instanceof Sheep) chance = 70;
            else if (victim instanceof Boar) chance = 15;
            else if (victim instanceof Buffalo) chance = 10;
            else if (victim instanceof Duck) chance = 40;
        } else if (hunter instanceof Boa) {
            if (victim instanceof Fox) chance = 15;
            else if (victim instanceof Rabbit) chance = 20;
            else if (victim instanceof Mouse) chance = 40;
            else if (victim instanceof Duck) chance = 10;
        } else if (hunter instanceof Fox) {
            if (victim instanceof Fox) chance = 15;
            else if (victim instanceof Rabbit) chance = 70;
            else if (victim instanceof Mouse) chance = 90;
            else if (victim instanceof Duck) chance = 60;
            else if (victim instanceof Caterpillar) chance = 40;
        } else if (hunter instanceof Bear) {
            if (victim instanceof Boa) chance = 80;
            else if (victim instanceof Horse) chance = 40;
            else if (victim instanceof Deer) chance = 80;
            else if (victim instanceof Rabbit) chance = 80;
            else if (victim instanceof Mouse) chance = 90;
            else if (victim instanceof Goat) chance = 70;
            else if (victim instanceof Sheep) chance = 70;
            else if (victim instanceof Boar) chance = 50;
            else if (victim instanceof Buffalo) chance = 20;
            else if (victim instanceof Duck) chance = 70;
        } else if (hunter instanceof Eagle) {
            if (victim instanceof Boa) chance = 80;
            else if (victim instanceof Fox) chance = 10;
            else if (victim instanceof Rabbit) chance = 90;
            else if (victim instanceof Mouse) chance = 90;
            else if (victim instanceof Duck) chance = 80;
        } else if (hunter instanceof Boar) {
            if (victim instanceof Mouse) chance = 50;
            else if (victim instanceof Caterpillar) chance = 90;
        } else if (hunter instanceof Mouse) {
            if (victim instanceof Caterpillar) chance = 90;
        } else if (hunter instanceof Duck) {
            if (victim instanceof Caterpillar) chance = 90;
        }
        return chance;
    }
}

