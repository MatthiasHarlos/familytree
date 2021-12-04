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
    private CountryForm country;
    private String gender;
    private int listNumber;
    private String closeButton;
    private String isParent;
    private Human childMom;
    private Human childDad;

    public void setIsNoParent() {
        if(childMom != null && !this.childMom.getMother().equals(this) || childDad != null && !this.childDad.getFather().equals(this)) {
            isParent = null;
        }
    }

    public Human possibleMother(Human possibleMother, int numberOfList) {
        if (possibleMother.getListNumber() == numberOfList) {
            return possibleMother;
        } else if(possibleMother.getMother() != null && possibleMother.possibleMother(possibleMother.getMother(), numberOfList).getListNumber() == numberOfList) {
            return possibleMother.possibleMother(possibleMother.getMother(), numberOfList);
        }
        return null;
    }
    public Human possibleFather(Human possibleFather, int numberOfList) {
        if (possibleFather.getListNumber() == numberOfList) {
            return possibleFather;
        } else if(possibleFather.getFather() != null && possibleFather.possibleFather(possibleFather.getFather(), numberOfList).getListNumber() == numberOfList) {
            return possibleFather.possibleFather(possibleFather.getFather(), numberOfList);
        }
        return null;
    }

    public String getFamilyTreeSiblings() {
        String siblingString = "";
        if (this.siblings != null) {
            for (Human human : this.siblings) {
                siblingString += "<span href=\"#\" style='background-color:silver'>" +
                        human.getFirstName() + "</span>";
            }
            return siblingString;
        }
        return siblingString;
    }

    public String getFamilyTree() {
        String genderColor = "";
        if (gender != null) {
            genderColor = gender;
        }
        if (this.mother == null && this.father == null) {
            return "<li>\n" +
                    "<a href=\"#\"" + genderColor + " class='open-button' onclick='openForm" + this.getFirstName() + this.getLastName() + "()'>" + this.getFirstName() + " " + this.getLastName() + "</a>\n" + this.getFamilyTreeSiblings() +
                    "</li>";
        } else if (this.mother != null && this.father != null)  {
            String ancestorsMother = this.mother.getFamilyTree();
            String ancestorsFather = this.father.getFamilyTree();
            return "<li>\n" +
                    "<a href=\"#\"" + genderColor + " class='open-button' onclick='openForm" + this.getFirstName() + this.getLastName() + "()'>" + this.getFirstName() + " " + this.getLastName() + "</a>\n" + this.getFamilyTreeSiblings() +
                    "<ul>" + ancestorsMother + ancestorsFather + "</ul>" +
                    "</li>";
        } else if (this.mother != null) {
            return "<li>\n" +
                    "<a href=\"#\"" + genderColor + " class='open-button' onclick='openForm" + this.getFirstName() + this.getLastName() + "()'>" + this.getFirstName() + " " + this.getLastName() + "</a>\n" + this.getFamilyTreeSiblings() +
                    "<ul>" +  this.mother.getFamilyTree() + "</ul>" +
                    "</li>";
        } else {
             return "<li>\n" +
                     "<a href=\"#\"" + genderColor + " class='open-button' onclick='openForm" + this.getFirstName() + this.getLastName() + "()'>" + this.getFirstName() + " " + this.getLastName() + "</a>\n" + this.getFamilyTreeSiblings() +
                     "<ul>" + this.father.getFamilyTree()  + "</ul>" +
                    "</li>";
        }
    }

    public String getCloseButton() {
        return "\n" +
                "        <button type=\"button\" class=\"btn cancel\" onclick=\"closeForm" + this.firstName + this.lastName + "()\">Schließen</button>";
    }

    public String getScript() {
        if (this.mother == null && this.father == null) {
            return "function openForm" + this.getFirstName() + this.getLastName() + "() {\n" +
                    "  document.getElementById('myForm" + this.getFirstName() + this.getLastName() + "').style.display = \"block\";\n" +
                    "}\n" +
                    "\n" +
                    "function closeForm" + this.getFirstName() + this.getLastName() + "() {\n" +
                    "  document.getElementById('myForm" + this.getFirstName() + this.getLastName() + "').style.display = \"none\";\n" +
                    "}\n";
        } else if (this.mother != null && this.father != null)  {
            String ancestorsMother = this.mother.getScript();
            String ancestorsFather = this.father.getScript();
            return "function openForm" + this.getFirstName() + this.getLastName() + "() {\n" +
                    "  document.getElementById('myForm" + this.getFirstName() + this.getLastName() + "').style.display = \"block\";\n" +
                    "}\n" +
                    "\n" +
                    "function closeForm" + this.getFirstName() + this.getLastName() + "() {\n" +
                    "  document.getElementById('myForm" + this.getFirstName() + this.getLastName() + "').style.display = \"none\";\n" +
                    "}\n" + ancestorsMother + ancestorsFather;
        }
        else if (this.mother != null) {
            return "function openForm" + this.getFirstName() + this.getLastName() + "() {\n" +
                    "  document.getElementById('myForm" + this.getFirstName() + this.getLastName() + "').style.display = \"block\";\n" +
                    "}\n" +
                    "\n" +
                    "function closeForm" + this.getFirstName() + this.getLastName() + "() {\n" +
                    "  document.getElementById('myForm" + this.getFirstName() + this.getLastName() + "').style.display = \"none\";\n" +
                    "}\n" + this.mother.getScript();
        }
        else {
            return "function openForm" + this.getFirstName() + this.getLastName() + "() {\n" +
                    "  document.getElementById('myForm" + this.getFirstName() + this.getLastName() + "').style.display = \"block\";\n" +
                    "}\n" +
                    "\n" +
                    "function closeForm" + this.getFirstName() + this.getLastName() + "() {\n" +
                    "  document.getElementById('myForm" + this.getFirstName() + this.getLastName() + "').style.display = \"none\";\n" +
                    "}\n" + this.father.getScript();
        }

    }


    public Human(int age, String firstName,  Human mother, Human father) {
        this.age = age;
        this.firstName = firstName;
        this.mother = mother;
        this.father = father;
    }

    public Human() {
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



    public CountryForm getCountry() {
        return country;
    }

    public void setCountry(CountryForm country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getListNumber() {
        return listNumber;
    }

    public void setListNumber(int listNumber) {
        this.listNumber = listNumber;
    }

    public String isParent() {
        return isParent;
    }

    public void setParent(String parent) {
        isParent = parent;
    }

    public Human getChildMom() {
        return childMom;
    }

    public void setChildMom(Human childMom) {
        this.childMom = childMom;
    }

    public Human getChildDad() {
        return childDad;
    }

    public void setChildDad(Human childDad) {
        this.childDad = childDad;
    }
}
