package com.validator.model;

public class OrcidValidationResult {
    private final boolean formatValid;
    private final boolean exists;
    private final String name;

    public OrcidValidationResult(boolean formatValid, boolean exists, String name) {
        this.formatValid = formatValid;
        this.exists = exists;
        this.name = name;
    }

    public boolean isFormatValid() {
        return formatValid;
    }

    public boolean exists() {
        return exists;
    }

    public String getName() {
        return name;
    }
}
