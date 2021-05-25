package com.nouhoun.springboot.jwt.integration.domain;

public class Cart {

    public Cart(double value) {
        this.value = value;
    }

    private double value;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
