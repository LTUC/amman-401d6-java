package com.company.mansys;

public class Address {

    private double latt;
    private double lng;

    private String address;

    public Address(String address) {
        this.address = address;
    }

    public double getLatt() {
        return latt;
    }

    public void setLatt(double latt) {
        this.latt = latt;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
