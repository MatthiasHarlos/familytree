package com.newenergytrading.familytree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class HumanTestDeleteAllBelow {
    @Test
    void testOne() {
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
        List<Human> humanList = new ArrayList<>(List.of(greatGreatGreatGreatGrandMother1, greatGreatGreatGrandFather1, greatGreatGrandMother1, greatGrandFather1, grandMother1, grandFather1, grandFather2, mother, father, son));
        HashSet<Human> deleteList = new HashSet<>();

        son.deleteAllBelow(deleteList);
        System.out.println(deleteList);
        humanList.removeAll(deleteList);
        Assertions.assertEquals(10, deleteList.size());
        Assertions.assertEquals(0, humanList.size());


    }

    @Test
    void testTwo (){
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

        List<Human> humanList = new ArrayList<>(List.of(greatGreatGreatGreatGrandMother1, greatGreatGreatGrandFather1, greatGreatGrandMother1, greatGrandFather1, grandMother1, grandFather1, grandFather2, mother, father, son));
        HashSet<Human> deleteList = new HashSet<>();

        mother.deleteAllBelow(deleteList);
        System.out.println(deleteList);
        humanList.removeAll(deleteList);
        System.out.println(humanList);
        Assertions.assertEquals(3, deleteList.size());
        Assertions.assertEquals(7, humanList.size());
    }
}
