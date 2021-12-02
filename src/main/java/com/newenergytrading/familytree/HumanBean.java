package com.newenergytrading.familytree;

import java.util.ArrayList;
import java.util.List;

public class HumanBean {
    private int age;
    private String firstName;
    private String lastName;
    private Integer motherIndex;
    private Integer fatherIndex;
    private List<Integer> siblingsIndex = new ArrayList<>();
    private Integer country;



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
}
