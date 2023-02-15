package org.example;

import jakarta.persistence.*;

@Entity
public class BuddyInfo {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String phoneNumber;

    private String addressBookId;

    public BuddyInfo(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        addressBookId = "";
    }

    public BuddyInfo() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddressBookId() {
        return addressBookId;
    }

    public void setAddressBookId(String id) {
        addressBookId = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String toString() {
        return "Name: " + name + ", Phone Number: " + phoneNumber;
    }
}
