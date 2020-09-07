package ru.devyatov.htc_test;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Employee implements Comparable<Employee> {

    private String name;
    @SerializedName("phone_number")
    private int phoneNumber;
    private List<String> skills;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    @Override
    public int compareTo(Employee o) {
        if (this.getName() == null && o.getName() == null) {
            return 0;
        }
        if (o.getName() == null) {
            return -1;
        }
        if (this.getName() == null) {
            return 1;
        }
        return this.getName().compareTo(o.getName());
    }
}
