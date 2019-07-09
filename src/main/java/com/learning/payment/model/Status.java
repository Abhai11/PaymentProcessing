package com.learning.payment.model;

public enum Status {
    SUCCESS("success"),
    FAIL("fail");

    private String code;

    Status(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
