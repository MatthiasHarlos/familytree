package com.newenergytrading.familytree;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class HumanBean {
    private int age;
    private String firstName;
    private String lastName;
    private Integer motherIndex;
    private Integer fatherIndex;
    private List<Integer> siblingsIndex = new ArrayList<>();
    @NotNull(message = "Bitte wähle ein Land!")
    private Integer country;
    private Integer gender;


    public HumanBean() {

    }

    public HumanBean( Integer country) {
        if(country == null) {
            throw new IllegalArgumentException();
        }
        this.country = country;
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
}
