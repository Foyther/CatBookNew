package com.example.catbooknew.dto;

public enum Permission {
    READ("read"), WRITE("write"), REMOVE("remove");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
