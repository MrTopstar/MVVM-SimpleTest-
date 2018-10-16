package com.aceplussolutions.myosetpaing.mvvmtest.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

@Entity
public class Contact {

    @PrimaryKey(autoGenerate = true)
    public int id;
    private String name;
    private String phno;
    @TypeConverters(DateConverter.class)
    private Date contactDate;

    public Contact(String name, String phno, Date contactDate) {
        this.name = name;
        this.phno = phno;
        this.contactDate = contactDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public Date getContactDate() {
        return contactDate;
    }

    public void setContactDate(Date contactDate) {
        this.contactDate = contactDate;
    }
}
