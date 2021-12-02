package com.newenergytrading.familytree;

public class CountryForm {

    private String country;
    private Integer continent;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getContinent() {
        return continent;
    }

    public void setContinent(Integer continent) {
        this.continent = continent;
    }

    @Override
    public String toString() {
        return "CountryForm{" +
                "country='" + country + '\'' +
                ", continent=" + continent +
                '}';
    }
}
