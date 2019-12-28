package com.company.scorh.domain;

import java.io.Serializable;

public class Notice implements Serializable {
    private Integer id;
    private String name;
    private Boolean reserved;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isReserved() {
        return reserved;
    }

    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }
}
