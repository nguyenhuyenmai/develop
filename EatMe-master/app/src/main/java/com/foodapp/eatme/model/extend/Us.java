package com.foodapp.eatme.model.extend;

public class Us {
    private double amount;
    private String unitShort;
    private String unitLong;

    public Us() {
    }

    public Us(double amount, String unitShort, String unitLong) {
        this.amount = amount;
        this.unitShort = unitShort;
        this.unitLong = unitLong;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUnitShort() {
        return unitShort;
    }

    public void setUnitShort(String unitShort) {
        this.unitShort = unitShort;
    }

    public String getUnitLong() {
        return unitLong;
    }

    public void setUnitLong(String unitLong) {
        this.unitLong = unitLong;
    }
}