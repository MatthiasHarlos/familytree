package com.newenergytrading.familytree;

public class CountryForm {

    private String country;
    private String continent;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
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
