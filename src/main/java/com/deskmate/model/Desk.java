package com.deskmate.model;

import java.time.LocalDateTime;

public class Desk {
    private long deskId;
    private String deskCode;
    private String name;
    private boolean active;
    private LocalDateTime createdAt;

    public Desk(long deskId, String deskCode, String name, boolean active, LocalDateTime createdAt) {
        this.deskId = deskId;
        this.deskCode = deskCode;
        this.name = name;
        this.active = active;
        this.createdAt = createdAt;
    }

    public long getDeskId() { return deskId; }
    public String getDeskCode() { return deskCode; }
    public String getName() { return name; }
    public boolean isActive() { return active; }
}

