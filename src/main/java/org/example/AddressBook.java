package org.example;


import jakarta.persistence.*;

import java.util.*;

@Entity
public class AddressBook {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(fetch=FetchType.EAGER)
    private List<BuddyInfo> buddies;

    public AddressBook() {
        buddies = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addBuddy(BuddyInfo buddy) {
        buddies.add(buddy);
    }

    public List<BuddyInfo> getBuddies() {
        return buddies;
    }

    public void setBuddies(List<BuddyInfo> buddies) {
        this.buddies = buddies;
    }

    public void printContent() {
        for (BuddyInfo buddy : buddies) {
            System.out.println(buddy);
        }
    }

    public String toString() {
        String str = "";
        str += "Id: " + id + "\n";
        for (BuddyInfo buddy : buddies) {
            str += buddy.toString() + "\n";
        }
        return str;
    }

    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        BuddyInfo buddy1 = new BuddyInfo("Bob", "123");
        BuddyInfo buddy2 = new BuddyInfo("Joe", "456");
        addressBook.addBuddy(buddy1);
        addressBook.addBuddy(buddy2);
        addressBook.printContent();
    }
}
