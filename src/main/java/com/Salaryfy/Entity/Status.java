package com.Salaryfy.Entity;

public enum Status {
    FALSE(false),
    TRUE(true);

    private final boolean status;

    Status(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public static Status fromString(String status) {
        for (Status s : Status.values()) {
            if (s.getStatus()) {
                return s;
            }
        }
        throw new IllegalArgumentException("Invalid status: " + status);
    }
}
