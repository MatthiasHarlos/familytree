package com.newenergytrading.familytree;

import java.util.ArrayList;
import java.util.List;

public class Human {

    private int age;
    private String firstName;
    private String lastName;
    private Human mother;
    private Human father;
    private List<Human> siblings = new ArrayList<>();
    private String country;

    public String getFamilyTreeSiblings() {
        String siblingString = "";
        if (this.siblings != null) {
            for (Human human : this.siblings) {
                siblingString += "<span href=\"#\">" +
                        human.getFirstName() + "</span>";
            }
            return siblingString;
        }
        return siblingString;
    }

    public String getFamilyTree() {
        if (this.mother == null && this.father == null) {
            return "<li>\n" +
                    "<a href=\"#\">" + this.getFirstName() + "</a>\n" + this.getFamilyTreeSiblings() +
                    "</li>";
        } else if (this.mother != null && this.father != null)  {
            String ancestorsMother = this.mother.getFamilyTree();
            String ancestorsFather = this.father.getFamilyTree();
            return "<li>\n" +
                    "<a href=\"#\">" + this.getFirstName() + "</a>\n" + this.getFamilyTreeSiblings() +
                    "<ul>" + ancestorsMother + ancestorsFather + "</ul>" +
                    "</li>";
        } else if (this.mother != null) {
            return "<li>\n" +
                    "<a href=\"#\">" + this.getFirstName() + "</a>\n" + this.getFamilyTreeSiblings() +
                    "<ul>" +  this.mother.getFamilyTree() + "</ul>" +
                    "</li>";
        } else {
             return "<li>\n" +
                     "<a href=\"#\">" + this.getFirstName() + "</a>\n" + this.getFamilyTreeSiblings() +
                    "<ul>" + this.father.getFamilyTree()  + "</ul>" +
                    "</li>";
        }
    }

    public Human(int age, String firstName,  Human mother, Human father) {
        this.age = age;
        this.firstName = firstName;
        this.mother = mother;
        this.father = father;
    }
    public Human(int age, String firstName, String lastName, Human mother, Human father) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mother = mother;
        this.father = father;
    }

    public Human(int age, String firstName, String lastName) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Human getOldestSibling() {
         if (this.getSiblings().size() > 1) {
            Human result = this.getSiblings().get(0);
            for (Human h : this.siblings) {
                if (h.getAge() > result.getAge() && h.getAge() > this.getAge()) {
                    result = h;
                    return result;
                }
            }
         }
         return this;
    }

    public Human getOldestFamilyMember() {
        if (this.mother == null && this.father == null) {
            return this.getOldestSibling();
        } else if (this.mother != null && this.father != null)  {
            if (this.getAge() < this.mother.getOldestFamilyMember().getAge() && this.getAge() < this.father.getOldestFamilyMember().getAge()) {
                Human oldestMother = this.mother.getOldestFamilyMember().getOldestSibling();
                Human oldestFather = this.father.getOldestFamilyMember().getOldestSibling();
                if (oldestMother.getAge() < oldestFather.getAge()) {
                    return oldestFather;
                } else {
                    return oldestMother;
                }
            }
        } else if (this.mother != null) {
            if (this.getAge() < this.mother.getOldestFamilyMember().getAge()) {
                return this.mother.getOldestFamilyMember().getOldestSibling();
            }
        } else {
            if (this.getAge() < this.father.getOldestFamilyMember().getAge()) {
                return this.father.getOldestFamilyMember().getOldestSibling();
            }
        }
        return this.getOldestSibling();
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    @Override
    public String toString() {
        return "Human{" +
                "age=" + age +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mother=" + mother +
                ", father=" + father +
                ", siblings=" + siblings +
                ", country='" + country + '\'' +
                '}';
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
