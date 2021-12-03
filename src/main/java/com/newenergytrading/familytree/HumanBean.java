package com.newenergytrading.familytree;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class HumanBean {

    @NotNull(message = "Bitte wähle ein Alter")
    private Integer age;
    private String firstName;
    private String lastName;
    private Integer motherIndex;
    private Integer fatherIndex;
    private List<Integer> siblingsIndex = new ArrayList<>();
    @NotNull(message = "Bitte wähle zuerst ein Land aus!")
    private Integer country;
    private Integer gender;
    private Integer initialGeneration;
    private int listNumber;


    public HumanBean() {

    }

    public HumanBean( Integer country) {
        if(country == null) {
            throw new IllegalArgumentException();
        }
        this.country = country;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getMotherIndex() {
        return motherIndex;
    }

    public void setMotherIndex(Integer motherIndex) {
        this.motherIndex = motherIndex;
    }

    public Integer getFatherIndex() {
        return fatherIndex;
    }

    public void setFatherIndex(Integer fatherIndex) {
        this.fatherIndex = fatherIndex;
    }

    public List<Integer> getSiblingsIndex() {
        return siblingsIndex;
    }

    public void setSiblingsIndex(List<Integer> siblingsIndex) {
        this.siblingsIndex = siblingsIndex;
    }

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public int getListNumber() {
        return listNumber;
    }

    public void setListNumber(int listNumber) {
        this.listNumber = listNumber;
    }

    public Integer getInitialGeneration() {
        return initialGeneration;
    }

    public void setInitialGeneration(Integer initialGeneration) {
        this.initialGeneration = initialGeneration;
    }

    @Override
    public String toString() {
        return "HumanBean{" +
                "age=" + age +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", motherIndex=" + motherIndex +
                ", fatherIndex=" + fatherIndex +
                ", siblingsIndex=" + siblingsIndex +
                ", country=" + country +
                ", gender=" + gender +
                ", listNumber=" + listNumber +
                '}';
    }
}
