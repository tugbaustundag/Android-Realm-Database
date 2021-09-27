package com.smality.realm_database_android;

import io.realm.RealmObject;
public class Student extends RealmObject {
    int roll_no;
    String name;

    public int getRoll_no() { return roll_no; }
    public void setRoll_no(int roll_no) {
        this.roll_no = roll_no;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
