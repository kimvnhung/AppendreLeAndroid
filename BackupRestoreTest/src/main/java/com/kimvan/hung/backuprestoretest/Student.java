package com.kimvan.hung.backuprestoretest;

/**
 * Created by h on 17/01/2018.
 */

public class Student {
    private String name;
    private String bornDate;
    private String telephoneNumber;

    public Student(String name, String bornDate, String telephoneNumber) {
        this.name = name;
        this.bornDate = bornDate;
        this.telephoneNumber = telephoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}
