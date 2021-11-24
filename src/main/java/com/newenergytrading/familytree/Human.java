package com.newenergytrading.familytree;

import java.util.ArrayList;
import java.util.List;

public class Human {

    private int age;
    private String firstName;
    private Human mother;
    private Human father;
    private List<Human> siblings = new ArrayList<>();

    public Human(int age, String firstName, Human mother, Human father) {
        this.age = age;
        this.firstName = firstName;
        this.mother = mother;
        this.father = father;
    }

    public Human getOldestFamilyMember() {
        if (this.mother == null && this.father == null) {
            return this;
        } else if (this.mother != null && this.father != null)  {
            if (this.getAge() < this.mother.getOldestFamilyMember().getAge() && this.getAge() < this.father.getOldestFamilyMember().getAge()) {
                Human oldestMother = this.mother.getOldestFamilyMember();
                Human oldestFather = this.father.getOldestFamilyMember();
                if (oldestMother.getAge() < oldestFather.getAge()) {
                    return oldestFather;
                } else {
                    return oldestMother;
                }
            }
        } else if (this.mother != null && this.father == null) {
            if (this.getAge() < this.mother.getOldestFamilyMember().getAge()) {
                return this.mother.getOldestFamilyMember();
            } else {
                return this;
            }
        } else {
            if (this.getAge() < this.father.getOldestFamilyMember().getAge()) {
                return this.father.getOldestFamilyMember();
            } else {
                return this;
            }
        }
        return this;
    }

    public int getGenerationCount() {
        if (this.mother == null && this.father == null) {
            return 1;
        } else if (this.mother != null && this.father != null)  {
            int ancestorsMother = this.mother.getGenerationCount();
            int ancestorsFather = this.father.getGenerationCount();
            if (ancestorsFather < ancestorsMother) {
                return 1 + ancestorsMother;
            } else {
                return 1 + ancestorsFather;
            }
        } else if (this.mother != null) {
            return 1 + this.mother.getGenerationCount();
        } else {
            return 1 + this.father.getGenerationCount();
        }
    }

    public int getAgeCounter() {
        int siblingsAges = 0;
        if (this.siblings.size() > 0) {
            for (Human sibling : this.siblings) {
                siblingsAges += sibling.getAge();
            }
        }
        if (this.mother == null && this.father == null) {
            return this.age + siblingsAges;
        } else if (this.mother != null && this.father != null)  {
            int ancestorsMother = this.mother.getAgeCounter();
            int ancestorsFather = this.father.getAgeCounter();
            return this.age + ancestorsFather + ancestorsMother + siblingsAges;
        } else if (this.mother != null) {
            return this.age + this.mother.getAgeCounter() + siblingsAges;
        } else {
            return this.age + this.father.getAgeCounter() + siblingsAges;
        }
    }

    public List<Human> getSiblings() {
        return siblings;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Human getMother() {
        return mother;
    }

    public void setMother(Human mother) {
        this.mother = mother;
    }

    public Human getFather() {
        return father;
    }

    public void setFather(Human father) {
        this.father = father;
    }
}
