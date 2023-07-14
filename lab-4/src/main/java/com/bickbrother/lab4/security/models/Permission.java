package com.bickbrother.lab4.security.models;

public enum Permission {
    READ("read"),
    WRITE("write"),
    EDIT("edit");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
