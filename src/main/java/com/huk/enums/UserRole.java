package com.huk.enums;

import java.util.stream.Stream;

public enum UserRole {
    SUPER_ADMIN, ADMIN, USER;

    public static UserRole defineRole(String value) {
        return Stream.of(values())
                     .filter(v -> v.name().equals(value))
                     .findFirst()
                     .orElseThrow(() -> new RuntimeException("UserRole not found"));
    }
}
