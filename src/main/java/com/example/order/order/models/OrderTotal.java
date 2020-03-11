package com.example.order.order.models;

public class OrderTotal {

    private double total;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public OrderTotal() {
    }

    public OrderTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderTotal{" +
                ", total=" + total +
                '}';
    }
}
