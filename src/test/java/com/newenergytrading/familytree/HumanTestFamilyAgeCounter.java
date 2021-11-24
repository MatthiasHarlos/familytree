package com.newenergytrading.familytree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HumanTestFamilyAgeCounter {

    @Test
    void testOne() {
        Human son = new Human(15, "Matthias", null, null);
        son.getSiblings().add(new Human(14, "bruder1", null, null));
        son.getSiblings().add(new Human(16, "bruder2", null, null));
        Assertions.assertEquals(45, son.getAgeCounter());

    }

    @Test
    void testTwo() {
        Human mother = new Human(30, "Hildie", null, null);
        Human father = new Human(31, "Peter", null, null);
        father.getSiblings().add(new Human(30, "bruder1", null, null));
        father.getSiblings().add(new Human(35, "bruder2", null, null));
        Human son = new Human(15, "Matthias", mother, father);
        son.getSiblings().add(new Human(14, "bruder1", null, null));
        son.getSiblings().add(new Human(16, "bruder2", null, null));
        Assertions.assertEquals(30 + 31 + 30 + 35 + 15 + 14 + 16, son.getAgeCounter());
    }

    @Test
    void testThree() {
        Human greatGrandFather1 = new Human(55, "Georg", null, null);
        Human grandMother1 = new Human(45, "grandle", null, null);
        grandMother1.getSiblings().add(new Human(30, "bruder1", null, null));
        grandMother1.getSiblings().add(new Human(35, "bruder2", null, null));
        Human grandFather1 = new Human(46, "gusti", null, null);
        Human grandFather2 = new Human(44, "hansle", null, greatGrandFather1);
        Human mother = new Human(30, "Hildie", grandMother1, grandFather1);
        Human father = new Human(31, "Peter", null, grandFather2);
        father.getSiblings().add(new Human(30, "bruder1", null, null));
        father.getSiblings().add(new Human(35, "bruder2", null, null));
        Human son = new Human(15, "Matthias", mother, father);
        Assertions.assertEquals(55 + 45 + 30 + 35 + 46 + 44 + 30 + 31 + 30 + 35 + 15, son.getAgeCounter());
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
        Assertions.assertEquals(55+90+70+55+45+46+44+30+31+15, son.getAgeCounter());
    }

    @Test
    void testFive() {
        Human greatGreatGreatGreatGrandMother1 = new Human(55, "Georg", null, null);
        Human greatGreatGreatGrandFather1 = new Human(90, "Georg", greatGreatGreatGreatGrandMother1, null);
        Human greatGreatGrandMother1 = new Human(70, "Hanne", null, greatGreatGreatGrandFather1);

        greatGreatGrandMother1.getSiblings().add(new Human(30, "bruder1", null, null));
        greatGreatGrandMother1.getSiblings().add(new Human(35, "bruder2", null, null));

        Human greatGrandFather1 = new Human(55, "Georg", greatGreatGrandMother1, null);
        Human greatGrandMother1 = new Human(56, "jana", null, null);
        Human grandMother1 = new Human(45, "grandle", null, null);

        grandMother1.getSiblings().add(new Human(30, "bruder1", null, null));
        grandMother1.getSiblings().add(new Human(35, "bruder2", null, null));

        Human grandFather1 = new Human(46, "gusti", null, null);
        Human grandFather2 = new Human(44, "hansle", greatGrandMother1, greatGrandFather1);
        Human grandMother2 = new Human(47, "sii", null, null);

        Human mother = new Human(30, "Hildie", grandMother1, grandFather1);
        mother.getSiblings().add(new Human(30, "bruder1", null, null));
        mother.getSiblings().add(new Human(35, "bruder2", null, null));

        Human father = new Human(31, "Peter", grandMother2, grandFather2);

        father.getSiblings().add(new Human(30, "bruder1", null, null));
        father.getSiblings().add(new Human(35, "bruder2", null, null));

        Human son = new Human(15, "Matthias", mother, father);
        Assertions.assertEquals(55 + 90 + 70 + 30 + 35 + 55 + 56 + 45 + 30 + 35 + 46 + 44 + 47 + 30 + 30 + 35 + 31 + 30 + 35 + 15, son.getAgeCounter());
    }
}
