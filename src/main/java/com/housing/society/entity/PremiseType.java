package com.housing.society.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PremiseType {
    BHK_1("1bhk"),
    BHK_2("2bhk"),
    BHK_3("3bhk"),
    VILLA("villa"),
    SHOP("shop");

    private final String value;

    PremiseType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static PremiseType fromValue(String value) {
        if (value == null) {
            return null;
        }
        for (PremiseType t : values()) {
            if (t.value.equalsIgnoreCase(value)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Invalid premiseType: " + value);
    }
}
