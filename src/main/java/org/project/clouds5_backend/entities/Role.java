package org.project.clouds5_backend.entities;

public enum Role {
    USER(0),
    ADMIN(1);

    private final int value;

    Role(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
