package com.epam.ts.entity;

public class UserAccount {
    private Integer id;
    private Double prepaidAmount;

    public Double getPrepaidAmount() {
        return prepaidAmount;
    }

    public void setPrepaidAmount(Double prepaidAmount) {
        this.prepaidAmount = prepaidAmount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
