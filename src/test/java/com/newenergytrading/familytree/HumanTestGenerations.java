package com.newenergytrading.familytree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HumanTestGenerations {

    @Test
    void testOne() {
        Human son = new Human(15, "Matthias", null, null);
        Assertions.assertEquals(1, son.getGenerationCount());
    }

    @Test
    void testTwo() {
        Human mother = new Human(30, "Hildie", null, null);
        Human father = new Human(31, "Peter", null, null);
        Human son = new Human(15, "Matthias", mother, father);
        Assertions.assertEquals(2, son.getGenerationCount());
    }

    @Test
    void testThree() {
        Human greatGrandFather1 = new Human(55, "Georg", null, null);
        Human grandMother1 = new Human(45, "grandle", null, null);
        Human grandFather1 = new Human(46, "gusti", null, null);
        Human grandFather2 = new Human(44, "hansle", null, greatGrandFather1);
        Human mother = new Human(30, "Hildie", grandMother1, grandFather1);
        Human father = new Human(31, "Peter", null, grandFather2);
        Human son = new Human(15, "Matthias", mother, father);
        Assertions.assertEquals(4, son.getGenerationCount());
    }

    @Test
    void testFour() {
        Human greatGreatGreatGreatGrandMother1 = new Human(55, "Georg", null, null);
        Human greatGreatGreatGrandFather1 = new Human(90, "Georg", greatGreatGreatGreatGrandMother1, null);
        Human greatGreatGrandMother1 = new Human(70, "Hanne", null, greatGreatGreatGrandFather1);
        Human greatGrandFather1 = new Human(55, "Georg", greatGreatGrandMother1, null);
        Human grandMother1 = new Human(45, "grandle", null, null);
        Human grandFather1 = new Human(46, "gusti", null, null);
        Human grandFather2 = new Human(44, "hansle", null, greatGrandFather1);
        Human mother = new Human(30, "Hildie", grandMother1, grandFather1);
        Human father = new Human(31, "Peter", null, grandFather2);
        Human son = new Human(15, "Matthias", mother, father);
        Assertions.assertEquals(7, son.getGenerationCount());
    }

    @Test
    void testFive() {
        Human greatGreatGreatGreatGrandMother1 = new Human(55, "Georg", null, null);
        Human greatGreatGreatGrandFather1 = new Human(90, "Georg", greatGreatGreatGreatGrandMother1, null);
        Human greatGreatGrandMother1 = new Human(70, "Hanne", null, greatGreatGreatGrandFather1);
        Human greatGrandFather1 = new Human(55, "Georg", greatGreatGrandMother1, null);
        Human greatGrandMother1 = new Human(56, "jana", greatGreatGrandMother1, null);
        Human grandMother1 = new Human(45, "grandle", null, null);
        Human grandFather1 = new Human(46, "gusti", null, null);
        Human grandFather2 = new Human(44, "hansle", greatGrandMother1, greatGrandFather1);
        Human grandMother2 = new Human(47, "sii", null, null);
        Human mother = new Human(30, "Hildie", grandMother1, grandFather1);
        Human father = new Human(31, "Peter", grandMother2, grandFather2);
        Human son = new Human(15, "Matthias", mother, father);
        Assertions.assertEquals(7, son.getGenerationCount());
    }

}
