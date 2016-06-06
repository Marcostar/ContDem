package com.example.contactdemo.Model;

/**
 * Created by Sagar on 6/6/2016.
 */
public class Contacts {
    String FirstName, LastName, Email;
    int mobileNumber;

    public Contacts(String firstName, String lastName, String email, int mobileNumber) {
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        this.mobileNumber = mobileNumber;
    }

    public Contacts() {

    }

    // setter and getter methods for all the variables
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
