package com.company.scorh.domain;

import java.io.Serializable;
import java.util.Date;

public class Test implements Serializable {
    private int id;
    private Date date;
    private Date datetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", date=" + date +
                ", datetime=" + datetime +
                '}';
    }
}
